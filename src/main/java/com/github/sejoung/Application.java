package com.github.sejoung;

import com.github.sejoung.domain.TestDto;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.List;

/**
 * @author kim se joung
 *
 */
@SpringBootApplication
public class Application {


    @Autowired
    private RetryTemplate retryTemplate;

    public static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
   // @Bean
    public ApplicationRunner runner(Producer producer) {
        return (args) -> producer.send("test");
    }

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(2000l);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);

        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(3);

        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }
    
    //@KafkaListener(topicPartitions = { @TopicPartition(topic = "test", partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")) })
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info( cr.value().toString());
       
    }
   // @KafkaListener(topicPattern="trackingTest")
   // @KafkaListener(topicPartitions = { @TopicPartition(topic = "ClickViewData", partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")) })
    public void listen(@Payload String payload,
            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts
            ) {
        
            logger.info("RECEIVED_MESSAGE_KEY={}, RECEIVED_PARTITION_ID={}, RECEIVED_TOPIC={}, RECEIVED_TIMESTAMP={}", key, partition, topic, ts);
            logger.info("Payload={}",payload);
    }
    @KafkaListener(topicPattern="ClickViewData", errorHandler = "voidSendToErrorHandler")
   // @KafkaListener(topicPartitions = { @TopicPartition(topic = "ClickViewData", partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")) })
    public void listen(List<TestDto> list) throws Exception {

        retryTemplate.execute((retryCallback)->{

            logger.debug("size = {}", list.size());


            logger.debug("RetryCount = {}",retryCallback.getRetryCount());

            throw new Exception();
        });
    }
    
 //   @KafkaListener(topicPattern="ClickViewData", errorHandler = "voidSendToErrorHandler")
    public void voidListenerWithReplyingErrorHandler(String in) {
        throw new RuntimeException("fail");
    }
    
    @Bean
    public KafkaListenerErrorHandler voidSendToErrorHandler() {
        return (m, e) -> {
            
            logger.error("error payload \n{}",m.getPayload().toString());
           // e.printStackTrace();
            return "error";
        };
    }

}
package com.github.sejoung;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import com.github.sejoung.domain.TestDto;

/**
 * @author kim se joung
 *
 */
@SpringBootApplication
public class Application {

    public static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //@KafkaListener(topicPartitions = { @TopicPartition(topic = "test", partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")) })
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info( cr.value().toString());
       
    }
   // @KafkaListener(topicPattern="ClickViewData")
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
    @KafkaListener(topicPattern="ClickViewData")
   // @KafkaListener(topicPartitions = { @TopicPartition(topic = "ClickViewData", partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")) })
    public void listen(List<TestDto> list) {

           for(TestDto test: list) {
               if("AU".equals(test.getAdGubun())) {
                   logger.info(test.toString());

               }
           }
    
    }

}
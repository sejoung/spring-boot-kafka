package com.github.sejoung;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;

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

    @KafkaListener(topicPartitions = { @TopicPartition(topic = "test", partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "0")) })
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info( cr.value().toString());
       
    }

}
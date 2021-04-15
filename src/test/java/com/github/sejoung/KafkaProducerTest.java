package com.github.sejoung;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.ExecutionException;


@Slf4j
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
class KafkaProducerTest {

    @Autowired
    private KafkaProducer producer;

    @Test
    void sendTest() throws ExecutionException, InterruptedException {
        var future = producer.send("test payload");
        Assertions.assertThat(future.get().getRecordMetadata().topic()).isEqualTo(Constants.TOPIC);
    }
}
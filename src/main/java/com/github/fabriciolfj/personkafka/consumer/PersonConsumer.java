package com.github.fabriciolfj.personkafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@KafkaListener(id = "personconsumer", topics = "${topic.person}")
public class PersonConsumer {

    @KafkaHandler
    @RetryableTopic(
            backoff = @Backoff(value = 3000L),
            attempts= "5")
    public void handlePerson(final String message) {
        log.info("Receive: {}", message);
    }
}

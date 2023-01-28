package com.github.fabriciolfj.personkafka.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fabriciolfj.personkafka.dto.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PersonProducer {

    private final KafkaTemplate<String, String> template;
    private final ObjectMapper mapper;

    @Value("${topic.person}")
    private String topic;

    @Transactional
    public void execute(final Person person) throws JsonProcessingException {
        var message = mapper.writeValueAsString(person);

        template.send(topic, message);
    }

}

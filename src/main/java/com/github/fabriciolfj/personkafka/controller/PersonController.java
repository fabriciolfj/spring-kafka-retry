package com.github.fabriciolfj.personkafka.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fabriciolfj.personkafka.consumer.PersonConsumer;
import com.github.fabriciolfj.personkafka.dto.Person;
import com.github.fabriciolfj.personkafka.producer.PersonProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonProducer personProducer;

    @PostMapping
    public void create() throws JsonProcessingException {
        var person = Person.builder().id(UUID.randomUUID().toString()).name("teste").build();
        personProducer.execute(person);
    }
}

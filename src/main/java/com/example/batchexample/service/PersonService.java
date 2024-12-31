package com.example.batchexample.service;

import com.example.batchexample.entity.Person;
import com.example.batchexample.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService( PersonRepository personRepository ) {
        this.personRepository = personRepository;
    }

    public Person save( Person person ) {
        return this.personRepository.save( person );
    }
}

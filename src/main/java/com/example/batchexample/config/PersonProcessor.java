package com.example.batchexample.config;

import com.example.batchexample.entity.Person;
import com.example.batchexample.entity.PersonDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class PersonProcessor implements ItemProcessor< PersonDTO, Person > {
    @Override
    public Person process( PersonDTO personDTO ) throws Exception {
        Person person = new Person();
        person.setIdentityCard( personDTO.identityCard() );
        person.setName( personDTO.name() );
        person.setMiddleName( personDTO.middleName() );
        person.setLastName( personDTO.lastName() );
        person.setDob( parseDate( personDTO.dob() ) );
        person.setGenre( personDTO.gender() );
        return person;
    }

    private LocalDate parseDate( String dateString ) {
        return LocalDate.parse( dateString );
    }
}

package com.example.batchexample.config;

import com.example.batchexample.entity.PersonDTO;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class PersonDTOFieldSetMapper implements FieldSetMapper<PersonDTO> {
    @Override
    public PersonDTO mapFieldSet( FieldSet fieldSet ) throws BindException {

        return new PersonDTO(
                fieldSet.readString( "identityCard" ),
                fieldSet.readString( "name" ),
                fieldSet.readString( "lastName" ),
                fieldSet.readString( "middleName" ),
                fieldSet.readString( "dob" ),
                fieldSet.readChar( "gender" ));

    }
}

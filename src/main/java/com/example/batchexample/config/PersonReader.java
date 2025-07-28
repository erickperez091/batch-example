package com.example.batchexample.config;

import com.example.batchexample.entity.PersonDTO;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.ClassPathResource;

public class PersonReader extends FlatFileItemReader< PersonDTO > {

    public PersonReader() {
        this.setLinesToSkip(1);
        this.setName( "person" );
        this.setResource( new ClassPathResource( "persons.csv" ) );
        DefaultLineMapper<PersonDTO> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames( "identityCard", "name", "lastName", "middleName", "dob", "gender" );
        lineMapper.setLineTokenizer( lineTokenizer );
        lineMapper.setFieldSetMapper( new PersonDTOFieldSetMapper() );
        setLineMapper( lineMapper );
    }
}

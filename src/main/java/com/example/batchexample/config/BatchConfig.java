package com.example.batchexample.config;

import com.example.batchexample.entity.Person;
import com.example.batchexample.entity.PersonDTO;
import com.example.batchexample.service.PersonService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

//    @Bean
//    public FlatFileItemReader< PersonDTO > itemReader() {
//        return new FlatFileItemReaderBuilder< PersonDTO >()
//                .name( "personReader" )
//                .linesToSkip( 1 )
//                .resource( new ClassPathResource( "persons.csv" ) )
//                .delimited()
//                .names( "identityCard", "name", "lastName", "middleName", "dob", "gender" )
//                .targetType( PersonDTO.class )
//                .build();
//    }

    @Bean
    public FlatFileItemReader< PersonDTO> itemReader() {
        return new PersonReader();
    }

    @Bean
    public ItemProcessor< PersonDTO, Person > processor() {
        return new PersonProcessor();
    }

    @Bean
    public ItemWriter< Person > writer( PersonService personService ) {
        return new PersonWriter( personService );
    }

    @Bean
    public Step step( JobRepository jobRepository, PlatformTransactionManager transactionManager,
                      ItemWriter< Person > writer, ItemProcessor< PersonDTO, Person > processor,
                      FlatFileItemReader< PersonDTO> reader ) {
        return new StepBuilder( "step1", jobRepository )
                .< PersonDTO, Person >chunk( 5, transactionManager )
                .reader( reader )
                .processor( processor )
                .writer( writer ).build();
    }

    @Bean
    public Job personJob( JobRepository jobRepository, Step step, PersonJobCompletionListener listener ) {
        return new JobBuilder( "PersonJob", jobRepository )
                .listener( listener )
                .start( step )
                .build();
    }
}

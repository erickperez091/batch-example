package com.example.batchexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan( { "com.example" } )
@EnableJpaRepositories( basePackages = { "com.example.batchexample.repository" } )
public class BatchExampleApplication {

    // System.exit(SpringApplication.exit(SpringApplication.run(BatchProcessingApplication.class, args)));
    public static void main( String[] args ) {
        SpringApplication.run( BatchExampleApplication.class, args );
    }

}

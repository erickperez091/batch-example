package com.example.batchexample.config;

import com.example.batchexample.entity.Person;
import com.example.batchexample.service.PersonService;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CompletableFuture;

public class PersonWriter implements ItemWriter< Person > {

    @Autowired
    private PersonService personService;

    public PersonWriter(  ) {
    }

    @Override
    public void write( Chunk< ? extends Person > chunk ) throws Exception {
        CompletableFuture< Void > personCompletableFuture = CompletableFuture.runAsync( () -> {
            System.out.println("[CompletableFuture][PersonWriter][write][SavingPerson]");
            chunk.forEach( person -> {
                personService.save( person );
                System.out.printf("[CompletableFuture][PersonWriter][write][SavingPerson][Person Id][%s]%n", person.getId());
            } );
        } );
        CompletableFuture<Void> writeInConsoleCompletableFuture = CompletableFuture.runAsync( () -> {
            chunk.forEach( person -> {
                System.out.printf( "[CompletableFuture][PersonWriter][write][PrintingPerson][%s]%n", person.toString());
            } );
        });

        CompletableFuture.allOf( personCompletableFuture, writeInConsoleCompletableFuture ).thenRun( () ->{
            personCompletableFuture.join();
            writeInConsoleCompletableFuture.join();
        } );
    }
}

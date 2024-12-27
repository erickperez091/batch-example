package com.example.batchexample.repository;

import com.example.batchexample.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository< Person, Integer > {
}

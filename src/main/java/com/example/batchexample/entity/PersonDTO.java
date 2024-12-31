package com.example.batchexample.entity;

public record PersonDTO(String identityCard, String name, String lastName, String middleName, String dob,
                        Character gender) {
}

package com.example.batchexample.entity;

import java.time.LocalDate;

public record PersonDTO(String identityCard, String name, String lastName, String middleName, String dob, Character gender) {
}

package com.example.batchexample.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.StringJoiner;

import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE;

@Entity
@Table( name = "person",
        indexes = {
                @Index( name = "person_idx_id", unique = true, columnList = "id" ),
                @Index( name = "person_idx_identity_card", unique = true, columnList = "identityCard" ) },
        uniqueConstraints = {
                @UniqueConstraint( name = "person_id_ctx", columnNames = "id" ),
                @UniqueConstraint( name = "person_identity_card_ctx", columnNames = "identityCard" )
        }
)
public class Person implements Serializable {

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column( name = "identityCard" )
    private String identityCard;

    @Column( name = "name" )
    private String name;

    @Column( name = "lastName" )
    private String lastName;

    @Column( name = "middleName" )
    private String middleName;

    @DateTimeFormat( pattern = "yyyy-MM-dd", iso = DATE )
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" )
    @Column( name = "dob", columnDefinition = "DATE" )
    @JsonDeserialize( using = LocalDateDeserializer.class )
    @JsonSerialize( using = LocalDateSerializer.class )
    private LocalDate dob;

    @Column( name = "genre" )
    private Character genre;

    public Person() {
    }

    public Person( Integer id, String identityCard, String name, String lastName, String middleName, LocalDate dob, Character genre ) {
        this.id = id;
        this.identityCard = identityCard;
        this.name = name;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dob = dob;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard( String identityCard ) {
        this.identityCard = identityCard;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName( String middleName ) {
        this.middleName = middleName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob( LocalDate dob ) {
        this.dob = dob;
    }

    public Character getGenre() {
        return genre;
    }

    public void setGenre( Character genre ) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return new StringJoiner( ", ", Person.class.getSimpleName() + "[", "]" )
                .add( "id=" + id )
                .add( "identityCard='" + identityCard + "'" )
                .add( "name='" + name + "'" )
                .add( "lastName='" + lastName + "'" )
                .add( "middleName='" + middleName + "'" )
                .add( "dob=" + dob )
                .add( "genre=" + genre )
                .toString();
    }
}

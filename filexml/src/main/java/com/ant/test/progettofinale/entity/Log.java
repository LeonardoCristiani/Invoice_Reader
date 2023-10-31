package com.ant.test.progettofinale.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * Tabella log.
 */
@Entity
@Data
public class Log extends Generic_Entity
{
    /**
     * ID interno.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Messaggio.
     */
    @Column(name = "message")
    private String message;

    /**
     * Data di inserimento.
     */
    @Column(name = "date")
    private LocalDate date;
}
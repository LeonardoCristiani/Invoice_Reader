package com.ant.test.progettofinale.dto;

import lombok.Data;

/**
 * DTO per i dati delle banche.
 */
@Data
public class BankDTO 
{
    /**
     * ID interno della banca.
     */
    private long id;
    /**
     * Nome della banca.
     */
    private String name;
}
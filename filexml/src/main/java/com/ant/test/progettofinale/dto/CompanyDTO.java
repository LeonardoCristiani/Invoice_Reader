package com.ant.test.progettofinale.dto;

import lombok.Data;

/**
 * DTO per i dati dell'azienda.
 */
@Data
public class CompanyDTO 
{
    /**
     * ID interno dell'azienda.
     */
    private long id;
    /**
     * Nome dell'azienda.
     */
    private String name;
    /**
     * Partita IVA dell'azienda.
     */
    private String vat_Number;
    /**
     * Indirizzo dell'azienda.
     */
    private String address;
}
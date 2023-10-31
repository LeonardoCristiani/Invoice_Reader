package com.ant.test.progettofinale.dto;

import lombok.Data;

/**
 * DTO per i dati di un conto corrente.
 */
@Data
public class Bank_AccountDTO 
{
    /**
     * ID interno del conto.
     */
    private long id;
    /**
     * IBAN.
     */
    private String iban;
    /**
     * Valore massimo del castelletto.
     */
    private double max_value;
    /**
     * ID dell'azienda associata con questo conto.
     */
    private long company_id;
    /**
     * ID della banca associata con questo conto.
     */
    private long bank_id;
    
}
package com.ant.test.progettofinale.dto;

import java.time.LocalDate;

import lombok.Data;

/**
 * DTO per i dati delle fatture.
 */
@Data
public class InvoiceDTO 
{
    /**
     * ID interno della fattira.
     */
    private long id;
    /**
     * Numero della fattura.
     */
    private String invoiceNumber;
    /**
     * Imponibile.
     */
    private double income;
    /**
     * IVA.
     */
    private double vat;
    /**
     * Ritenuta d'acconto (solo fatture passive).
     */
    private double retention_Tax;
    /**
     * Causale.
     */
    private String description;
    /**
     * Data di emissione (attiva) / ricezione (passiva).
     */
    private LocalDate issue_Date;
    /**
     * Data di scadenza.
     */
    private LocalDate expiration_Date;
    /*
     * Data di pagamento.
     */
    private LocalDate collection_Date;
    /**
     * Indica se la fattura è stata anticipata.
     */
    private boolean advanced;
    /**
     * Indica se la fattura è stata stornata.
     */
    private boolean reversed;
}
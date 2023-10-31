package com.ant.test.progettofinale.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Tabella fatture.
 */
@Entity
@Table(name = "invoices")
@Data
public class Invoice extends Generic_Entity
{
    /**
     * ID interno.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Numero fattura.
     */
    @Column(name = "invoice_number")
    private String invoiceNumber;

    /**
     * Imponibile.
     */
    @Column(name = "income")
    private double income;

    /**
     * IVA.
     */
    @Column(name = "vat")
    private double vat;

    /**
     * Ritenuta d'acconto (solo fatture passive).
     */
    @Column(name = "retention_tax")
    private double retentionTax;

    /**
     * Description.
     */
    @Column(name = "description" , length = 1000)
    private String description;

    /**
     * Data di emissione (attiva) / ricezione (passiva).
     */
    @Column(name = "issue_date")
    private LocalDate issueDate;

    /**
     * Data di scadenza.
     */
    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    /**
     * Data di pagamento.
     */
    @Column(name = "collection_date")
    private LocalDate collectionDate;

    /**
     * Indica se la fattura è stata anticipata.
     */
    @Column(name = "advanced")
    private boolean wasAdvanced;

    /**
     * Indica se la fattura è stata stornata.
     */
    @Column(name = "reversed")
    private boolean wasReversed;
    
    /**
     * Tipo di documento.
     */
    @ManyToOne
    private Document_Type type;

    /**
     * Azienda che ha inviato la fattura.
     */
    @ManyToOne
    private Company sender;

    /**
     * Azienda che ha ricevuto la fattura.
     */
    @ManyToOne
    private Company receiver;
        
    /**
     * Conto corrente dove ricevere / inviare il pagamento.
     */
    @ManyToOne
    @JoinColumn(name = "bank_account")
    private Bank_Account bank_account;
    
}
package com.ant.test.progettofinale.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Tabella conti correnti.
 */
@Entity
@Table(name = "bank_accounts")
@Data
public class Bank_Account extends Generic_Entity
{
    /**
     * ID interno del conto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    /**
     * IBAN.
     */
    @Column(name = "iban")
    private String iban;

    /**
     * Banca associata al conto.
     */
    @ManyToOne
    private Bank bank;

    /**
     * Azienda associata al conto.
     */
    @ManyToOne
    private Company company;

    /**
     * Valore massimo del castelletto.
     */
    @Column(name = "max_value", nullable = true)
    private double max_value;

    /**
     * Lista di fattura alle quali il conto corrente è associato.
     */
    @OneToMany(mappedBy = "bank_account")
    private List<Invoice> invoices;
}
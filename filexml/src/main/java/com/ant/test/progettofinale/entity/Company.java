package com.ant.test.progettofinale.entity;

import java.util.*;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Tabella aziende.
 */
@Entity
@Table(name="companies")
@Data
public class Company extends Generic_Entity
{
    /**
     * ID interno.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    /**
     * Nome dell'azienda.
     */
    @Column(name="name")
    private String name;

    /**
     * Partita IVA azienda.
     */
    @Column(name="vat_number")
    private String vat_number;

    /**
     * Indirizzo azienda.
     */
    @Column(name="address")
    private String address;

    /**
     * Conti correnti associati all'azienda.
     */
    @OneToMany(mappedBy = "company")
    @JsonIgnore
    @Column(name = "bank_account_id")
    private List<Bank_Account> bank_account;

    /**
     * 
     */
    @OneToMany(mappedBy = "company")
    @Column(name="company_id")
    private  List<Association_Table> association_table;

    /**
     * Fatture ricevute dall'azienda.
     */
    @OneToMany(mappedBy = "receiver")
    @Column(name="receiver_id")
    private List<Invoice> receiver;

    /**
     * Fatture inviate dall'azienda.
     */
    @OneToMany(mappedBy = "sender")
    @Column(name="sender_id")
    private List<Invoice> sender;
}
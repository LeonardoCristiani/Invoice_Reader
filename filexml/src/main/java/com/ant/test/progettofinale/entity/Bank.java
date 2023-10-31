package com.ant.test.progettofinale.entity;
import java.util.List;

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
 * Tabella banche.
 */
@Entity
@Table(name = "banks")
@Data
public class Bank extends Generic_Entity
{
    /**
     * ID interno.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    /**
     * Nome della banca.
     */
    @Column(name = "name")
    private String name;
    
    /**
     * Conti correnti associati alla banca.
     */
    @OneToMany(mappedBy = "bank")
    @JsonIgnore
    private List<Bank_Account> Bank_Account;
}
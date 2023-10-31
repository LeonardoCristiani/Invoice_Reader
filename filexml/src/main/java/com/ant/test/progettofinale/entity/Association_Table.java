package com.ant.test.progettofinale.entity;

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
 * Tabella di associazione tra aziende e tipi di azienda.
 */
@Entity
@Table(name = "association_table")
@Data
public class Association_Table extends Generic_Entity
{
    /**
     * ID interno.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    /**
     * Azienda associata.
     */
    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    /**
     * Tipo di azienda.
     */
    @ManyToOne
    @JoinColumn(name="type_id")
    private Type type;
}
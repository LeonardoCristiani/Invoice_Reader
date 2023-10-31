package com.ant.test.progettofinale.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Tabella tipi di documenti.
 */
@Entity
@Table(name="document_type")
@Data
public class Document_Type extends Generic_Entity
{
    /**
     * ID interno.
     */
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    
    /**
     * Tipo di documento.
     */
    @Column(name="type")
    private String type;

    /**
     * Le fatture associato al tipo.
     */
    @OneToMany( mappedBy="type")
    private List< Invoice> invoice;
}
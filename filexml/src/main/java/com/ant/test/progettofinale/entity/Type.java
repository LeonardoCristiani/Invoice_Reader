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
 * Tabella tipo di azienda.
 */
@Entity
@Table(name="type")
@Data
public class Type extends Generic_Entity
{
    /**
     * ID interno.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Tipo di azienda.
     */
    @Column(name="type")
    private String type;

    /**
     * Istanze di {@link Association_Table} che collegano le aziende al loro tipo.
     */
    @OneToMany(mappedBy = "type")
    private List<Association_Table> association_table;
}
package com.ant.test.progettofinale.dto;

import lombok.Data;

/**
 * DTO per i dati relativi al tipo di un'azienda.
 */
@Data
public class TypeDTO 
{
    /**
     * ID interno del tipo.
     */
    private long id;
    /**
     * Tipo di azienda.
     */
    private String type;   
}
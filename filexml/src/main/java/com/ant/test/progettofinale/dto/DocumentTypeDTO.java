package com.ant.test.progettofinale.dto;

import lombok.Data;

/**
 * DTO per i dati dei tipi di documenti.
 */
@Data
public class DocumentTypeDTO 
{
    /**
     * ID interno del tipo.
     */
    private long id;
    /**
     * Tipo di documento.
     */
    private String type;
}
package com.ant.test.progettofinale.dto;

import lombok.Data;

/**
 * DTO per i dati della tabella di associazione tra aziende e tipi.
 */
@Data
public class AssociationDTO 
{
    /**
     * ID.
     */
    private long id;
    /**
     * ID dell'azienda.
     */
    private long id_Company;
    /**
     * ID del tipo di azienda.
     */
    private long id_type;
}
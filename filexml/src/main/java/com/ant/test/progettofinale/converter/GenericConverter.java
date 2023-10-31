package com.ant.test.progettofinale.converter;

import com.ant.test.progettofinale.entity.Generic_Entity;

/**
 * Convertitore generico per convertire l'instanza di un'entità nel suo DTO.
 */
public interface GenericConverter<E extends Generic_Entity, D>
{
    /**
     * Converte un entità nel suo DTO.
     * @param p Entità da convertire.
     * @return Il DTO risultato della conversione.
     */
    D fromEntityToDto(E p);

    /**
     * Converte un DTO nella sua entità associata.
     * @param d DTO da convertire.
     * @return L'entità risulato della conversione.
     */
    E fromDtoToEntity(D d);
}
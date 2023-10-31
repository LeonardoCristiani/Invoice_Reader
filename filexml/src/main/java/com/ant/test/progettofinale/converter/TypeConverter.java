package com.ant.test.progettofinale.converter;

import org.springframework.stereotype.Service;

import com.ant.test.progettofinale.dto.TypeDTO;
import com.ant.test.progettofinale.entity.Type;

/**
 * Convertitore per i dati dei tipi delle aziende.
 */
@Service
public class TypeConverter implements GenericConverter<Type, TypeDTO>{

    @Override
    public Type fromDtoToEntity (TypeDTO d)
    {
        Type t = new Type();
        t.setId(d.getId());
        t.setType(d.getType());
        return t;
    }
    
    @Override
    public TypeDTO fromEntityToDto (Type typ)
    {
        TypeDTO d = new TypeDTO();
        d.setId(typ.getId());
        d.setType(typ.getType());
        return d;
    }
}
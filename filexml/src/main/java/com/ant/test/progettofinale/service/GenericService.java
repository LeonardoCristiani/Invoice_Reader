package com.ant.test.progettofinale.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ant.test.progettofinale.converter.GenericConverter;
import com.ant.test.progettofinale.entity.Generic_Entity;

import lombok.Data;

@Data
public abstract class GenericService<E extends Generic_Entity, C extends GenericConverter<E, D>, R extends JpaRepository<E, TipoID>, D, TipoID>
{
    private final C converter;
    private final R repository;

    public List<D> findAll()
    { 
        List<E> entities = repository.findAll();
        List<D> dtos = new ArrayList<D>(entities.size());
        entities.forEach((entity) -> {
            dtos.add(converter.fromEntityToDto(entity));
        });
        return dtos;
    }

    public D findByID(TipoID id)
    {
        E entity = repository.findById(id).get();
        return converter.fromEntityToDto(entity);
    }

    public E saveInDB(E entity)
    {
        return repository.save(entity);
    }
}
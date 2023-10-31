package com.ant.test.progettofinale.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ant.test.progettofinale.converter.TypeConverter;
import com.ant.test.progettofinale.dto.TypeDTO;
import com.ant.test.progettofinale.entity.Type;
import com.ant.test.progettofinale.repository.TypeRepository;

@Service
public class TypeService extends GenericService<Type, TypeConverter, TypeRepository, TypeDTO, Long>
{
    public TypeService(TypeConverter converter, TypeRepository repository) 
    {
        super(converter, repository);
    }

    public List<TypeDTO> findByType(String type)
    {
        List<Type> types = getRepository().findByType(type);
        List<TypeDTO> dtos = new ArrayList<>(types.size());
        types.forEach((typeData) -> dtos.add(getConverter().fromEntityToDto(typeData)));
        return dtos;
    }
}
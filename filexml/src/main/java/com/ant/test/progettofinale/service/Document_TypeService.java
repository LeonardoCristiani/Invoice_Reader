package com.ant.test.progettofinale.service;

import org.springframework.stereotype.Service;

import com.ant.test.progettofinale.converter.Document_TypeConverter;
import com.ant.test.progettofinale.dto.DocumentTypeDTO;
import com.ant.test.progettofinale.entity.Document_Type;
import com.ant.test.progettofinale.repository.Document_TypeRepository;

@Service
public class Document_TypeService extends GenericService<Document_Type, Document_TypeConverter, Document_TypeRepository, DocumentTypeDTO, Long>
{
    public Document_TypeService(Document_TypeConverter converter, Document_TypeRepository repository) 
    {
        super(converter, repository);
    }

    public DocumentTypeDTO findByType (String type)
    {
        Document_Type document= getRepository().findByType(type);
        return getConverter().fromEntityToDto(document);
    }
}
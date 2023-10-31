package com.ant.test.progettofinale.converter;


import org.springframework.stereotype.Service;

import com.ant.test.progettofinale.dto.DocumentTypeDTO;
import com.ant.test.progettofinale.entity.Document_Type;

/**
 * Convertitore per i dati della tabella dei tipi di documenti.
 */
@Service
public class Document_TypeConverter implements GenericConverter<Document_Type, DocumentTypeDTO>
{
    
    @Override
    public Document_Type fromDtoToEntity (DocumentTypeDTO dto)
    {
        Document_Type doc = new Document_Type();
        doc.setId(dto.getId());
        doc.setType(dto.getType());
        return doc;
    }

    @Override
    public DocumentTypeDTO fromEntityToDto (Document_Type doc)
    {
        DocumentTypeDTO dto = new DocumentTypeDTO();
        dto.setId(doc.getId());
        dto.setType(doc.getType());
        return dto;
    }
}
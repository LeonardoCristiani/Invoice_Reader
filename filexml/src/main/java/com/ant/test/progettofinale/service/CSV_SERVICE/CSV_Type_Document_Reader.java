package com.ant.test.progettofinale.service.CSV_SERVICE;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ant.test.progettofinale.converter.Document_TypeConverter;
import com.ant.test.progettofinale.dto.DocumentTypeDTO;
import com.ant.test.progettofinale.entity.Document_Type;
import com.ant.test.progettofinale.service.Document_TypeService;

import lombok.Data;

/**
 * Legge i dati per la tabella document_type da CSV.
 */
@Service
@Data
public class CSV_Type_Document_Reader
{
    private final  Document_TypeService  service;
    private final Document_TypeConverter converter;
    
    /**
     * Legge e controlla i dati.
     * @param path Percorso del file CSV.
     * @return true in ogni caso.
     */
    public boolean type_Document_LOAD_CSV(String path)
    {
        return check(type_reader(path),service.findAll());
    }

    /**
     * Legge i dati dal file CSV.
     * @param path Percorso del file.
     * @return Istanze di {@link DocumentTypeDTO} risultato della lettura del file.
     */
    public List<DocumentTypeDTO> type_reader(String path)
    {
        List<DocumentTypeDTO> documentType_CSV= new ArrayList<>();
        Path file = Paths.get(path);
        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8))
        {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) 
            {    
                String[] value = line.split(",");
                DocumentTypeDTO document = new DocumentTypeDTO();
                document.setId(Long.parseLong(value[0]));
                document.setType(value[1]);
                documentType_CSV.add(document);
                line = br.readLine();
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return documentType_CSV;  
    }

    /**
     * Controlla se i dati letti già esistono.
     * @param list_CSV Istanze di {@link DocumentTypeDTO} create dalla lettura del file CSV.
     * @param list_DB Istanze di {@link DocumentTypeDTO} create dalla lettura dei dati nel database.
     * @return true in ogni caso.
     */
    //Questo metodo restituirà true in ogni caso.
    public boolean check(List<DocumentTypeDTO> list_CSV, List<DocumentTypeDTO> list_DB)
    {
        boolean ris = false;
        if(list_DB.size() != 0)
        {
            for (DocumentTypeDTO document_CSV :  list_CSV) 
            {
                boolean x = false;
                for (DocumentTypeDTO document_DB : list_DB) 
                {
                    if (document_DB.getType().equals(document_DB.getType()) && document_CSV.getId() == document_DB.getId())
                    {
                        x = true;
                    } 
                }
                if(x == false)
                {
                    Document_Type insert  = new Document_Type();
                    insert.setType(document_CSV.getType());
                    service.saveInDB(insert);
                }
            }
            ris = true;
        }
        else
        {
            for (DocumentTypeDTO document_CSV : list_CSV) 
            {
                Document_Type insert  = new Document_Type();
                insert.setType(document_CSV.getType());
                service.saveInDB(insert);    
            }
            ris = true;   
        }
        return ris;
    }
}   
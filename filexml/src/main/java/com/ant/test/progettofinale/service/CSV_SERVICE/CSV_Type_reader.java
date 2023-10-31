package com.ant.test.progettofinale.service.CSV_SERVICE;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.ant.test.progettofinale.converter.TypeConverter;
import com.ant.test.progettofinale.dto.TypeDTO;
import com.ant.test.progettofinale.entity.Type;
import com.ant.test.progettofinale.service.TypeService;

import lombok.Data;

/**
 * Legge i dati per la tabella type da CSV.
 */
@Service
@Data
public class CSV_Type_reader 
{
    private final TypeService typeService;
    private final TypeConverter typeConverter;
    
    /**
     * Legge e controlla i dati.
     * @param path Percorso del file CSV.
     * @return true in ogni caso.
     */
    public boolean type_LOAD_CSV(String path)
    {
        return check(type_reader(path) , typeService.findAll());
    }

    /**
     * Legge i dati dal file CSV.
     * @param path Percorso del file.
     * @return Istanze di {@link TypeDTO} risultato della lettura del file.
     */
    public List<TypeDTO> type_reader(String path)
    {
        List<TypeDTO> types_CSV= new ArrayList<>();
        Path file = Paths.get(path);
        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8))
        {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) 
            {    
                String[] value = line.split(",");
                TypeDTO type = new TypeDTO();
                type.setId(Long.parseLong(value[0]));
                type.setType(value[1]);
                types_CSV.add(type);
                line = br.readLine();
            }
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return types_CSV;  
    }

    /**
     * Controlla se i dati letti già esistono.
     * @param list_CSV Istanze di {@link TypeDTO} create dalla lettura del file CSV.
     * @param list_DB Istanze di {@link TypeDTO} create dalla lettura dei dati nel database.
     * @return true in ogni caso.
     */
    //Questo metodo restituirà true in ogni caso.
    public boolean check(List<TypeDTO> types_CSV, List<TypeDTO> types_DB)
    {
        boolean ris = false;
        if(types_DB.size() != 0)
        {
            for (TypeDTO type_CSV : types_CSV) 
            {
                boolean x = false;
                for (TypeDTO type_DB : types_DB) 
                {
                    if (type_CSV.getType().equals(type_DB.getType()))
                    {
                        x = true;
                    } 
                }
                if(x == false)
                {
                    Type insert  = new Type();
                    insert.setType(type_CSV.getType());
                    typeService.saveInDB(insert);
                }
            }
            ris = true;
        }
        else
        {
            for (TypeDTO type_CSV : types_CSV) 
            {
                Type insert  = new Type();
                insert.setType(type_CSV.getType());
                typeService.saveInDB(insert);     
            }
            ris = true;   
        }
        return ris;
    }   
}
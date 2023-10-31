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

import com.ant.test.progettofinale.dto.AssociationDTO;
import com.ant.test.progettofinale.entity.Association_Table;
import com.ant.test.progettofinale.service.Service_controller.Service_Controller;

import lombok.Data;

/**
 * Legge i dati per la tabella association_table da CSV.
 */
@Service
@Data
public class CSV_Association_Reader 
{
    private final Service_Controller service_Controller;

    /**
     * Legge e controlla i dati.
     * @param path Percorso del file CSV.
     * @return true in ogni caso.
     */
    public boolean Association_LOAD_CSV(String path)
    {
        return check(type_reader(path),service_Controller.getAssociation_TableService().findAll());
    }

    /**
     * Legge i dati dal file CSV.
     * @param path Percorso del file.
     * @return Istanze di {@link AssociationDTO} risultato della lettura del file.
     */
    public List<AssociationDTO> type_reader(String path)
    {
        List<AssociationDTO> tableType_CSV= new ArrayList<>();
        Path file = Paths.get(path);
        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8))
        {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) 
            {    
                String[] value = line.split(",");
                AssociationDTO table = new AssociationDTO();
                table.setId(Long.parseLong(value[0]));
                table.setId_Company(Long.parseLong(value[1]));
                table.setId_type(Long.parseLong(value[2]));
                tableType_CSV.add(table);
                line = br.readLine();
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return tableType_CSV;  
    }

    /**
     * Controlla se i dati letti già esistono.
     * @param list_CSV Istanze di {@link AssociationDTO} create dalla lettura del file CSV.
     * @param list_DB Istanze di {@link AssociationDTO} create dalla lettura dei dati nel database.
     * @return true in ogni caso.
     */
    //Questo metodo restituirà true in ogni caso.
    public boolean check(List<AssociationDTO> list_CSV, List<AssociationDTO> list_DB)
    {
        boolean ris = false;
        if(list_DB.size() != 0)
        {
            for (AssociationDTO table_CSV :  list_CSV) 
            {
                boolean x = false;
                for (AssociationDTO table_DB : list_DB) 
                {
                    if (table_CSV.getId() == table_DB.getId())
                    {
                        x = true;
                    } 
                }
                if(x == false)
                {
                    Association_Table table = service_Controller.getAssociation_TableService().getConverter().fromDtoToEntity(table_CSV);
                    service_Controller.getAssociation_TableService().saveInDB(table);
                }
            }
            ris = true;
        }
        else
        {
            for (AssociationDTO table_CSV : list_CSV) 
            {
                Association_Table table = service_Controller.getAssociation_TableService().getConverter().fromDtoToEntity(table_CSV);
                service_Controller.getAssociation_TableService().saveInDB(table);
            }
            ris = true;   
        }
        return ris;
    }
}
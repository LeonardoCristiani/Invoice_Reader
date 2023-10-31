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

import com.ant.test.progettofinale.converter.CompanyConverter;
import com.ant.test.progettofinale.dto.CompanyDTO;
import com.ant.test.progettofinale.entity.Company;
import com.ant.test.progettofinale.service.CompanyService;
import com.ant.test.progettofinale.service.Service_controller.Service_Controller;

import lombok.Data;

/**
 * Legge i dati per la tabella companies da CSV.
 */
@Service
@Data
public class CSV_Company_reader
{
    private final CompanyService service;
    private final CompanyConverter converter;
    private final Service_Controller service_Controller;

    /**
     * Legge e controlla i dati.
     * @param path Percorso del file CSV.
     * @return true in ogni caso.
     */
    public boolean Company_LOAD_CSV(String path)
    {  
        return check(company_reader(path), service.findAll());
    }

    /**
     * Legge i dati dal file CSV.
     * @param path Percorso del file.
     * @return Istanze di {@link CompanyDTO} risultato della lettura del file.
     */
    public List<CompanyDTO> company_reader(String path)
    {
        List<CompanyDTO> companies= new ArrayList<>();
        Path file = Paths.get(path);
        
        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8))
        {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) 
            {    
                String[] value = line.split(",");
                CompanyDTO company = new CompanyDTO();
                company.setId(Long.parseLong(value[0]));
                company.setName(value[1]);
                company.setAddress(value[2]);
                company.setVat_Number(value[3]);
                companies.add(company);
                line = br.readLine();
            }
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return companies;  
    }
    
    /**
     * Controlla se i dati letti già esistono.
     * @param list_CSV Istanze di {@link CompanyDTO} create dalla lettura del file CSV.
     * @param list_DB Istanze di {@link CompamyDTO} create dalla lettura dei dati nel database.
     * @return true in ogni caso.
     */
    //Questo metodo restituirà true in ogni caso.
    public boolean check(List<CompanyDTO> list_CSV, List<CompanyDTO> list_DB)
    {
        boolean ris = false;
        if(list_DB.size() != 0)
        {
            //int i = 0;
            for (CompanyDTO item_CSV : list_CSV) 
            {
                boolean x = false;
                for (CompanyDTO item_DB : list_DB) 
                {
                    if (item_CSV.getName().equals(item_DB.getName()))
                    {
                        x = true;
                    } 
                }
                if(x == false)
                {
                    Company insert  = new Company();
                    insert.setId(item_CSV.getId());
                    insert.setName(item_CSV.getName());
                    insert.setAddress(item_CSV.getAddress());
                    insert.setVat_number(item_CSV.getVat_Number());
                    service.saveInDB(insert);
                     
                }
            }
            ris = true;
        }
        else
        {
            // int i = 0;
            for (CompanyDTO item_CSV : list_CSV) 
            {
                Company insert  = new Company();
                insert.setId(item_CSV.getId());
                insert.setName(item_CSV.getName());
                insert.setAddress(item_CSV.getAddress());
                insert.setVat_number(item_CSV.getVat_Number());
                service.saveInDB(insert);  
            
            }  
        }
        return ris;
    }
}
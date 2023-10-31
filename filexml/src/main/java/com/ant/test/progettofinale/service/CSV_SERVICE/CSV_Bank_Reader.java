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

import com.ant.test.progettofinale.converter.BankConverter;
import com.ant.test.progettofinale.dto.BankDTO;
import com.ant.test.progettofinale.entity.Bank;
import com.ant.test.progettofinale.service.BankService;

import lombok.Data;

/**
 * Legge i dati per la tabella banks da CSV.
 */
@Service
@Data
public class CSV_Bank_Reader 
{
    private final BankService bankService;
    private final BankConverter bankConverter;
    
    /**
     * Legge e controlla i dati.
     * @param path Percorso del file CSV.
     * @return true in ogni caso.
     */
    public boolean bank_LOAD_CSV(String path)
    {
        return check(bank_reader(path), bankService.findAll());
    }

    /**
     * Legge i dati dal file CSV.
     * @param path Percorso del file.
     * @return Istanze di {@link BankDTO} risultato della lettura del file.
     */
    public List<BankDTO> bank_reader(String path)
    {
        List<BankDTO> banks_CSV= new ArrayList<>();
        Path file = Paths.get(path);
        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8))
        {
            String line = br.readLine();
            System.out.println(line);
            line = br.readLine();
            while (line != null) 
            {    
                String[] value = line.split(",");
                BankDTO bank = new BankDTO();
                bank.setId(Long.parseLong(value[0]));
                bank.setName(value[1]);
                banks_CSV.add(bank);
                line = br.readLine();
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return banks_CSV;  
    }

    /**
     * Controlla se i dati letti già esistono.
     * @param list_CSV Istanze di {@link BankDTO} create dalla lettura del file CSV.
     * @param list_DB Istanze di {@link BankDTO} create dalla lettura dei dati nel database.
     * @return true in ogni caso.
     */
    //Questo metodo restituirà true in ogni caso.
    public boolean check(List<BankDTO> item_CSV, List<BankDTO> item_DB)
    {
        boolean ris = false;
        if(item_DB.size() != 0)
        {
            for (BankDTO type_CSV : item_CSV) 
            {
                boolean x = false;
                for (BankDTO type_DB : item_DB) 
                {
                    if (type_CSV.getName().equals(type_DB.getName()))
                    {
                        x = true;
                    } 
                }
                if(x == false)
                {
                    Bank insert  = new Bank();
                    insert.setName(type_CSV.getName());
                    bankService.saveInDB(insert);
                }
            }
            ris = true;
        }
        else
        {
            for (BankDTO type_CSV : item_CSV) 
            {
                Bank insert  = new Bank();
                insert.setName(type_CSV.getName());
                bankService.saveInDB(insert);;      
            }  
        }
        return ris;
    }
}
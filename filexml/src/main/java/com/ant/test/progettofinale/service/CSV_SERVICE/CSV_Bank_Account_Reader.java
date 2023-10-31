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
import com.ant.test.progettofinale.converter.Bank_AccountConverter;
import com.ant.test.progettofinale.converter.CompanyConverter;
import com.ant.test.progettofinale.dto.BankDTO;
import com.ant.test.progettofinale.dto.Bank_AccountDTO;
import com.ant.test.progettofinale.dto.CompanyDTO;
import com.ant.test.progettofinale.entity.Bank_Account;
import com.ant.test.progettofinale.service.BankService;
import com.ant.test.progettofinale.service.Bank_AccountService;
import com.ant.test.progettofinale.service.CompanyService;

import lombok.Data;

/**
 * Legge i dati per la tabella banks da CSV.
 */
@Service
@Data
public class CSV_Bank_Account_Reader
{
    private final Bank_AccountService service;
    private final Bank_AccountConverter converter;
    private final BankService bankService;
    private final BankConverter bankConverter;
    private final CompanyConverter companyConverter;
    private final CompanyService companyService;
    
    /**
     * Legge e controlla i dati.
     * @param path Percorso del file CSV.
     * @return true in ogni caso.
     */
    public boolean bank_account_LOAD_CSV(String path)
    {
        return check(bank_reader(path), service.findAll());
    }

    /**
     * Legge i dati dal file CSV.
     * @param path Percorso del file.
     * @return Istanze di {@link Bank_AccountDTO} risultato della lettura del file.
     */
    public List<Bank_AccountDTO> bank_reader(String path)
    {
        List<Bank_AccountDTO> bank_accounts= new ArrayList<>();
        Path file = Paths.get(path);
        try (BufferedReader br = Files.newBufferedReader(file, StandardCharsets.UTF_8))
        {
            
            String line = br.readLine();
            line = br.readLine();
            
            while (line != null) 
            {   
                
                String[] value = line.split(",");
                Bank_AccountDTO bank_account = new Bank_AccountDTO();
                bank_account.setId(Long.parseLong(value[0]));
                bank_account.setIban(value[1]);
                bank_account.setBank_id(Long.parseLong(value[2]));
                bank_account.setCompany_id(Long.parseLong(value[3]));
                bank_account.setMax_value(Double.parseDouble(value[4]));
                bank_accounts.add(bank_account);
                line = br.readLine();
            }
            
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return bank_accounts;  
    }

    /**
     * Controlla se i dati letti già esistono.
     * @param list_CSV Istanze di {@link Bank_AccountDTO} create dalla lettura del file CSV.
     * @param list_DB Istanze di {@link Bank_AccountDTO} create dalla lettura dei dati nel database.
     * @return true in ogni caso.
     */
    //Questo metodo restituirà true in ogni caso.
    public boolean check(List<Bank_AccountDTO> list_CSV, List<Bank_AccountDTO> list_DB)
    {   
        
        boolean ris = false;
        if(list_DB.size() != 0)
        {
            for (Bank_AccountDTO item_CSV : list_CSV) 
            {
                boolean x = false;
                for (Bank_AccountDTO item_DB : list_DB) 
                {
                    if (item_CSV.getIban().equals(item_DB.getIban()))
                    {
                        x = true;
                    } 
                }
                if(x == false)
                {
                    Bank_Account insert  = new Bank_Account();
                    insert.setId(item_CSV.getId());
                    insert.setIban(item_CSV.getIban());
                    insert.setMax_value(item_CSV.getMax_value());
                    
                    BankDTO bank = bankService.findByID(item_CSV.getBank_id());
                    insert.setBank(bankConverter.fromDtoToEntity(bank));
                    
                    CompanyDTO company = companyService.findByID(item_CSV.getCompany_id());
                    insert.setCompany(companyConverter.fromDtoToEntity(company));
                    service.saveInDB(insert);
                }
            }
            ris = true;
        }
        else
        {
            for (Bank_AccountDTO item_CSV : list_CSV) 
            {
                Bank_Account insert  = new Bank_Account();
                insert.setId(item_CSV.getId());
                insert.setIban(item_CSV.getIban());
                insert.setMax_value(item_CSV.getMax_value());

                BankDTO bank = bankService.findByID(item_CSV.getBank_id());
                insert.setBank(bankConverter.fromDtoToEntity(bank));

                CompanyDTO company = companyService.findByID(item_CSV.getCompany_id());
                insert.setCompany(companyConverter.fromDtoToEntity(company));

                service.saveInDB(insert);     
            }  
        }
        return ris;
    }   
}
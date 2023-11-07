package com.ant.test.progettofinale.service.CSV_CONTROLLER;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;


import com.ant.test.progettofinale.service.CSV_SERVICE.CSV_Association_Reader;
import com.ant.test.progettofinale.service.CSV_SERVICE.CSV_Bank_Account_Reader;
import com.ant.test.progettofinale.service.CSV_SERVICE.CSV_Bank_Reader;
import com.ant.test.progettofinale.service.CSV_SERVICE.CSV_Company_reader;
import com.ant.test.progettofinale.service.CSV_SERVICE.CSV_Type_Document_Reader;
import com.ant.test.progettofinale.service.CSV_SERVICE.CSV_Type_reader;

import lombok.Data;

/**
 * Gestisce la lettura dei file CSV relativi ai tipi di aziende, alle banche, alle aziende e ai tipi di documenti.
 */
@Service
@Data
public class CSV_CONTROLLER 
{
    @Value("${CSV_CONTROOLER_MENAGER_ADD_DATA_TO_DB}")
    private String activator;
    /**
     * Lettore del file CSV per i tipi di aziende.
     */
    private final CSV_Type_reader CSV_Type_reader;
    /**
     * Lettore del file CSV per le banche.
     */
    private final CSV_Bank_Reader CSV_Bank_Reader;
    /**
     * Lettore del file CSV per le aziende.
     */
    private final CSV_Company_reader CSV_Company_reader;
    /**
     * Lettore del file CSV per i conti correnti.
     */
    private final CSV_Bank_Account_Reader CSV_Bank_Account_Reader;
    /**
     * Lettore del file CSV per i tipi di documenti.
     */
    private final CSV_Type_Document_Reader CSV_Type_Document_Reader;
    /**
     * Lettore del file CSV per i dati della tabella di associazione tra aziende e tipi.
     */
    private final CSV_Association_Reader csv_Association_Reader;

    /**
     * Legge i file CSV.
     */
    public void CSV_START_UP()
    {
        if (CSV_Type_reader.type_LOAD_CSV("../filexml/CSV/Type.csv"))
            System.out.println("type load");
        if(CSV_Bank_Reader.bank_LOAD_CSV("../filexml/CSV/bank.csv"))
            System.out.println("bank load");

        if(CSV_Company_reader.Company_LOAD_CSV("../filexml/CSV/Company.csv"))
            System.out.println("company load");

        if(CSV_Bank_Account_Reader.bank_account_LOAD_CSV("../filexml/CSV/Bank_Account.csv"))
            System.out.println("bank_account load");

        if(CSV_Type_Document_Reader.type_Document_LOAD_CSV("../filexml/CSV/Type_Document.csv"))
            System.out.println("type load ");
        if(csv_Association_Reader.Association_LOAD_CSV("../filexml/CSV/Association_table.csv"));
            System.out.println("association_table ");
    }
    

     @EventListener(ApplicationReadyEvent.class)
     public void CSW_ACTIVATOR()
     {
        if(activator.equals("true"))
            CSV_START_UP();
     }
}
package com.ant.test.progettofinale.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;

import com.ant.test.progettofinale.entity.Invoice;
import com.ant.test.progettofinale.entity.InvoiceXML;
import com.ant.test.progettofinale.service.Service_controller.Service_Controller;

import lombok.Data;

@Service
@Data
public class Gestione_file 
{
    private  final Service_Controller service_Controller ;
    public static String path="/Users/leonardocristiani/Desktop/fatturaletta";

    public   Invoice check_Bank_Account(InvoiceXML invoiceXML,Invoice invoice, String xmlPath) 
    {
        try 
        {
            invoice.setBank_account(service_Controller.getBank_AccountService().getConverter().fromDtoToEntity(service_Controller.getBank_AccountService().findByIban(invoiceXML.getPagamento().getDettaglioPagamento().get(0).getIBAN())));
            return invoice;
        }
            catch (NullPointerException e) 
        {
            try
            {
             invoice.setBank_account(service_Controller.getBank_AccountService().getConverter().fromDtoToEntity(service_Controller.getBank_AccountService().findByID(1L)));
             return invoice ;
            }
             catch (NullPointerException e1) 
            {
                Path sourcePath = Paths.get(xmlPath);
                Path destinationPath = Paths.get(path + "/errore/iban/" + sourcePath.getFileName());
                try 
                {
                    Files.move(sourcePath, destinationPath, StandardCopyOption.ATOMIC_MOVE);
                } 
                catch (IOException e2) 
                {
                    e2.printStackTrace();
                }
            } 
        }
        return null;
    }

    public Invoice check_Reciver(InvoiceXML invoiceXML , Invoice invoice, String xmlPath) 
    {
        
        try{
            invoice.setReceiver(service_Controller.getCompanyService().getRepository().findByVatNumber(invoiceXML.getCommittente().getDatiAnagrafici().getIdFiscaleIVA().getIdCodice()));
            return invoice;
        }
        catch(NullPointerException e)
        {
            invoice.setReceiver(service_Controller.getCompanyService().getRepository().findByVatNumber(invoiceXML.getCommittente().getDatiAnagrafici().getCodiceFiscale() ));
            if(invoice.getReceiver() == null)
            {
                try
                {
                    Path sourcePath = Paths.get(xmlPath);
                    Path destinationPath = Paths.get(path + "/errore/MISSING_receiver/" + sourcePath.getFileName());
                    Files.move(sourcePath, destinationPath, StandardCopyOption.ATOMIC_MOVE);
                }
                
                catch (IOException iE) 
                {
                    iE.printStackTrace();
                }
                return null;
            }
            return invoice;

        } 
        
    }

    public Invoice check_Sender(InvoiceXML invoiceXML , Invoice invoice, String xmlPath) 
    {
        try
        {
            invoice.setSender(service_Controller.getCompanyService().getRepository().findByVatNumber(invoiceXML.getPrestatore().getDatiAnagrafici().getIdFiscaleIVA().getIdCodice()));
            if(invoice.getSender() == null )
            {
                try
                { 
                invoice.setSender(service_Controller.getCompanyService().getRepository().findByVatNumber(invoiceXML.getPrestatore().getDatiAnagrafici().getCodiceFiscale()));
                if(invoice.getSender() == null)
                {
                    try
                    {
                        System.out.println(" sender \n---- \n"+invoiceXML.getPrestatore().getDatiAnagrafici().getAnagrafica().getDenominazione() + "," +
                                            invoiceXML.getPrestatore().getSede().getIndirizzo() +","+
                                            invoiceXML.getPrestatore().getDatiAnagrafici().getIdFiscaleIVA().getIdCodice()+"\n"+
                                            "\n-----");
                        System.out.println( "errore");
                        Path sourcePath = Paths.get(xmlPath);
                        Path destinationPath = Paths.get(path + "/errore/MISSING_sender/" + sourcePath.getFileName());
                        Files.move(sourcePath, destinationPath, StandardCopyOption.ATOMIC_MOVE);
                        return null;
                    }
                    catch (IOException iE) 
                    {
                        iE.printStackTrace();
                    }
                }                                                                                                                                                                                                                                                                                             
                return invoice ;
                }
                catch(Exception   ex)
                {
                    ex.printStackTrace();
                }
            }
            return invoice;
        }
        catch (NullPointerException e)
        {            
           e.printStackTrace();
        }
        return null;
    }

    public boolean check_Duplicate(InvoiceXML invoiceXML , Invoice invoice , String xmlPath) {
        try {
            service_Controller.getInvoiceService().findByInvoiceNumberAndSenderAndReceiverAndWasReversed(
                    invoiceXML.getDati_generali().getNumero(),
                    invoice.getSender(),
                    invoice.getReceiver(), 
                    false);
            // Se esiste una fattura duplicata, sposta il file XML nella cartella degli errori "duplicate" e continua con la prossima iterazione.
            try {
                Files.move(Paths.get(xmlPath), Paths.get(path + "/errore/duplicate/" + Paths.get(xmlPath).getFileName()), StandardCopyOption.ATOMIC_MOVE);
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            return false;

        } catch (NullPointerException e) {
            // Se non esiste una fattura duplicata, procedi con l'elaborazione.
            return true;
        }
    }

    public Invoice check_Type_Document(InvoiceXML invoicexml,Invoice invoice ,String xmlPath )
    {
        try 
        {
            invoice.setType(service_Controller.getDocument_TypeService().getConverter().fromDtoToEntity( service_Controller.getDocument_TypeService().findByType(invoicexml.getDati_generali().getTipoDocumento().toString())));
            return invoice;
        } 
        catch (NullPointerException e) 
        {
            Path sourcePath = Paths.get(xmlPath);
            Path destinationPath = Paths.get(path + "/errore/td/" + sourcePath.getFileName());
            try 
            {
                Files.move(sourcePath, destinationPath, StandardCopyOption.ATOMIC_MOVE);
            } 
            catch (IOException e1)
            {
                e1.printStackTrace();
            }
        }
        return null;
    }
    
}

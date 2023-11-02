package com.ant.test.progettofinale.refreshtasks;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.UnmarshalException;
import jakarta.xml.bind.Unmarshaller;

import java.nio.file.Path;
import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ant.test.progettofinale.entity.Invoice;
import com.ant.test.progettofinale.entity.InvoiceXML;
import com.ant.test.progettofinale.generated.DatiGeneraliDocumentoType;
import com.ant.test.progettofinale.generated.FatturaElettronicaType;
import com.ant.test.progettofinale.invioce_file.FileSystem;
import com.ant.test.progettofinale.service.Gestione_file;
import com.ant.test.progettofinale.service.Service_controller.Service_Controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

@Component
@Data
public class DbRefresher 
{
    @Value("${File_Destinazione_Invoice_Lette}")
    private final String PATH ;
    private final Service_Controller service_Controller;
    private final Gestione_file gestione_file;

    @Scheduled(cron = "*/30 * * * * *")
    public void reportCurrentTime() throws JAXBException, jakarta.xml.bind.JAXBException, IOException 
    {
        int i = 0;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try 
        {
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            try (DirectoryStream<Path> files = Files.newDirectoryStream(Paths.get("../filexml/filexml/fatture_clienti"))) 
            {
                JAXBContext jc = JAXBContext.newInstance(FatturaElettronicaType.class);
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                for (Path file : files) 
                {
                    i++;
                    File xml = new File(file+"");
                    System.out.println(xml.getName());
                    try
                    {
                        JAXBElement<FatturaElettronicaType> tests = (JAXBElement<FatturaElettronicaType>) unmarshaller.unmarshal(xml);
                        FatturaElettronicaType fattura = tests.getValue();
                        InvoiceXML invoicexml = new InvoiceXML();
                        invoicexml.Separator(fattura);
                        Invoice invoice = new Invoice();
                        System.out.println("tradotta");
                        if(fattura.getFatturaElettronicaHeader().getDatiTrasmissione().getPECDestinatario()!= null)
                        {
                            //Files.delete(file)
                             Files.move(Paths.get(xml.getPath()),Paths.get(PATH+"/errore/malformate/"+xml.getName()) , StandardCopyOption.ATOMIC_MOVE);

                            continue;
                        }
                        invoice = gestione_file.check_Sender(invoicexml ,invoice , xml.getPath());
                        if(invoice == null)
                        {
                            continue;
                        }
                        System.out.println("fine sender ");

                        invoice = gestione_file.check_Reciver(invoicexml,invoice , xml.getPath());
                        if(invoice == null)
                        {
                             System.err.println(invoicexml.getCommittente().getDatiAnagrafici().getAnagrafica().getDenominazione());
                            continue;
                        }
                        System.out.println("fine reciver ");

                        if(!(invoicexml.getPagamento()==null))
                        {
                            invoice = gestione_file.check_Bank_Account(invoicexml , invoice , xml.getPath());
                            if(invoice == null)
                            {
                                continue;
                            }
                        }
                        else
                        {
                            System.out.println("kkkkkkk");
                            invoice.setCollectionDate(LocalDate.parse(invoicexml.getDati_generali().getData().toString()));
                            try
                            {
                            invoice.setBank_account(service_Controller.getBank_AccountService().getRepository().findBank_AccountsByIdcodice(invoice.getSender().getVat_number()).get(0));
                            }
                            catch(IndexOutOfBoundsException x)
                            {
                                invoice.setBank_account(service_Controller.getBank_AccountService().getRepository().findById(1L).get());
                            }
                        }
                        System.out.println("fine bank_account ");

                        invoice = gestione_file.check_Type_Document(invoicexml , invoice , xml.getPath());
                        if(invoice == null)
                        {
                            continue;
                        }
                        System.out.println("fine document type ");

                        if(gestione_file.check_Duplicate(invoicexml , invoice , xml.getPath() ))
                        {
                            invoice.setInvoiceNumber(invoicexml.getDati_generali().getNumero());
                            invoice.setDescription(invoicexml.getBeniServizi().getDettaglioLinee().get(0).getDescrizione());//causale
                            try
                            {
                                invoice.setExpirationDate(LocalDate.parse(invoicexml.getPagamento().getDettaglioPagamento().get(0).getDataScadenzaPagamento().toString()));//scadenza fattura
                            }
                            catch ( NullPointerException nullo)
                            {
                                try
                                {
                                    invoice.setExpirationDate(LocalDate.parse(invoicexml.getPagamento().getDettaglioPagamento().get(0).getDataRiferimentoTerminiPagamento().toString()));//scadenza fattura

                                }
                                catch  ( NullPointerException nullo2)
                                {
                                    invoice.setExpirationDate(LocalDate.now());//scadenza fattura
                                }
                            }
                            invoice.setIssueDate(LocalDate.parse(invoicexml.getDati_generali().getData().toString()));//data di emissione fattura
                            invoice.setIncome(Double.parseDouble(invoicexml.getRiepilogo().getImponibileImporto().toString()));//importo
                            invoice.setVat(Double.parseDouble(invoicexml.getRiepilogo().getImposta().toString()));//tasse
                            invoice.setRetentionTax(0.0);//ritenuta
                            invoice.setWasAdvanced(false);//anticipato
                            invoice.setWasReversed(false);//stornato
                            service_Controller.getInvoiceService().saveInDB(invoice); 
                         
                        }else
                        {
                            continue;
                        }
                        try
                        {
                            service_Controller.getCompanyService().findByTypeAndVat_Number("INTERNO", invoice.getSender().getVat_number());
                            DatiGeneraliDocumentoType  data_info = fattura.getFatturaElettronicaBody().get(0).getDatiGenerali().getDatiGeneraliDocumento();
                            String path = FileSystem.check_Directory(PATH, invoice.getSender(), data_info);
                            if(!path.equals(""))
                            {                     
                                Files.copy(Paths.get(xml.getPath()), Paths.get(path +"/"+ xml.getName()) , StandardCopyOption.REPLACE_EXISTING); 
                            } 
                        }
                        catch(NullPointerException e)
                        {
                            System.out.println("Value null sender ");
                        }
                                // Se passa lo sposta
                        try
                        {
                            service_Controller.getCompanyService().findByTypeAndVat_Number("INTERNO", invoice.getReceiver().getVat_number());
                            DatiGeneraliDocumentoType  data_info = fattura.getFatturaElettronicaBody().get(0).getDatiGenerali().getDatiGeneraliDocumento();
                            String path = FileSystem.check_Directory(PATH, invoice.getReceiver(), data_info);
                            if(!path.equals(""))
                            {                     
                                Files.move(Paths.get(xml.getPath()), Paths.get(path +"/"+ xml.getName()), StandardCopyOption.ATOMIC_MOVE);
                            } 
                        }
                        catch(NullPointerException e)
                        {
                            //se passa lo cancella
                            System.out.println(e);
                            System.out.println("Value null receiver ");
                            Files.delete(xml.toPath());
                            continue;
                        }
                       

                    }
                    catch (IndexOutOfBoundsException e)
                    {
                        System.err.println(e);
                        System.out.println(xml.getPath());
                        Files.move(Paths.get(xml.getPath()),Paths.get(PATH+"/errore/altre_fatture/"+xml.getName()) , StandardCopyOption.ATOMIC_MOVE);
                    }  
                    catch (UnmarshalException ex)
                    {
                        Files.move(Paths.get(xml.getPath()),Paths.get(PATH+"/errore/malformate/"+xml.getName()) , StandardCopyOption.ATOMIC_MOVE);

                    }
                }    
            }
        }
        catch (ParserConfigurationException e) 
        {
            //errore
            e.printStackTrace();
            System.out.println(e);
        }
        
        finally
        {
            System.out.println("----------------------------- ");
            System.out.println("finito esecuzione " + i );
            System.out.println("----------------------------- ");
        } 
    }

}
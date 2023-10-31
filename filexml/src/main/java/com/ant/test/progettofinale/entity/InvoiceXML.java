package com.ant.test.progettofinale.entity;

import com.ant.test.progettofinale.generated.CedentePrestatoreType;
import com.ant.test.progettofinale.generated.CessionarioCommittenteType;
import com.ant.test.progettofinale.generated.DatiBeniServiziType;
import com.ant.test.progettofinale.generated.DatiGeneraliDocumentoType;
import com.ant.test.progettofinale.generated.DatiPagamentoType;
import com.ant.test.progettofinale.generated.DatiRiepilogoType;
import com.ant.test.progettofinale.generated.FatturaElettronicaType;

import lombok.Data;

@Data
public class InvoiceXML 
{
    //header
    // prestatore 
    private CedentePrestatoreType prestatore;
    // committente 
    private CessionarioCommittenteType committente;
    // dati denerali
    private DatiGeneraliDocumentoType dati_generali ;
    private DatiRiepilogoType riepilogo ;
    // dati pagamento 
    private DatiPagamentoType pagamento;
    // dati bene e servizzi 
    private DatiBeniServiziType beniServizi;
    
    // dati 

    public void Separator(FatturaElettronicaType fattura)
    {
        //header 
        
        // prestatore 
        this.prestatore =  fattura.getFatturaElettronicaHeader().getCedentePrestatore();
        
        // committente 
        this.committente = fattura.getFatturaElettronicaHeader().getCessionarioCommittente();
        //body
        
        // dati denerali
        this.dati_generali = fattura.getFatturaElettronicaBody().get(0).getDatiGenerali().getDatiGeneraliDocumento();
        
        // dati riepilogo 
        this.riepilogo = fattura.getFatturaElettronicaBody().get(0).getDatiBeniServizi().getDatiRiepilogo().get(0);
       
        // dati pagamento 
        try
        {
            this.pagamento = fattura.getFatturaElettronicaBody().get(0).getDatiPagamento().get(0);
        }
        catch ( IndexOutOfBoundsException e)
        {
            System.out.println("pagamento nullo ");
        }
        // beni e servizzi 
        this.beniServizi= fattura.getFatturaElettronicaBody().get(0).getDatiBeniServizi();
    }

}

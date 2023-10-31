package com.ant.test.progettofinale.converter;

import org.springframework.stereotype.Service;

import com.ant.test.progettofinale.dto.InvoiceDTO;
import com.ant.test.progettofinale.entity.Invoice;

/**
 * Convertitore per i dati delle fatture.
 */
@Service
public class InvoiceConverter implements GenericConverter<Invoice, InvoiceDTO> {

    @Override
    public Invoice fromDtoToEntity(InvoiceDTO d)
    {
        Invoice i = new Invoice();

        i.setIncome(d.getIncome());
        i.setInvoiceNumber(d.getInvoiceNumber());
        i.setVat(d.getVat());
        i.setRetentionTax(d.getRetention_Tax());
        i.setDescription(d.getDescription());
        i.setIssueDate(d.getIssue_Date());
        i.setExpirationDate(d.getExpiration_Date());
        i.setCollectionDate(d.getCollection_Date());
        i.setWasAdvanced(d.isAdvanced());
        i.setWasReversed(d.isReversed());

        return i;
    }

    @Override
    public InvoiceDTO fromEntityToDto(Invoice i)
    {
        InvoiceDTO d = new InvoiceDTO();

        d.setIncome(i.getIncome());
        d.setInvoiceNumber(i.getInvoiceNumber());
        d.setVat(i.getVat());
        d.setRetention_Tax(i.getRetentionTax());
        d.setDescription(i.getDescription());
        d.setIssue_Date(i.getIssueDate());
        d.setExpiration_Date(i.getExpirationDate());
        d.setCollection_Date(i.getCollectionDate());
        d.setAdvanced(i.isWasAdvanced());
        d.setReversed(i.isWasReversed());

        return d;
    }
}
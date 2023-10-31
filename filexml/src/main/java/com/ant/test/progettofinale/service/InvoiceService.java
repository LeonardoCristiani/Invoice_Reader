package com.ant.test.progettofinale.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ant.test.progettofinale.converter.InvoiceConverter;
import com.ant.test.progettofinale.dto.InvoiceDTO;
import com.ant.test.progettofinale.entity.Company;
import com.ant.test.progettofinale.entity.Invoice;
import com.ant.test.progettofinale.repository.InvoiceRepository;

@Service
public class InvoiceService extends GenericService<Invoice, InvoiceConverter, InvoiceRepository, InvoiceDTO, Long>
{
    public InvoiceService(InvoiceConverter converter, InvoiceRepository repository) 
    {
        super(converter, repository);
    }

    public List<InvoiceDTO> findByInvoiceNumber(String number)
    {
        List<Invoice> invoices = getRepository().findByInvoiceNumber(number);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }

    public List<InvoiceDTO> findByDocumentType(long typeId)
    {
        List<Invoice> invoices = getRepository().findByTypeDocument(typeId);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }

    public List<InvoiceDTO> findBySender(long senderId)
    {
        List<Invoice> invoices = getRepository().findSenderCompany(senderId);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }

    public List<InvoiceDTO> findByIncome(double income)
    {
        List<Invoice> invoices = getRepository().findByIncome(income);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }

    public List<InvoiceDTO> findByVat(double vat)
    {
        List<Invoice> invoices = getRepository().findByVat(vat);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }

    public List<InvoiceDTO> findByRetentionTax(double retentionTax)
    {
        List<Invoice> invoices = getRepository().findByRetentionTax(retentionTax);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }

    public List<InvoiceDTO> findByCasual(String causal)
    {
        List<Invoice> invoices = getRepository().findByDescription(causal);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }

    public List<InvoiceDTO> findByIssueDate(LocalDate date)
    {
        List<Invoice> invoices = getRepository().findByIssueDate(date);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }

    public List<InvoiceDTO> findBeforeIssueDate(LocalDate date)
    {
        List<Invoice> invoices = getRepository().findByIssueDate(date);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }

    public List<InvoiceDTO> findAfterIssueDate(LocalDate date)
    {
        List<Invoice> invoices = getRepository().findAfterIssueDate(date);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }

    public List<InvoiceDTO> findByExpirationDate(LocalDate date)
    {
        List<Invoice> invoices = getRepository().findByExpirationDate(date);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }

    public List<InvoiceDTO> findBeforeExpirationDate(LocalDate date)
    {
        List<Invoice> invoices = getRepository().findBeforeExpirationDate(date);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }

    public List<InvoiceDTO> findAfterExpirationDate(LocalDate date)
    {
        List<Invoice> invoices = getRepository().findAfterExpirationDate(date);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }

    public List<InvoiceDTO> findByCollectionDate(LocalDate date)
    {
        List<Invoice> invoices = getRepository().findByCollectionDate(date);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }

    public List<InvoiceDTO> findBeforeCollectionDate(LocalDate date)
    {
        List<Invoice> invoices = getRepository().findBeforeCollectionDate(date);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }

    public List<InvoiceDTO> findAfterCollectionDate(LocalDate date)
    {
        List<Invoice> invoices = getRepository().findAfterCollectionDate(date);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }

    public List<InvoiceDTO> findByAdvanced(boolean advanced)
    {
        List<Invoice> invoices = getRepository().findByWasAdvanced(advanced);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }

    public List<InvoiceDTO> findByReserved(boolean reversed)
    {
        List<Invoice> invoices = getRepository().findByWasReversed(reversed);
        List<InvoiceDTO> dtos = new ArrayList<>(invoices.size());
        invoices.forEach((company) -> dtos.add(getConverter().fromEntityToDto(company)));
        return dtos;
    }
    
    public InvoiceDTO findByInvoiceNumberAndSenderAndReceiverAndWasReversed(String invoiceNumber , Company sender , Company receiver, boolean wasReversed)
    {
        Invoice invoice = getRepository().findByInvoiceNumberAndSenderAndReceiverAndWasReversed(invoiceNumber, sender, receiver, wasReversed);
        return getConverter().fromEntityToDto(invoice);

    }

}
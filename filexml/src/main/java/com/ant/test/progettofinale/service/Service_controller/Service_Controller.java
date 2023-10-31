package com.ant.test.progettofinale.service.Service_controller;

import org.springframework.stereotype.Service;

import com.ant.test.progettofinale.service.Association_TableService;
import com.ant.test.progettofinale.service.BankService;
import com.ant.test.progettofinale.service.Bank_AccountService;
import com.ant.test.progettofinale.service.CompanyService;
import com.ant.test.progettofinale.service.Document_TypeService;
import com.ant.test.progettofinale.service.InvoiceService;
import com.ant.test.progettofinale.service.TypeService;

import lombok.Data;


@Service
@Data
public class Service_Controller 
{
    private final Bank_AccountService bank_AccountService;
    private final BankService bankService;
    private final CompanyService companyService;
    private final TypeService typeService;
    private final Document_TypeService document_TypeService;
    private final InvoiceService invoiceService;
    private final Association_TableService association_TableService;
  
}
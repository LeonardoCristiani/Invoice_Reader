package com.ant.test.progettofinale.service;

import org.springframework.stereotype.Service;

import com.ant.test.progettofinale.converter.Bank_AccountConverter;
import com.ant.test.progettofinale.dto.Bank_AccountDTO;
import com.ant.test.progettofinale.entity.Bank_Account;
import com.ant.test.progettofinale.repository.Bank_AccountRepository;

@Service
public class Bank_AccountService extends GenericService<Bank_Account, Bank_AccountConverter, Bank_AccountRepository, Bank_AccountDTO, Long>
{

    public Bank_AccountService(Bank_AccountConverter converter, Bank_AccountRepository repository) 
    {
        super(converter, repository);
    }
    
    public Bank_AccountDTO findByIban(String iban)
    {
        Bank_Account accounts = getRepository().findByIban(iban);
        Bank_AccountDTO dtos = getConverter().fromEntityToDto(accounts);
        return dtos;
    }
}
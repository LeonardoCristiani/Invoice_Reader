package com.ant.test.progettofinale.converter;

import org.springframework.stereotype.Service;

import com.ant.test.progettofinale.dto.Bank_AccountDTO;
import com.ant.test.progettofinale.entity.Bank_Account;
import com.ant.test.progettofinale.repository.BankRepository;
import com.ant.test.progettofinale.repository.CompanyRepository;

import lombok.Data;

/**
 * Convertitore per i dati dei conti correnti.
 */
@Service
@Data
public class Bank_AccountConverter implements GenericConverter<Bank_Account, Bank_AccountDTO> 
{
    private final BankRepository bankRepository;

    private final CompanyRepository companyRepository;

    @Override
    public Bank_AccountDTO fromEntityToDto(Bank_Account b)
    {
        Bank_AccountDTO d = new Bank_AccountDTO();
        d.setId(b.getId());
        d.setIban(b.getIban());
        d.setMax_value(b.getMax_value());
        d.setBank_id(b.getBank().getId());
        d.setCompany_id(b.getCompany().getId());
        
        return d;
    }

    @Override
    public Bank_Account fromDtoToEntity(Bank_AccountDTO d)
    {
        Bank_Account b = new Bank_Account();
        b.setId(d.getId());
        b.setIban(d.getIban());
        b.setBank(bankRepository.findById(d.getBank_id()).get());
        b.setCompany(companyRepository.findById(d.getCompany_id()).get());
        b.setMax_value(d.getMax_value());
        return b;
    }
}
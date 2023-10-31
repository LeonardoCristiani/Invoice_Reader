package com.ant.test.progettofinale.converter;

import org.springframework.stereotype.Service;
import com.ant.test.progettofinale.dto.BankDTO;
import com.ant.test.progettofinale.entity.Bank;

/**
 * Convertitore per i dati delle banche.
 */
@Service
public class BankConverter implements GenericConverter<Bank, BankDTO>
{
    @Override
    public Bank fromDtoToEntity (BankDTO d)
    {
        Bank b = new Bank();
        b.setId(d.getId());
        b.setName(d.getName());
        return b;
    }

    @Override
    public BankDTO fromEntityToDto (Bank b)
    {
        BankDTO d = new BankDTO();
        d.setId(b.getId());
        d.setName(b.getName());
        return d;
    }   
}
package com.ant.test.progettofinale.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ant.test.progettofinale.converter.BankConverter;
import com.ant.test.progettofinale.dto.BankDTO;
import com.ant.test.progettofinale.entity.Bank;
import com.ant.test.progettofinale.repository.BankRepository;

@Service
public class BankService extends GenericService<Bank, BankConverter, BankRepository, BankDTO, Long>
{
    public BankService(BankConverter converter, BankRepository repository) {
        super(converter, repository);
    }
    
    public List<BankDTO> findByName(String name)
    {
        List<Bank> banks = getRepository().findByName(name);
        List<BankDTO> dtos = new ArrayList<>(banks.size());
        banks.forEach((bank) -> dtos.add(getConverter().fromEntityToDto(bank)));
        return dtos;
    }
}
package com.ant.test.progettofinale.converter;

import org.springframework.stereotype.Service;

import com.ant.test.progettofinale.dto.CompanyDTO;
import com.ant.test.progettofinale.entity.Company;

/**
 * Convertitore per i dati delle aziende.
 */
@Service
public class CompanyConverter implements GenericConverter<Company, CompanyDTO>
{
    @Override
    public Company fromDtoToEntity (CompanyDTO d)
    {
        Company c = new Company();
        c.setId(d.getId());
        c.setName(d.getName());
        c.setVat_number(d.getVat_Number());
        c.setAddress(d.getAddress());
        return c;
    }

    @Override
    public CompanyDTO fromEntityToDto (Company c)
    {
        CompanyDTO d = new CompanyDTO();
       
        d.setId(c.getId());
        d.setName(c.getName());
        d.setVat_Number(c.getVat_number());
        d.setAddress(c.getAddress());
        return d;
    }
}
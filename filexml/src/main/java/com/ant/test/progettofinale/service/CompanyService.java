package com.ant.test.progettofinale.service;

import org.springframework.stereotype.Service;

import com.ant.test.progettofinale.converter.CompanyConverter;
import com.ant.test.progettofinale.dto.CompanyDTO;
import com.ant.test.progettofinale.entity.Company;
import com.ant.test.progettofinale.repository.CompanyRepository;

@Service
public class CompanyService extends GenericService<Company, CompanyConverter, CompanyRepository, CompanyDTO, Long>
{
    public CompanyService(CompanyConverter converter, CompanyRepository repository)
    {
        super(converter, repository);
    }

    public CompanyDTO findByName(String name)
    {
        Company companies = getRepository().findByName(name);
        CompanyDTO dtos = new CompanyDTO();
        dtos= getConverter().fromEntityToDto(companies);
        return dtos;
    }

    public CompanyDTO findByVatNumber(String vatNumber)
    {
        Company companies = getRepository().findByVatNumber(vatNumber);
        CompanyDTO dtos = getConverter().fromEntityToDto(companies);
        return dtos;
    }

    public CompanyDTO findByTypeAndVat_Number(String type, String name)
    {
        Company compa= getRepository().findByTypeAndVat_Number(type, name);
        
        return getConverter().fromEntityToDto(compa);

    }
}
package com.ant.test.progettofinale.converter;

import org.springframework.stereotype.Service;

import com.ant.test.progettofinale.dto.AssociationDTO;
import com.ant.test.progettofinale.entity.Association_Table;
import com.ant.test.progettofinale.repository.CompanyRepository;
import com.ant.test.progettofinale.repository.TypeRepository;

import lombok.Data;

/**
 * Convertitore per i dati della tabella di associazione.
 */
@Service
@Data
public class AssociationConverter implements GenericConverter <Association_Table,AssociationDTO>
{
    private final CompanyRepository companyRepository;
    private final TypeRepository typeRepository;
    @Override
    public AssociationDTO fromEntityToDto (Association_Table entity) 
    {
        AssociationDTO assodto= new AssociationDTO();
        assodto.setId(entity.getId());
        assodto.setId_Company(entity.getCompany().getId());
        assodto.setId_type(entity.getType().getId());
        return assodto;
    }

    @Override
    public Association_Table fromDtoToEntity(AssociationDTO d) 
    {
        Association_Table asstab= new Association_Table();
        asstab.setId(d.getId());

        asstab.setCompany(companyRepository.findById(d.getId_Company()).get());
        asstab.setType(typeRepository.findById(d.getId_type()).get());
        return asstab;
    }
}
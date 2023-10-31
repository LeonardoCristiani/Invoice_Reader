package com.ant.test.progettofinale.service;

import org.springframework.stereotype.Service;

import com.ant.test.progettofinale.converter.AssociationConverter;
import com.ant.test.progettofinale.dto.AssociationDTO;
import com.ant.test.progettofinale.entity.Association_Table;
import com.ant.test.progettofinale.repository.AssociationRepository;

/**
 * 
 */
@Service
public class Association_TableService  extends GenericService<Association_Table, AssociationConverter,AssociationRepository , AssociationDTO, Long>
{
    public Association_TableService(AssociationConverter converter, AssociationRepository repository)
    {
        super(converter, repository);
    }
}
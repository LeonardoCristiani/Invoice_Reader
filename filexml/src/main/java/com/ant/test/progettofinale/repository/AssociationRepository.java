package com.ant.test.progettofinale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ant.test.progettofinale.entity.Association_Table;

/**
 * Repository per la tabella association_table.
 */
public interface AssociationRepository extends JpaRepository<Association_Table, Long>
{

}
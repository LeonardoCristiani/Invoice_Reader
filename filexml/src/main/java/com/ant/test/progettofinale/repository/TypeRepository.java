package com.ant.test.progettofinale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ant.test.progettofinale.entity.Type;
import java.util.List;

/**
 * Repository per la tabella type.
 */
public interface TypeRepository extends JpaRepository<Type, Long>
{
    /**
     * 
     * @param type
     * @return
     */
    public List<Type> findByType(String type);
}
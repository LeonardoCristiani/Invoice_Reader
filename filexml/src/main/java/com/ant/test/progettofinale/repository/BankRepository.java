package com.ant.test.progettofinale.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ant.test.progettofinale.entity.Bank;
import java.util.List;

/**
 * Repository per la tabella banks.
 */
public interface BankRepository extends JpaRepository<Bank, Long>
{
    /**
     * Trova le banche che hanno un certo nome.
     * @param name Nome della banca.
     * @return Istanze di {@link Bank} risultato della ricerca.
     */
    List<Bank> findByName(String name);
}
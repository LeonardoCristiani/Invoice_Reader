package com.ant.test.progettofinale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ant.test.progettofinale.entity.Document_Type;

/**
 * Repository per la tabella document_type.
 */
public interface Document_TypeRepository extends JpaRepository<Document_Type, Long>
{
    /**
     * Trova i dati di uno specifico tipo di documento.
     * @param type Tipo di documento.
     * @return Istanza di {@link Document_Type} con le informazioni sul tipo.
     */
    public Document_Type findByType(String type);

    //SELECT COUNT(*) FROM document_type where type = type;
    /**
     * 
     * @param type
     * @return
     */
    //Questa query restituirà sempre 1.
    @Query("SELECT COUNT(*) FROM Document_Type dt WHERE dt.type = :type")
    public long findCountType(String type);
}
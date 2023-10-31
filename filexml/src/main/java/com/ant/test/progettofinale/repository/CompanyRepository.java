package com.ant.test.progettofinale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ant.test.progettofinale.entity.Company;

import java.util.List;

/**
 * Repository per la tabella companies.
 */
public interface CompanyRepository extends JpaRepository<Company, Long>
{
    /**
     * Trova un'azienda per nome.
     * @param name Nome dell'azienda.
     * @return Istanza di {@link Company} relativa all'azienda trovata.
     */
    public Company findByName(String name);

    // select *from company where vat_number= ?
    /**
     * Trova un'azienda per partita IVA.
     * @param vat_number Partita IVA.
     * @return Istanza di {@linl Company} relativa all'azienda trovata.
     */
    @Query("SELECT c from Company c WHERE vat_number = :vat_number")
    public Company findByVatNumber(String vat_number);

     /* Select  co.name as CompanyName 
          from type t 
          inner join association_table asso 
              on asso.type_id = t.id
          inner join companies co 
              on asso.company_id = co.id
          where t.type= type;
*/
    /**
     * Trova tutte le aziende di un certo tipo.
     * @param type Tipo di azienda.
     * @return Istanze di {@link Company} delle aziende del tipo indicato.
     */
    @Query("SELECT  co.name as CompanyName" +
        " from Type t inner join t.association_table asso " + 
        " inner join asso.company co " +
        " where t.type = :type")

    public List<Company> findCompanyByTypes(String type);

/*!SECTION
 * select co.* 
from companies co inner join association_table asso
on asso.company_id = co.id 
inner join type t on t.id= asso.type_id
where t.type="Interno" and co.name="DINAMICA SRL";
 */
 
    /**
     * Trova un'azienda che ha un certo nome e un certo tipo.
     * @param type Tipo dell'azienda.
     * @param name Nome dell'azienda.
     * @return Istanza di {@link Company} dell'azienda trovata.
     */
    @Query("Select co from Company co " +
        "inner join co.association_table asso " + 
        "inner join asso.type t " + 
        "where t.type = :type and co.vat_number = :name" )   
    public Company findByTypeAndVat_Number(String type, String name);
}
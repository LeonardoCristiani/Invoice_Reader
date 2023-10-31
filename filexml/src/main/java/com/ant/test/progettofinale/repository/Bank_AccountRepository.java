package com.ant.test.progettofinale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ant.test.progettofinale.entity.Bank_Account;

/**
 * Repository per la tabella bank_accounts.
 */
public interface Bank_AccountRepository extends JpaRepository<Bank_Account, Long>
{
  /**
   * Trova il conto corrente associato a un IBAN.
   * @param iban IBAN.
   * @return Istanza di {@link Bank_Account} 
   */
  public Bank_Account findByIban(String iban);
  
  /*Select b.name, ba.iban , ba.max_value 
from bank_accounts ba 
inner join banks b on ba.bank_id = b.id
where b.name= b.name;
 
  */
  /**
   * Trova una banca in base al suo nome.
   * @param name Nome della banca.
   * @return Istanza di {@link Bank_Account} che contiene i dati della banca a cui è associato il conto.
   */
  @Query(" Select  b.name as Company , ba.iban as Iban , ba.max_value as Value   from Bank_Account ba " +
          "inner join ba.bank b where b.name = : name ")
  public Bank_Account findBankByName(String name);
 

 

 /*
  *  Select co.name Company, b.name Bank, ba.iban , ba.max_value , ty.type
                    from type ty inner join companies co 
                            on ty.company_id= co.id 
                            inner join bank_accounts ba 
                            on ba.company_id = co.id
                            inner join banks b 
                            on ba.bank_id= b.id
                            where co.name= "";
   */
  /**
   * Trova i dati delle aziende del tipo specificato.
   * @param type Tipo dell'azienda.
   * @return Istanze di {@link Bank_Account} contenenti le informazioni sull'azienda.
   */
  @Query("SELECT co.name AS Company, b.name AS Bank, ba.iban AS Iban, ba.max_value AS MaxValue " +
        "FROM Type ty INNER JOIN ty.association_table asso " +
        "INNER JOIN asso.company co " +
        "INNER JOIN co.bank_account ba " +
        "INNER JOIN ba.bank b "+
        "WHERE ty.type = : type ")
  public List<Bank_Account> findBankAccountByCompanyName(String type);


  @Query("select b " +
          "from Bank_Account b "+
          "inner join b.company co " +
          "where co.vat_number = :idcodice")
  public List<Bank_Account> findBank_AccountsByIdcodice(String idcodice);
}
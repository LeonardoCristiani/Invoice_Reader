package com.ant.test.progettofinale.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ant.test.progettofinale.entity.Company;
import com.ant.test.progettofinale.entity.Invoice;
import java.time.LocalDate;


/**
 * Repository per la tabella invoices.
 */
public interface InvoiceRepository extends JpaRepository<Invoice, Long>
{
    /**
     * Trova le fatture in base al numero.
     * @param invoiceNumber Numero fattura.
     * @return Istanze di {@link Invoice} con il numero indicato.
     */
    public List<Invoice> findByInvoiceNumber(String invoiceNumber);

    //SELECT dt.type from invoices i inner join document_type dt on dt.id = i.type_id;
    /**
     * Trova le fatture in base al tipo di documento.
     * @param type_id ID tipo.
     * @return Istanze di {@link Invoice} del tipo indicato.
     */
    @Query("SELECT t.type FROM Invoice i INNER JOIN i.type t ")
    public List<Invoice> findByTypeDocument(long type_id);

    /**
     * Trova le fattura inviate da una certa azienda.
     * @param sender_id ID dell'azienda.
     * @return Istanze di {@link Invoice} rappresentanti le fatture inviate dall'azienda fornita.
     */
    @Query("SELECT s.name FROM Invoice i INNER JOIN i.sender s")
    public List<Invoice> findSenderCompany(long sender_id);

    /**
     * Trova le fattura ricevute da una certa azienda.
     * @param sender_id ID dell'azienda.
     * @return Istanze di {@link Invoice} rappresentanti le fatture ricevute dall'azienda fornita.
     */
    @Query("SELECT r.name FROM Invoice i INNER JOIN i.receiver r")
    public List<Invoice> findReceiverCompany(long receiver_id);

    /**
     * Trova le fatture con un certo imponibile.
     * @param income Imponibile.
     * @return Istanze di {@link Invoice} che hanno l'imponibile fornito.
     */
    public List<Invoice> findByIncome(double income);

    /**
     * Trova le fatture con una certa IVA.
     * @param vat IVA.
     * @return Istanze di {@link Invoice} che hanno l'IVA fornita.
     */
    public List<Invoice> findByVat(double vat);

    /**
     * Trova le fattura pagate su un certo conto corrente.
     * @param account_id ID del conto.
     * @return Istanze di {@link Invoice} pagate su un certo conto corrente.
     */
    @Query("SELECT a.iban FROM Invoice i INNER JOIN i.bank_account a")
    public List<Invoice> findIban (long account_id);

    /**
     * Trova le fatture con una certa ritenuta d'acconto.
     * @param retentionTax Ritenuta d'acconto.
     * @return Istanze di {@link Invoice} con la ritenuta d'acconto indicata.
     */
    public List<Invoice> findByRetentionTax(double retentionTax);

    /**
     * Trova le fatture con una certa causale.
     * @param causal Causale.
     * @return Istanze di {@link Invoice} con la causale indicata.
     */
    public List<Invoice> findByDescription(String causal);

    /**
     * Trova le fatture con una certa data di invio / ricezione.
     * @param issueDate Data di invio / ricezione.
     * @return Istanze di {@link Invoice} inviate / ricevute nella data indicata.
     */
    public List<Invoice> findByIssueDate(LocalDate issueDate);

    /**
     * Trova le fattura con una certa data di scadenza.
     * @param expirationDate Data di scadenza.
     * @return Istanze di {@link Invoice} che scadono nella data indicata.
     */
    public List<Invoice> findByExpirationDate(LocalDate expirationDate);

    /**
     * Trova le fattura con una certa data di pagamento.
     * @param collectionDate Data di pagamento.
     * @return Istanze di {@link Invoice} che sono state pagate nella data indicata.
     */
    public List<Invoice> findByCollectionDate(LocalDate collectionDate);

    /**
     * Trova tutte le fatture anticipate o meno.
     * @param wasAdvanced Indica se la fattura è stata anticipata.
     * @return Istanze di {@link Invoice} risultato della ricerca.
     */
    public List<Invoice> findByWasAdvanced(boolean wasAdvanced);

    /**
     * Trova tutte le fatture stornate o meno.
     * @param wasReversed Indica se la fattura è stata stornata.
     * @return Istanze di {@link Invoice} risultato della ricerca.
     */
    public List<Invoice> findByWasReversed(boolean wasReversed);
    
    //SELECT * FROM invoices i where expiration_date < YEAR('01');
    /**
     * Trova tutte le fatture scadute prima di una certa data.
     * @param expiration_date Data di scadenza.
     * @return Istanze di {@link Invoice} scadute prima della data indicata.
     */
    @Query("SELECT i FROM Invoice i where i.expirationDate <: expirationDate")
    public List<Invoice> findBeforeExpirationDate(LocalDate expiration_date);
        
        // String hql = "SELECT i FROM Invoice i where i.expirationDate <: expirationDate";
        // Query query = session.createQuery(hql);
        // Scanner s = new Scanner(System.in);
        // query.setParameter("expirationDate", s);
    
     /**
     * Trova tutte le fatture scadute dopo di una certa data.
     * @param expiration_date Data di scadenza.
     * @return Istanze di {@link Invoice} scadute dopo della data indicata.
     */
    @Query("SELECT i FROM Invoice i where i.expirationDate >: expirationDate")
    public List<Invoice> findAfterExpirationDate(LocalDate expiration_date);

     /**
     * Trova tutte le fatture emesse / ricevute prima di una certa data.
     * @param expiration_date Data di scadenza.
     * @return Istanze di {@link Invoice} emesse / ricevute prima della data indicata.
     */
    @Query("SELECT i FROM Invoice i where i.issueDate <: issueDate")
    public List<Invoice> findBeforeIssueDate(LocalDate issue_date);

     /**
     * Trova tutte le fatture emesse / ricevute dopo di una certa data.
     * @param expiration_date Data di scadenza.
     * @return Istanze di {@link Invoice} emesse / ricevute dopo della data indicata.
     */
    @Query("SELECT i FROM Invoice i where i.issueDate >: issueDate")
    public List<Invoice> findAfterIssueDate(LocalDate issue_date);

     /**
     * Trova tutte le fatture pagate prima di una certa data.
     * @param expiration_date Data di scadenza.
     * @return Istanze di {@link Invoice} pagate prima della data indicata.
     */
    @Query("SELECT i FROM Invoice i where i.collectionDate <: collectionDate")
    public List<Invoice> findBeforeCollectionDate(LocalDate collection_date);

     /**
     * Trova tutte le fatture pagate dopo di una certa data.
     * @param expiration_date Data di scadenza.
     * @return Istanze di {@link Invoice} pagate dopo della data indicata.
     */
    @Query("SELECT i FROM Invoice i where i.collectionDate >: collectionDate")
    public List<Invoice> findAfterCollectionDate(LocalDate collection_date);

   //SELECT b.name as 'Banca', ba.iban as 'Iban' FROM invoices i inner join bank_accounts ba on ba.id = i.account_id
   //                   inner join banks b on b.id = ba.bank_id where ba.id = b.id;
   /**
    * Trova le fatture pagate su un certo conto corrente.
    * @param account_id ID del conto.
    * @return Istanze di {@link Invoice} pagate sul conto corrente indicato.
    */
   @Query("SELECT b.name AS Banca, ba.iban AS Iban, i.description AS Descrizione, i.issueDate AS Emissione, i.expirationDate AS Scadenza, "
        +   " i.invoiceNumber AS Numero, i.income"
        +   " FROM Invoice i"
        +   " INNER JOIN i.bank_account ba "
        +   " INNER JOIN ba.bank b " 
        +    "WHERE ba.id = : account_id ")
    public List<Invoice> findInvoiceWithBank(@Param("account_id") long account_id);
    
   /**
    * Trova le fatture con un certo numero, un certo emittente, un certo ricevente.
    * @param invoiceNumber Numero fattura.
    * @param sender Emittente.
    * @param receiver Ricevente.
    * @param wasReversed Indica se la fattura è stata anticipata.
    * @return Istanza di {@link Invoice} risultato della ricerca.
    */
    public Invoice findByInvoiceNumberAndSenderAndReceiverAndWasReversed(String invoiceNumber, Company sender, Company receiver, boolean wasReversed);
   
/*!SECTION
        * select *
        from invoices 
        where invoice_number= ? and received_id= ? and sender_id=?;
 */
//  public Invoice findByInvoiceNumberAndSenderAndReceiverAndWasReversed( String invoiceNumber,@Param("sender_id") Company sender,@Param("received_id") Company received ,@Param("wasReversed") boolean wasreversed);

}
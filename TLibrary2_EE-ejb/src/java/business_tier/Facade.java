/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business_tier;

import integration_tier.TBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import sub_business_tier.TFacade;

/**
 *
 * @author azochniak
 */
@Stateless
public class Facade implements FacadeRemote {
    
//    @javax.persistence.PersistenceContext
//    private javax.persistence.EntityManager em;

    TFacade facade;
    TBase base;
    
    @PostConstruct
    public void init() {
        /**
         * Initializing here might be walkaround for class A cannot be cast to class A.
         */
        facade = new TFacade();
        base = new TBase(facade);
//        base.setEm(em);
        try {
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (Throwable ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("There is no JDBC Driver. Copy it to glassfish/lib folder and restart glassfish", ex);
        }
    }

    public Object[][] gettitle_books() {
        return facade.gettitle_books();
    }

    public boolean add_title_book(String data[]) {
        return facade.add_title_book(data);
    }

    public boolean add_book(String data1[], String data2[]) {
        return facade.add_book(data1, data2);
    }

    public ArrayList<String> Search_title_book(String data[]) {
        return facade.Search_title_book(data);
    }

    public void Print_books() {
        facade.Print_books();
    }

    public void Print_title_books() {
        facade.Print_title_books();
    }

    // definition of methods for database service
    public void update_titles() throws Exception {
        base.update_titles();
    }

    public void update_books() throws Exception {
        base.update_books();
    }

    public void update_data() throws Exception {
        base.update_data();
        LOG.info("UPDATEDATA(): books="+base.books());
    }

    public void add_titles() throws Exception {
        base.add_titles();
    }

    public void add_books() throws Exception {
        base.add_books();
    }

    public ArrayList<ArrayList<String>> titles() throws Exception {
        final ArrayList<ArrayList<String>> titles = base.titles();
        return titles;
    }

    @Override
    public void return_book(String[] bookTitle, String[] bookNumber) {
        LOG.info("returning book="+Arrays.asList(bookTitle)+ " number=" + Arrays.asList(bookNumber));
        facade.return_book(bookTitle, bookNumber);
    }
    
    private static final Logger LOG = Logger.getLogger(Facade.class.getName());

    @Override
    public void add_borrow(String[] string, String[] string0, String client) {
        facade.add_borrow(string, string0, client);
    }

    @Override
    public List<String> getClients() {
        return facade.getClients();
    }

    @Override
    public Object[][] getBooksWithBorrower() {
        return facade.getBooksWithBorrowers();
    }

    @Override
    public void exampleData() {
        facade.exampleData();
    }

    @Override
    public ArrayList<String> getBooksByTitle(String[] title) {
        return facade.getBooksByTitle(title);
    }

    @Override
    public void add_client(String client) throws Exception {
        facade.add_client(client);
    }

    @Override
    public List<ArrayList<String>> books() {
        return base.books();
    }

    @Override
    public String addTitleBook(String[] t1) {
        add_title_book(t1);
        return "OK";
    }

    @Override
    /**
     * Searches for particular book. 
     * 
     * If given argument is not null, then tries to match it's every not-null parameter with, accordingly, ["2" for TBook, "3" for TBookOnTape, Author, Title, ISBN and Publisher]. 
     * Example: 
     * if one looks for only book 
     */
    public List<ArrayList<String>> booksByTitle(String[] titleForFactory) {
        return base.booksByTitle(titleForFactory);
    }

    public Object getTitleByISBN(String ISBN) {
        return base.getTitleByISBN(ISBN);
    }

    @Override
    public String addBook(final String[] ISBNat3, final String[] numberAt1) {
        return facade.addBook(ISBNat3, numberAt1);
    }

    @Override
    public void store_data() {
        base.store_data();
    }
    
}

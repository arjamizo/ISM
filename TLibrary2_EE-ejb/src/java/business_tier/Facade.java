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

    private TFacade facade;
    private TBase base;
    
    @PostConstruct
    public void init() {
        /**
         * Initializing here might be walkaround for class A cannot be cast to class A.
         */
//        base.setEm(em);
        try {
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (Throwable ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("There is no JDBC Driver. Copy it to glassfish/lib folder and restart glassfish", ex);
        }
    }

    public Object[][] gettitle_books() {
        return getFacade().gettitle_books();
    }

    public boolean add_title_book(String data[]) {
        return getFacade().add_title_book(data);
    }

    public boolean add_book(String data1[], String data2[]) {
        return getFacade().add_book(data1, data2);
    }

    public ArrayList<String> Search_title_book(String data[]) {
        return getFacade().Search_title_book(data);
    }

    public void Print_books() {
        getFacade().Print_books();
    }

    public void Print_title_books() {
        getFacade().Print_title_books();
    }

    // definition of methods for database service
    public void update_titles() throws Exception {
        getBase().update_titles();
    }

    public void update_books() throws Exception {
        getBase().update_books();
    }

    public void update_data() throws Exception {
        getBase().update_data();
    }

    public void add_titles() throws Exception {
        getBase().add_titles();
    }

    public void add_books() throws Exception {
        getBase().add_books();
    }

    public ArrayList<ArrayList<String>> titles() throws Exception {
        final ArrayList<ArrayList<String>> titles = getBase().titles();
        return titles;
    }

    @Override
    public void return_book(String[] bookTitle, String[] bookNumber, String client) {
        getFacade().return_book(bookTitle, bookNumber, client);
    }
    
    private static final Logger LOG = Logger.getLogger(Facade.class.getName());

    @Override
    public void add_borrow(String[] string, String[] string0, String client) {
        getFacade().add_borrow(string, string0, client);
    }

    @Override
    public List<String> getClients() {
        return getFacade().getClients();
    }

    @Override
    public Object[][] getBooksWithBorrower() {
        return getFacade().getBooksWithBorrowers();
    }

    @Override
    public void exampleData() {
        getFacade().exampleData();
    }

    @Override
    public ArrayList<String> getBooksByTitle(String[] title) {
        return getFacade().getBooksByTitle(title);
    }

    @Override
    public List<ArrayList<String>> books() {
        return getBase().books();
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
        return getBase().booksByTitle(titleForFactory);
    }

    public Object getTitleByISBN(String ISBN) {
        return getBase().getTitleByISBN(ISBN);
    }

    @Override
    public String addBook(final String[] ISBNat3, final String[] numberAt1) {
        return getFacade().addBook(ISBNat3, numberAt1);
    }

    @Override
    public void store_data() {
        getBase().store_data();
    }

    public TFacade getFacade() {
        if(this.facade==null) setFacade(new TFacade());
        return facade;
    }

    public void setFacade(TFacade facade) {
        this.facade = facade;
    }

    public TBase getBase() {
        if(base==null) setBase(new TBase(getFacade()));
        return base;
    }

    public void setBase(TBase base) {
        this.base = base;
    }
    
}

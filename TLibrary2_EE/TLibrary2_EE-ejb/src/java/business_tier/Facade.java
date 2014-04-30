/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business_tier;

import integration_tier.TBase;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sub_business_tier.TFacade;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TTitle_book;

/**
 *
 * @author azochniak
 */
@Stateless
public class Facade implements FacadeRemote {
    
//    @PersistenceContext
//    private EntityManager em;
//    @PostConstruct
//    public void init() {
//        base.setEm(em);
//    } 

    TFacade facade = new TFacade();
    TBase base = new TBase(facade);
    
    public ArrayList<TTitle_book> getmTitle_books() {
        return facade.getmTitle_books();
    }

    public Object[][] gettitle_books() {
        return facade.gettitle_books();
    }

    public TTitle_book search_title_book(TTitle_book title_book) {
        return facade.search_title_book(title_book);
    }

    public TTitle_book add_title_book(String data[]) {
        return facade.add_title_book(data);
    }

    public TTitle_book add_book(String data1[], String data2[]) {
        return facade.add_book(data1, data2);
    }

    public ArrayList<String> Search_title_book(String data[]) {
        return facade.Search_title_book(data);
    }

    public TBook Search_accessible_book(String data1[], String data2) {
        return facade.Search_accessible_book(data1, data2);
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
    }

    public void add_titles() throws Exception {
        base.add_titles();
    }

    public void add_books() throws Exception {
        base.add_books();
    }

    public ArrayList<ArrayList<String>> titles() throws Exception {
        return base.titles();
    }

    @Override
    public void returnBook(String[] bookTitle, String[] bookNumber) {
        LOG.info("returning book="+Arrays.asList(bookTitle)+ " number=" + Arrays.asList(bookNumber));
        facade.returnBook(bookTitle, bookNumber);
    }
    private static final Logger LOG = Logger.getLogger(Facade.class.getName());

    @Override
    public void borrowBook(String[] string, String[] string0, String client) {
        facade.borrowBook(string, string0, client);
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
        base.add_users();
    }
    
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business_tier;

import com.google.common.base.Predicate;
import integration_tier.TBase;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
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
    
    @PostConstruct
    public void init() {
        try {
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (Throwable ex) {
            Logger.getLogger(Facade.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("There is no JDBC Driver. Copy it to glassfish/lib folder and restart glassfish", ex);
        }
    }
    
    public ArrayList<TTitle_book> getmTitle_books() {
        return facade.getmTitle_books();
    }

    public Object[][] gettitle_books() {
        return facade.gettitle_books();
    }

    public TTitle_book search_title_book(TTitle_book title_book) {
        return facade.search_title_book(title_book);
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
        LOG.info("addBook(): "+ISBNat3[2]+" "+Arrays.asList(numberAt1));
        ArrayList<String> book; 
//        book = (ArrayList<String>) com.google.common.collect.Iterables.find(getBooksByTitle(null), 
//                new Predicate() {
//            @Override
//            public boolean apply(Object t) {
//                LOG.info("Object to String[]: "+t);
//                ArrayList<String> title=(ArrayList<String>) t;
//                LOG.info("addBook(): "+title+" "+Arrays.asList(numberAt1));
//                return title.get(2).equals(ISBNat3[2]);
//            }
//        }, (String[])null);
        String[] ttitle = null;
        LOG.info("lookin for book "+ISBNat3[2]);
        LOG.info("books: "+gettitle_books().length);
        for (Object[] e : gettitle_books()) {
            LOG.info("checking... "+ e);
            String[] title=(String[]) e;
            LOG.info("checking... "+ title[2]);
            
            if(title[2].equals(ISBNat3[2])) {
                List<String> list = new ArrayList();
                list.add(title[4].length()==0?"1":"3");
                list.addAll(Arrays.asList(title));
                LOG.info("found1!"+list);
                ttitle=list.toArray(new String[0]);
                break;
            }
        }
        LOG.info("found2!"+(ttitle!=null?Arrays.asList(ttitle):"NULL TITLE"));
        if(ttitle==null) 
            throw new RuntimeException("did not find title entry for this book "+ ISBNat3[2]);
//        asList = book.toArray(new String[0]);
        add_book(ttitle, numberAt1);
        return "OK";
    }
    
}

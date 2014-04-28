/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.pwr;

import integration_tier.TBase;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import sub_business_tier.TFacade;
import sub_business_tier.UnaryOperator;
import sub_business_tier.entities.Client;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TTitle_book;

/**
 *
 * @author azochniak
 */
@Stateless
public class Facade /*implements FacadeRemote*/ {
    /**
     * actually not used, but prooves existance of other possibility of accessing entities.
     */
   // @PersistenceContext(unitName = "booksPUJTA")
  //  private EntityManager em; ABSOLUTNIE SIE POZBYC new InitialContext?
    
    TFacade facade=new TFacade(); // this class has to have TFacade instance, cannot inherit its because TFacade is application service and Facade class is session manager
    
    TBase base;
    
    @PostConstruct
    public void init() {
        base = new TBase(facade);
    }

    public EntityManager getEm() {
//        return em;
        return null;
    }
    
    public Facade() {
        LOG.info("initialized");
    }
    private static final Logger LOG = Logger.getLogger(Facade.class.getName());

    
    public synchronized void setmTitle_books(List<TTitle_book> title_books) {
        LOG.info("setting title books");
        throw new RuntimeException("can not call set mtitlebooks");
        //facade.setmTitle_books(title_books);
    }

    public synchronized List<TTitle_book> getmTitle_books() {
        return (facade.getmTitle_books());
    }

    
    public synchronized TTitle_book add_book(String[] data1, String[] data2) {
        return facade.add_book(data1, data2);
    }

    
    public synchronized TTitle_book add_title_book(String[] data) {
        return facade.add_title_book(data); //later we will update titles
    }
//przeniesc do TFacade1: 
    
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

    
    public void borrowBook(String[] data_title, String[] data_book, String client) {
        facade.borrowBook(data_title, data_book, client); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void returnBook(String[] data_title, String[] data_book) {
        facade.returnBook(data_title, data_book); //To change body of generated methods, choose Tools | Templates.
    }

    public synchronized String borrow_book(Client client, TBook book) {
        return facade.borrow_book(client, book); //To change body of generated methods, choose Tools | Templates.
    }

    
    public List<String> getClients() {
        return facade.getClients(); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void Print_clients() {
        facade.Print_clients(); //To change body of generated methods, choose Tools | Templates.
    }

    
    public synchronized void delete_client(String login) {
        facade.delete_client(login); //To change body of generated methods, choose Tools | Templates.
    }

    
    public synchronized void add_client(String name) {
        facade.add_client(name); //To change body of generated methods, choose Tools | Templates.
    }

    
    public synchronized Client search_client(String login) {
        return facade.search_client(login); //To change body of generated methods, choose Tools | Templates.
    }

    
    public synchronized void Print_title_books() {
        facade.Print_title_books(); //To change body of generated methods, choose Tools | Templates.
    }

    
    public synchronized void Print_books() {
        facade.Print_books(); //To change body of generated methods, choose Tools | Templates.
    }

    
    public synchronized ArrayList<String> gettitle_books_arr() {
        return facade.gettitle_books_arr(); //To change body of generated methods, choose Tools | Templates.
    }

    
    public synchronized TBook Search_borrowable_book(String[] data1, String data2) {
        return facade.Search_borrowable_book(data1, data2); //To change body of generated methods, choose Tools | Templates.
    }

    
    public synchronized TBook Search_accessible_book(String[] data1, String data2) {
        return facade.Search_accessible_book(data1, data2); //To change body of generated methods, choose Tools | Templates.
    }

    
    public synchronized TBook Search_book(String[] data1, String[] data2) {
        return facade.Search_book(data1, data2); //To change body of generated methods, choose Tools | Templates.
    }

    
    public synchronized ArrayList<String> Search_title_books(String[] data) {
        return facade.Search_title_books(data); //To change body of generated methods, choose Tools | Templates.
    }

    
    public synchronized TTitle_book Search_title_book(String[] data) {
        return facade.Search_title_book(data); //To change body of generated methods, choose Tools | Templates.
    }

    
    public synchronized Object[][] getBooks(UnaryOperator filter) {
        return facade.getBooks(filter); //To change body of generated methods, choose Tools | Templates.
    }

    
    public synchronized Object[][] gettitle_books() {
        return facade.gettitle_books(); //To change body of generated methods, choose Tools | Templates.
    }

    
    public synchronized TTitle_book search_title_book(TTitle_book title_book) {
        return facade.search_title_book(title_book); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void update_data(TTitle_book[] titles, TBook[] books) {
        facade.update_data(titles, books);
    }

    
    public String borrow_book(String client, String[] book) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    
//    public Object[][] getBooks(UnaryOperator filter) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    
    
}

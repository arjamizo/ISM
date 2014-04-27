/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package adapter;

import java.util.ArrayList;
import java.util.List;
import pl.pwr.remote.FacadeRemote;
import sub_business_tier.TFacade;
import sub_business_tier.TFactory;
import sub_business_tier.UnaryOperator;
import sub_business_tier.entities.Client;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TTitle_book;

/**
 *
 * @author azochniak
 */
public class FacadeAdapter implements FacadeRemote {
    private TFacade facade;

    public FacadeAdapter(TFacade facade) {
        this.facade = facade;
    }

    public TFacade getFacade() {
        return facade;
    }

    public void setFacade(TFacade facade) {
        this.facade = facade;
    }
    

    @Override
    public ArrayList<ArrayList<String>> titles() throws Exception {
        return facade.titles(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update_data(TTitle_book[] titles, TBook[] books) {
        facade.update_data(titles, books); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrowBook(String[] data_title, String[] data_book, String client) {
        facade.borrowBook(data_title, data_book, client); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void returnBook(String[] data_title, String[] data_book) {
        facade.returnBook(data_title, data_book); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getClients() {
        return facade.getClients(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Print_clients() {
        facade.Print_clients(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void delete_client(String login) {
        facade.delete_client(login); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void add_client(String name) {
        facade.add_client(name); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized Client search_client(String login) {
        return facade.search_client(login); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void Print_title_books() {
        facade.Print_title_books(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void Print_books() {
        facade.Print_books(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized ArrayList<String> gettitle_books_arr() {
        return facade.gettitle_books_arr(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized TBook Search_borrowable_book(String[] data1, String data2) {
        return facade.Search_borrowable_book(data1, data2); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized TBook Search_accessible_book(String[] data1, String data2) {
        return facade.Search_accessible_book(data1, data2); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized TBook Search_book(String[] data1, String[] data2) {
        return facade.Search_book(data1, data2); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized ArrayList<String> Search_title_books(String[] data) {
        return facade.Search_title_books(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized TTitle_book Search_title_book(String[] data) {
        return facade.Search_title_book(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized TTitle_book add_book(String[] data1, String[] data2) {
        return facade.add_book(data1, data2); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized TTitle_book add_title_book(String[] data) {
        return facade.add_title_book(data); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized Object[][] getBooks(UnaryOperator filter) {
        return facade.getBooks(filter); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized Object[][] gettitle_books() {
        return facade.gettitle_books(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized TTitle_book search_title_book(TTitle_book title_book) {
        return facade.search_title_book(title_book); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized void setmTitle_books(List<TTitle_book> title_books) {
        facade.setmTitle_books(title_books); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String borrow_book(String client, String[] book) {
        return facade.borrow_book(new Client(client), new TFactory().create_book(book));
    }
    
}

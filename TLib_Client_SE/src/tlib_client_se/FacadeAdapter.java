/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tlib_client_se;

import business_tier.FacadeRemote;
import java.util.ArrayList;
import java.util.List;
import sub_business_tier.TFacade;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TLend;
import sub_business_tier.entities.TTitle_book;
import sub_business_tier.entities.TUser;

/**
 *
 * @author artur
 */
class FacadeAdapter implements FacadeRemote {

    private TFacade facade;

    public FacadeAdapter(TFacade facade) {
        this.facade = facade;
    }

    public synchronized Object[][] getBooksWithBorrowers() {
        return facade.getBooksWithBorrowers(); //To change body of generated methods, choose Tools | Templates.
    }

    public List<String> getClients() {
        return facade.getClients(); //To change body of generated methods, choose Tools | Templates.
    }

    public void returnBook(String[] bookTitle, String[] bookNumber) {
        facade.returnBook(bookTitle, bookNumber); //To change body of generated methods, choose Tools | Templates.
    }

    public void borrowBook(String[] bookTitle, String[] bookNumber, String client) {
        facade.borrowBook(bookTitle, bookNumber, client); //To change body of generated methods, choose Tools | Templates.
    }

    public synchronized ArrayList<String> Search_title_books(String[] data) {
        return facade.Search_title_books(data); //To change body of generated methods, choose Tools | Templates.
    }

    public synchronized void update_data(TTitle_book[] titles, TBook[] books, TLend borrows[], TUser users[]) {
        facade.update_data(titles, books, borrows, users); //To change body of generated methods, choose Tools | Templates.
    }

    public synchronized void Print_title_books() {
        facade.Print_title_books(); //To change body of generated methods, choose Tools | Templates.
    }

    public synchronized void Print_books() {
        facade.Print_books(); //To change body of generated methods, choose Tools | Templates.
    }

    public synchronized TBook Search_accessible_book(String[] data1, String data2) {
        return facade.Search_accessible_book(data1, data2); //To change body of generated methods, choose Tools | Templates.
    }

    public synchronized ArrayList<String> Search_title_book(String[] data) {
        return facade.Search_title_book(data); //To change body of generated methods, choose Tools | Templates.
    }

    public synchronized TTitle_book add_book(String[] data1, String[] data2) {
        return facade.add_book(data1, data2); //To change body of generated methods, choose Tools | Templates.
    }

    public synchronized TTitle_book add_title_book(String[] data) {
        return facade.add_title_book(data); //To change body of generated methods, choose Tools | Templates.
    }

    public synchronized TTitle_book search_title_book(TTitle_book title_book) {
        return facade.search_title_book(title_book); //To change body of generated methods, choose Tools | Templates.
    }

    public synchronized Object[][] gettitle_books() {
        return facade.gettitle_books(); //To change body of generated methods, choose Tools | Templates.
    }

    public void setmTitle_books(ArrayList<TTitle_book> title_books) {
        facade.setmTitle_books(title_books); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<TTitle_book> getmTitle_books() {
        return facade.getmTitle_books(); //To change body of generated methods, choose Tools | Templates.
    }

    public void exampleData() {
        facade.exampleData(); //To change body of generated methods, choose Tools | Templates.
    }

    public void update_data() throws Exception {
        throw new Exception("fetching from DB: " + String.format("%d titles and %d books ", getmTitle_books().size(), getBooksWithBorrower().length) + getClients().toString() + " borrows: " + getBorrows().toString());
    }

    public void update_titles() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void update_books() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void add_titles() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void add_books() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<ArrayList<String>> titles() throws Exception {
//        return base.
        return null;
    }

    public Object[][] getBooksWithBorrower() {
        return facade.getBooksWithBorrowers();
    }

    @Override
    public ArrayList<String> getBooksByTitle(String[] title) {
        return facade.getBooksByTitle(title);
    }

    private List<TLend> getBorrows() {
        return facade.getBorrows();
    }

    @Override
    public void add_client(String client) throws Exception {
        if(!getClients().contains(client) && client!="Available") getClients().add(client);
        throw new UnsupportedOperationException(String.format("User %s was added.", client)); //To change body of generated methods, choose Tools | Templates.
    }

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business_tier;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author azochniak
 */
@Remote
public interface FacadeRemote {

    public Object[][] gettitle_books();
    
    public boolean add_title_book(String data[]);
    
    public boolean add_book(String data1[], String data2[]);

    public ArrayList<String> Search_title_book(String data[]);
    
    public void Print_books();

    public void Print_title_books();

    public void update_titles() throws Exception;

    public void update_books() throws Exception;

    public void update_data() throws Exception;

    public void add_titles() throws Exception;

    public void add_books() throws Exception;

    public ArrayList<ArrayList<String>> titles() throws Exception;

    public void return_book(String[] string, String[] string0, String client);

    public void add_borrow(String[] string, String[] string0, String client);

    public List<String> getClients();

    public Object[][] getBooksWithBorrower();

    public void exampleData();

    public ArrayList<String> getBooksByTitle(String[] title);

    public List<ArrayList<String>> books();
    public List<ArrayList<String>> booksByTitle(String[] titleForFactory);

    public String addTitleBook(String[] t1);

    public Object getTitleByISBN(String submittedValue);

    public String addBook(String[] string, String[] string0);

    public void store_data();

}

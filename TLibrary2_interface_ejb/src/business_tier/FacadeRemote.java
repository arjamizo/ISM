/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business_tier;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Remote;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TTitle_book;

/**
 *
 * @author azochniak
 */
@Remote
public interface FacadeRemote {

    public ArrayList<TTitle_book> getmTitle_books();

    public Object[][] gettitle_books();

    public TTitle_book search_title_book(TTitle_book title_book);

    public TTitle_book add_title_book(String data[]);

    public TTitle_book add_book(String data1[], String data2[]);

    public ArrayList<String> Search_title_book(String data[]);

    public TBook Search_accessible_book(String data1[], String data2);

    public void Print_books();

    public void Print_title_books();

    public void update_titles() throws Exception;

    public void update_books() throws Exception;

    public void update_data() throws Exception;

    public void add_titles() throws Exception;

    public void add_books() throws Exception;

    public ArrayList<ArrayList<String>> titles() throws Exception;

    public void returnBook(String[] string, String[] string0);

    public void borrowBook(String[] string, String[] string0, String client);

    public List<String> getClients();

    public Object[][] getBooksWithBorrower();

    public void exampleData();

    public ArrayList<String> getBooksByTitle(String[] title);

    public void add_client(String client) throws Exception;

    public List<ArrayList<String>> books();
    public List<ArrayList<String>> booksByTitle(String[] titleForFactory);

    public String addTitleBook(String[] t1);

    public Object getTitleByISBN(String submittedValue);

}

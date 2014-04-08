/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sub_business_tier;

import java.util.ArrayList;
import java.util.List;
import library_client_2014.UnaryOperator;
import sub_business_tier.entities.Client;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TTitle_book;

/**
 *
 * @author azochniak
 */
public interface FacadeInterface {

    void Print_books();

    void Print_clients();

    void Print_title_books();

    TBook Search_accessible_book(String[] data1, String data2);

    /***
     *         String d4[] = { "2", "ISBN1", "Actor1" };
     *         String tr4[] = { "1", "2", "-1" };
     *         ap.Search_book(d4, tr4);
     *         first means title_book will be created only for searching, second one means book has to be of ISDN2 and should be given back 2 days ago.
     * @param data1
     * @param data2
     * @return
     */
    TBook Search_book(String[] data1, String[] data2);

    TTitle_book Search_title_book(String[] data);
    ArrayList<String> Search_title_books(String[] data);
    TBook Search_borrowable_book(String data1[], String data2);

    /***
     * If book containing ISBN given in first argument does not exist, then add full info provided in second parameter.
     * @author artur
     */
    TTitle_book add_book(String[] data1, String[] data2);

    void add_client(String name);

    TTitle_book add_title_book(String[] data);

    String borrow_book(Client client, TBook book);
    ///////////////////////////////////////////////////////

    void delete_client(String login);

    ArrayList<TTitle_book> getmTitle_books();

    Object [][] gettitle_books();
    
    Object[][] getBooks(UnaryOperator filter);
    ArrayList<String> gettitle_books_arr();

    ///////////////////////////////////////////////////////////////////////
    //						NEWS FOR LAB 2
    Client search_client(String login);
    
    List<String> getClients();

    TTitle_book search_title_book(TTitle_book title_book);

    void setmTitle_books(ArrayList<TTitle_book> title_books);

    public void returnBook(String data_title[], String data_book[]);

    public void borrowBook(String[] toSymbol, String data[], String client);

    
}

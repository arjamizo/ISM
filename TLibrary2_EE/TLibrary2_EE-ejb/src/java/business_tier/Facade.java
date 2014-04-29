/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business_tier;

import integration_tier.TBase;
import java.util.ArrayList;
import java.util.List;
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
    public void returnBook(String[] string, String[] string0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrowBook(String[] string, String[] string0, String client) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getClients() {
        final ArrayList<String> cls = new ArrayList<String>();
        cls.add("Artur");
        cls.add("David");
        return cls;
    }

}

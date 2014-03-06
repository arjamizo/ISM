/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package davart_lab01;

/**
 *
 * @author UML05
 */
import java.io.Serializable;
import java.util.ArrayList;
public class TFacade implements Serializable {
    private ArrayList<TTitle_book> mTitle_books = new ArrayList<TTitle_book>();

    public TFacade() {
    }

    public synchronized ArrayList<TTitle_book> getmTitle_books() {
        return mTitle_books;
    }

    public synchronized void setmTitle_books(ArrayList<TTitle_book> title_books){};

    public synchronized TTitle_book search_title_book(TTitle_book title_book) {
        int idx;
        if((idx = mTitle_books.indexOf(title_book))!=-1) {
        	return mTitle_books.get(idx);
        }
    	return null;
    }

    public synchronized TTitle_book add_title_book(String[] data) {
    	TFactory factory = new TFactory();
    	TTitle_book title_book = factory.create_title_book(data);
    	if(search_title_book(title_book)==null)
		{
    		mTitle_books.add(title_book);
		}
        return title_book;
    }

    public synchronized TTitle_book add_book(String[] data1, String[] data2) {
    	TFactory factory = new TFactory();
    	TTitle_book help1 = factory.create_title_book(data1);
    	TTitle_book title_exist;
    	if((title_exist = search_title_book(help1)) != null) {
    		title_exist.add_book(data2);
    	}
    	return title_exist;
    	
    }

    public synchronized TTitle_book Search_title_book(String[] data) {
        return null;
    }

    public synchronized TBook Search_book(String[] data1, String[] data2) {
        return null;
    }

    public synchronized TBook Search_accessible_book(String data1[], Object data2) {
        return null;
    }

    public synchronized ArrayList<String> gettitle_books() {
        return null;
    }

    public synchronized void Print_books() {
    }

    public synchronized void Print_title_books() {
        System.out.print("Titles of books");
        ArrayList<String> help_list = gettitle_books();
        for (int i = 0; i < help_list.size(); i++) {
            System.out.print(help_list.get(i));
        }
    }

    public static void main(String t[]) {
        TFacade ap = new TFacade();
        String t1[] = {"1", "Author1", "Title1", "ISBN1", "Publisher1"};
        String t2[] = {"1", "Author2", "Title2", "ISBN2", "Publisher2"};
        String t3[] = {"1", "Author3", "Title3", "ISBN3", "Publisher3"};
        String t4[] = {"3", "Author1", "Title1", "ISBN1", "Publisher1", "Actor1"};
        String t5[] = {"3", "Author2", "Title2", "ISBN2", "Publisher2", "Actor2"};
        String t6[] = {"3", "Author4", "Title4", "ISBN4", "Publisher4", "Actor4"};
        ap.add_title_book(t1);
        ap.add_title_book(t2);
        ap.add_title_book(t2);
        ap.add_title_book(t3);
        ap.add_title_book(t4);
        ap.add_title_book(t5);
        ap.add_title_book(t5);
        ap.add_title_book(t6);
        String lan = ap.getmTitle_books().toString();
        System.out.println(lan);
        
        String d4[] = {"2", "ISBN1", "Actor1"};
        //...
        ap.Print_title_books();
        ap.Print_books();
        System.out.print("\nSearching of a title");
        System.out.print(ap.Search_title_book(t5).toString());
        System.out.print("\nSearching of an accessible book of a select title");
        System.out.print(ap. Search_accessible_book(d4, "2").toString());
        System.out.println();
    }
}

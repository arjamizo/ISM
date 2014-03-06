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
import java.util.Iterator;
public class TFacade implements Serializable {
    private ArrayList<TTitle_book> mTitle_books = new ArrayList<TTitle_book>();

    public TFacade() {
    }

    public synchronized ArrayList<TTitle_book> getmTitle_books() {
        return mTitle_books;
    }

    public synchronized void setmTitle_books(ArrayList<TTitle_book> title_books) {
    };

    public synchronized TTitle_book search_title_book(TTitle_book title_book) {
        int idx;
        if ((idx = mTitle_books.indexOf(title_book)) != -1) {
//            System.out.println("found this book "+mTitle_books.get(idx)+" on index="+idx);
            return mTitle_books.get(idx);
        }
        return null;
    }

    public synchronized TTitle_book add_title_book(String[] data) {
        TFactory factory = new TFactory();
        TTitle_book title_book = factory.create_title_book(data);
        if (search_title_book(title_book) == null) {
//            System.out.println(">ADDING title "+title_book);
            mTitle_books.add(title_book);
        }
        return title_book;
    }
    /*
     * If book containing ISBN given in first argument does not exist, then add full info provided in second parameter. 
     */
    public synchronized TTitle_book add_book(String[] data1, String[] data2) {
        TFactory factory = new TFactory();
        TTitle_book help1 = factory.create_title_book(data1);
        TTitle_book title_exist;
        if ((title_exist = search_title_book(help1)) != null) {
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

    public synchronized TBook Search_accessible_book(String data1[],
            Object data2) {
        return null;
    }

    public synchronized ArrayList<String> gettitle_books() {
        ArrayList<String> title_books = new ArrayList<String>();
        Iterator<TTitle_book> iterator = mTitle_books.iterator();
        do {
            title_books.add(iterator.next().toString());
        } while (iterator.hasNext()); 
        return title_books;
    }

    public synchronized void Print_books() {
        System.out.print("\nBooks");
        for (int i = 0; i < mTitle_books.size(); i++) {
            ArrayList<String> help_list = mTitle_books.get(i).getbooks();
            for (int j = 0; j < help_list.size(); j++) {
                System.out.print(help_list.get(j).toString());
            }
        }
    }

    public synchronized void Print_title_books() {
        System.out.print("\nTitles of books");
        ArrayList<String> help_list = gettitle_books();
        for (int i = 0; i < help_list.size(); i++) {
            System.out.print(help_list.get(i));
        }
    }

    public static void main(String t[]) {
        TFacade ap = new TFacade();
        String t1[] = { "1", "Author1", "Title1", "ISBN1", "Publisher1" };
        String t2[] = { "1", "Author2", "Title2", "ISBN2", "Publisher2" };
        String t3[] = { "1", "Author3", "Title3", "ISBN3", "Publisher3" };
        String t4[] = { "3", "Author1", "Title1", "ISBN1", "Publisher1",
                "Actor1" };
        String t5[] = { "3", "Author2", "Title2", "ISBN2", "Publisher2",
                "Actor2" };
        String t6[] = { "3", "Author4", "Title4", "ISBN4", "Publisher4",
                "Actor4" };
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
        
        //second iteration testing starts here
        System.out.println("printing titiles");
        String d1[] = { "0", "ISBN1" };
        String d2[] = { "0", "ISBN2" };
        String d3[] = { "0", "ISBN5" };
        String d4[] = { "2", "ISBN1", "Actor1" };
        String d5[] = { "2", "ISBN4", "Actor4" };
        String tr1[] = { "0", "1" };
        String tr2[] = { "0", "2" };
        String tr3[] = { "1", "3", "3" };
        String tr4[] = { "1", "2", "-1" };
        TTitle_book pom = ap.add_book(d1, tr1);
        if (pom != null) {
            System.out.print(pom.getmBooks().toString());
        }
        pom = ap.add_book(d2, tr1);
        if (pom != null) {
            System.out.print(pom.getmBooks().toString());
        }
        pom = ap.add_book(d2, tr1);
        if (pom != null) {
            System.out.print(pom.getmBooks().toString());
        }
        pom = ap.add_book(d2, tr2);
        if (pom != null) {
            System.out.print(pom.getmBooks().toString());
        }
        pom = ap.add_book(d3, tr2);
        if (pom != null) {
            System.out.print(pom.getmBooks().toString());
        }
        pom = ap.add_book(d4, tr3);
        if (pom != null) {
            System.out.print(pom.getmBooks().toString());
        }
        pom = ap.add_book(d4, tr3);
        if (pom != null) {
            System.out.print(pom.getmBooks().toString());
        }
        pom = ap.add_book(d4, tr4);
        if (pom != null) {
            System.out.print(pom.getmBooks().toString());
        }
        pom = ap.add_book(d5, tr2);
        if (pom != null) {
            System.out.print(pom.getmBooks().toString());
        }
        System.out.println();

        //fourth iteration starts here
        ap.Print_title_books();
        ap.Print_books();
        System.err.println("\nOnly up to here it is possible to do tasks without having done third iteration.");
//        if(2==1+1) throw new RuntimeException("only up to here implementation is done");
//        System.out.print("\nSearching of a title");
//        System.out.print(ap.Search_title_book(t5).toString());
//        System.out.print("\nSearching of an accessible book of a select title");
//        System.out.print(ap. Search_accessible_book(d4, "2").toString());
//        System.out.println();
    }
}

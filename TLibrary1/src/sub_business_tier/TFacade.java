/*
 * TFacade.java
 *
 * Created on 2 marzec 2007, 22:44
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package sub_business_tier;

import sub_business_tier.entities.TBook;
import java.io.Serializable;
import java.util.ArrayList;
import sub_business_tier.entities.TTitle_book;

public class TFacade implements Serializable {

    private ArrayList<TTitle_book> mTitle_books = new ArrayList<TTitle_book>();

    public TFacade() {
    }

    public ArrayList<TTitle_book> getmTitle_books() {
        return mTitle_books;
    }

    public void setmTitle_books(ArrayList<TTitle_book> title_books) {
        mTitle_books = title_books;
    }

    public synchronized Object[][] gettitle_books() {
        Object[][] title_books = new Object[mTitle_books.size()][];
        int i = 0;
        for (TTitle_book next : mTitle_books) {
            String[] title = new String[5];
            title[0] = next.getPublisher();
            title[1] = next.getISBN();
            title[2] = next.getTitle();
            title[3] = next.getAuthor();
            title[4] = next.getActor();
            title_books[i++] = title;
        }
        return title_books;
    }

    public synchronized TTitle_book search_title_book(TTitle_book title_book) {
        int idx;
        if ((idx = mTitle_books.indexOf(title_book)) != -1) {
            title_book = mTitle_books.get(idx);
            return title_book;
        }
        return null;
    }

    public synchronized TTitle_book add_title_book(String data[]) {
        TFactory factory = new TFactory();
        TTitle_book title_book = factory.create_title_book(data);
        if (search_title_book(title_book) == null) {
            mTitle_books.add(title_book);
            return title_book;
        }
        return null;
    }

    public synchronized TTitle_book add_book(String data1[], String data2[]) {
        TTitle_book help1, help2 = null;
        TFactory fabryka = new TFactory();
        help1 = fabryka.create_title_book(data1);
        if ((help2 = search_title_book(help1)) != null) {
            help2.add_book(data2);
        }
        return help2;
    }

    public synchronized ArrayList<String> Search_title_book(String data[]) {
        TFactory factory = new TFactory();
        TTitle_book title_book = factory.create_title_book(data);
        TTitle_book title_book_ = search_title_book(title_book);
        if (title_book_ != null) {
            return title_book_.getbooks();
        }
        return null;
    }

    public synchronized TBook Search_accessible_book(String data1[], String data2) {
        TFactory factory = new TFactory();
        TTitle_book title_book_help = factory.create_title_book(data1);
        TTitle_book title_exist = search_title_book(title_book_help);
        if (title_exist != null) {
            return title_exist.search_accessible_book(data2);
        }
        return null;
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
        System.out.println("\n\nTitles of book");
        Object[][] help_list = gettitle_books();
        for (int i = 0; i < help_list.length; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(help_list[i][j] + " ");
            }
            System.out.println();
        }
    }

    public synchronized void update_data(TTitle_book titles[], TBook books[]) {
        mTitle_books.clear();
        for (TTitle_book t : titles) {
            mTitle_books.add(t);
        }
        for (TTitle_book title : mTitle_books) {
            for (TBook book : books) {
                TTitle_book title1 = book.getmTitle_book();
                if (title1 != null) {
                    if (title1.equals(title)) {
                        title.getmBooks().add(book);
                    }
                }
            }
        }
    }

    public static void main(String args[]) {
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
        String d1[] = {"0", "ISBN1"};
        String d2[] = {"0", "ISBN2"};
        String d3[] = {"0", "ISBN5"};
        String d4[] = {"2", "ISBN1", "Actor1"};
        String d5[] = {"2", "ISBN4", "Actor4"};
        String tr1[] = {"0", "1"};
        String tr2[] = {"0", "2"};
        String tr3[] = {"1", "3", "3"};
        String tr4[] = {"1", "2", "-1"};
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
        ap.Print_title_books();
        ap.Print_books();
        System.out.print("\nSearching of a title");
        System.out.print(ap.Search_title_book(t6));
        System.out.print("\nSearching of an accessible book of a select title");
        System.out.println(ap.Search_accessible_book(d4, "2").toString());
        System.out.println();
    }

    public synchronized ArrayList<String> Search_title_books(String[] data) {
        ArrayList<String> returnList = new ArrayList<String>();
        TFactory factory = new TFactory();
        TTitle_book title_book = factory.create_title_book(data);
        for (TTitle_book mTTitle_book : getmTitle_books()) {
//            if(title_book.compareTo(mTTitle_book)>0) {
            returnList.add(mTTitle_book.toString());
//            }
        }
        return returnList;
    }
}

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
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import sub_business_tier.entities.TLend;
import sub_business_tier.entities.TTitle_book;
import sub_business_tier.entities.TUser;

public class TFacade implements Serializable {

    private ArrayList<TTitle_book> mTitle_books = new ArrayList<TTitle_book>();
    private List<TUser> users = new ArrayList<TUser>();
    private List<TLend> borrows = new ArrayList<TLend>();

    public List<TUser> getUsers() {
        return users;
    }

    public void setUsers(List<TUser> users) {
        this.users = users;
    }

    public List<TLend> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<TLend> borrows) {
        this.borrows = borrows;
    }

    public void exampleData() {
        users.add(new TUser().setLogin("Madzia")); 
        String t1[] = {"1", "Author1", "Title1", "ISBN1", "Publisher1"};
        //TTitle_book title = 
                add_title_book(t1);
        
        String d1[] = {"0", "ISBN1"};
        String tr1[] = {"1", "1", "0"}; //1x0 oznacza, ze ksiazka jest do wypozyczenia (1) oraz dostepna od teraz (0)
        TBook book;
//        This might have some problems because of assumption thatq we obtain serialized (and deserialized by client) entity object.
        add_book(d1, tr1);//.getmBooks().listIterator().next();
        add_book(d1, new String[] {"0", "2"});//.getmBooks().listIterator().next();
        add_book(d1, new String[] {"1", "3", "0"});//.getmBooks().listIterator().next();
        book = search_title_book(new TFactory().create_title_book(d1)).getmBooks().get(0);
        
        borrows.add(new TLend().setBook(book).setUser(users.iterator().next()));
    }
    
    public TFacade() {
    }

    public ArrayList<TTitle_book> getmTitle_books() {
        return mTitle_books;
    }

    public void setmTitle_books(ArrayList<TTitle_book> title_books) {
        mTitle_books = title_books;
    }

    public synchronized Object[][] gettitle_books() {
        Object[][] title_books = new Object[getmTitle_books().size()][];
        int i = 0;
        for (TTitle_book next : getmTitle_books()) {
            String[] title = new String[5];
            title[0] = next.getAuthor();
            title[1] = next.getTitle();
            title[2] = next.getISBN();
            title[3] = next.getPublisher();
            title[4] = next.getActor();
            title_books[i++] = title;
        }
        return title_books;
    }

    public synchronized TTitle_book search_title_book(TTitle_book title_book) {
        int idx;
        LOG.info("searching among: "+getmTitle_books());
        if ((idx = getmTitle_books().indexOf(title_book)) != -1) {
            LOG.info("found at position "+idx);
            title_book = getmTitle_books().get(idx);
            return title_book;
        }
        return null;
    }

    public synchronized boolean add_title_book(String data[]) {
        TFactory factory = new TFactory();
        TTitle_book title_book = factory.create_title_book(data);
        if (search_title_book(title_book) == null) {
            getmTitle_books().add(title_book);
            return true;
        }
        return false;
    }

    public synchronized boolean add_book(String data1[], String data2[]) {
        TTitle_book help1, help2 = null;
        TFactory factory = new TFactory();
        LOG.info("add_book_with_parameters: "+(data1)+"; "+(data2));
        LOG.info("add_book_with_parameters: "+Arrays.asList(data1)+"; "+Arrays.asList(data2));
        help1 = factory.create_title_book(data1);
        LOG.info("help1: "+help1);
        if ((help2 = search_title_book(help1)) != null) {
            LOG.info("help2="+help2);
            help2.add_book(data2);
        }
//        return help2;
        return true;
    }
    private static final Logger LOG = Logger.getLogger(TFacade.class.getName());

    public synchronized ArrayList<String> Search_title_book(String data[]) {
        TFactory factory = new TFactory();
        TTitle_book title_book = factory.create_title_book(data);
        LOG.info("from factory: "+title_book);
        TTitle_book title_book_ = search_title_book(title_book);
        LOG.info("found: "+title_book_);
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
        for (int i = 0; i < getmTitle_books().size(); i++) {
            ArrayList<String> help_list = getmTitle_books().get(i).getbooks();
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

    public synchronized void update_data(TTitle_book titles[], TBook books[], TLend borrows[], TUser users[]) {
        getmTitle_books().clear();
        for (TTitle_book t : titles) {
            getmTitle_books().add(t);
        }
        for (TTitle_book title : getmTitle_books()) {
            for (TBook book : books) {
                TTitle_book title1 = book.getmTitle_book();
                if (title1 != null) {
                    if (title1.equals(title)) {
                        title.getmBooks().add(book);
                    }
                }
            }
        }
        try {
            setUsers(Arrays.asList(users));
            setBorrows(Arrays.asList(borrows));
        } catch (Exception e) {
            LOG.warning("something wrong with setting users and/or borrows");
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
        Boolean pom = ap.add_book(d1, tr1);
        //pom=pom.getmBooks()
        if (pom != null) {
            System.out.print(pom.toString());
        }
        pom = ap.add_book(d2, tr1);
        if (pom != null) {
            System.out.print(pom.toString());
        }
        pom = ap.add_book(d2, tr1);
        if (pom != null) {
            System.out.print(pom.toString());
        }
        pom = ap.add_book(d2, tr2);
        if (pom != null) {
            System.out.print(pom.toString());
        }
        pom = ap.add_book(d3, tr2);
        if (pom != null) {
            System.out.print(pom.toString());
        }
        pom = ap.add_book(d4, tr3);
        if (pom != null) {
            System.out.print(pom.toString());
        }
        pom = ap.add_book(d4, tr3);
        if (pom != null) {
            System.out.print(pom.toString());
        }
        pom = ap.add_book(d4, tr4);
        if (pom != null) {
            System.out.print(pom.toString());
        }
        pom = ap.add_book(d5, tr2);
        if (pom != null) {
            System.out.print(pom.toString());
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

    public void borrowBook(String[] bookTitle, String[] bookNumber, String client) {
        TTitle_book title = search_title_book(new TFactory().create_title_book(bookTitle));
        TBook book = title.search_book(new TFactory().create_book(bookNumber));
        if(book==null) 
            throw new RuntimeException("This book "+ Arrays.asList(bookNumber) + " was not found.");
        for (TLend tLend : borrows) {
            if (tLend.getBook().equals(book)) throw new RuntimeException("Book " + book + " can not be borrowed.");
        }
        LOG.info("borrows class: "+ borrows.getClass().getName());
        borrows.add(new TLend().setUser(new TUser().setLogin(client)).setBook(book));
        book.startPeriod("7");
    }

    public void returnBook(String[] bookTitle, String[] bookNumber) {
        TTitle_book title = search_title_book(new TFactory().create_title_book(bookTitle));
        TBook book = title.search_book(new TFactory().create_book(bookNumber));
        for (TLend tLend : this.borrows) {
            if (tLend.getBook().equals(book)) {
                borrows.remove(tLend);
                book.startPeriod("0");
                break;
            }
        }
    }

    public List<String> getClients() {
        final ArrayList<String> cls = new ArrayList<String>();
        for (TUser user : users) {
            cls.add(user.getLogin());
        }
        if (!cls.contains("Artur")) cls.add("Artur");
        if (!cls.contains("David")) cls.add("David");
        return cls;
    }

    public synchronized Object[][] getBooksWithBorrowers() {
        List<Object[]> title_books = new ArrayList();
        for(TTitle_book next2:getmTitle_books()) {
            for(TBook next:next2.getmBooks())
            {
//                if(filter!=null && !filter.call(next.getmTitle_book()).equals(true)) continue;
                String[] title = new String[8];
                title[0]=Integer.toString(next.getNumber());
                title[1]=next.getmTitle_book().getPublisher();
                title[2]=next.getmTitle_book().getISBN();
                title[3]=next.getmTitle_book().getTitle();
                title[4]=next.getmTitle_book().getAuthor();
                title[5]=next.getmTitle_book().getActor(); //If Book is not book on tape, then this field has null
                
                title[6] = title[7] = "";
                try {
                    title[7] = next.getPeriod().toString();
                } catch (Exception e) {
                    //Intentionally left empty. If date can not be obtained, then there is no date. 
                }
                for (TLend lend : borrows) {
                    try {
                        if(lend.getBook().equals(next)) {
                            title[6] = lend.getUser().getLogin();
                            title[7] = lend.getBook().getPeriod().toString();
                            break;
                        }
                    } catch (Exception e) {
                        
                    }
                }
                title_books.add(title);
            }
        }
        Object ret[][] = new Object[title_books.size()][];
        //n^2 way of rewriting 1d array. 
        for (Object str[] : title_books) {
            ret[title_books.indexOf(str)]=str; //inefficient, but short.
        }
        return ret;
    }

    public ArrayList<String> getBooksByTitle(String[] title) {
        try {
            return search_title_book(new TFactory().create_title_book(title)).getbooks();
        } catch (Exception ex) {
//            ex.printStackTrace();
            LOG.warning(ex.getMessage());
        }
        return new ArrayList();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sub_business_tier;

/**
 *
 * @author UML05
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;
import library_client_2014.UnaryOperator;

import sub_business_tier.entities.Client;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TBook_borrowed;
import sub_business_tier.entities.TTitle_book;
public class TFacade implements Serializable, FacadeInterface {
    private List<TTitle_book> mTitle_books = new ArrayList<TTitle_book>();
    //////////////to store a set of clients//////////////////////////
    private Vector<Client> clients = new Vector<Client>();
    private Vector<TBook_borrowed> books_b = new Vector<TBook_borrowed>(); 

    public TFacade() {
    }

    @Override
    public synchronized List<TTitle_book> getmTitle_books() {
        return mTitle_books;
    }

    @Override
    public synchronized void setmTitle_books(List<TTitle_book> title_books) {
        this.mTitle_books=title_books;
    };

    @Override
    public synchronized TTitle_book search_title_book(TTitle_book title_book) {
        int idx;
        if ((idx = getmTitle_books().indexOf(title_book)) != -1) {
            //mTitle_books.equals(title_book);
            return getmTitle_books().get(idx);
        }
        return null;
    }
	
    /***
    * @author zkruczkiewicz
    ***/
    @Override
    public synchronized Object[][] gettitle_books() {
        Object[][] title_books = new Object[getmTitle_books().size()][];
        int i=0;
        for(TTitle_book next:getmTitle_books())
        {
            String[] title = new String[5];
            title[0]=next.getPublisher();
            title[1]=next.getISBN();
            title[2]=next.getTitle();
            title[3]=next.getAuthor();
            title[4]=next.getActor(); //If Book is not book on tape, then this field has null
            title_books[i++]=title;
        }
        return title_books;
    }
    @Override
    public synchronized Object[][] getBooks(UnaryOperator filter) {
        LOG.info("getting books");
        List<Object[]> title_books = new ArrayList();
        int i=0;
        for(TTitle_book next2:getmTitle_books())
            for(TBook next:next2.getmBooks())
            {
                if(filter!=null && !filter.call(next.getmTitle_book()).equals(true)) continue;
                String[] title = new String[8];
                title[0]=Integer.toString(next.getNumber());
                title[1]=next.getmTitle_book().getPublisher();
                title[2]=next.getmTitle_book().getISBN();
                title[3]=next.getmTitle_book().getTitle();
                title[4]=next.getmTitle_book().getAuthor();
                title[5]=next.getmTitle_book().getActor(); //If Book is not book on tape, then this field has null
                try {
                    title[6]=next.getBorrower();
                } catch (Exception ex) {
                    title[6]="";
                }
                try {
                    title[7]=next.getPeriod().toString();
                } catch (Exception ex) {
                    title[7]="";
                }
                title_books.add(title);
            }
        Object ret[][] = new Object[title_books.size()][];
        for (Object str[] : title_books) {
            ret[title_books.indexOf(str)]=str; //inefficient, but short. 
        }
        return ret;
    }

    @Override
    public synchronized  TTitle_book add_title_book(String[] data) {
        TFactory factory = new TFactory();
        TTitle_book title_book = factory.create_title_book(data);
        if (search_title_book(title_book) == null) {
            getmTitle_books().add(title_book);
           return title_book;
        }
        return null;
    }
    /***
     * If book containing ISBN given in first argument does not exist, then add full info provided in second parameter.
     * @author artur 
     * @return created title book
     */
    @Override
    public synchronized TTitle_book add_book(String[] data1, String[] data2) {
        TFactory factory = new TFactory();
        TTitle_book help1 = factory.create_title_book(data1);
        TTitle_book title_exist;
        if ((title_exist = search_title_book(help1)) != null) {
//            System.out.println("\n->added book "+title_exist);
            title_exist.add_book(data2);
        } else {
//            System.out.println("\n-|did not add book, exists: "+title_exist);
        }
        return title_exist;

    }

    @Override
    public synchronized TTitle_book Search_title_book(String[] data) {
        TFactory factory = new TFactory();
        TTitle_book title_book = factory.create_title_book(data);
        return search_title_book(title_book);
    }
    @Override
    public synchronized ArrayList<String> Search_title_books(String[] data) {
        ArrayList<String> returnList = new ArrayList<>();
        TFactory factory = new TFactory();
        TTitle_book title_book = factory.create_title_book(data);
        for (TTitle_book mTTitle_book : getmTitle_books()) {
            if(title_book.compareTo(mTTitle_book)>0) {
                returnList.add(mTTitle_book.toString());
            }
        }
        return returnList;
    }
    
    /***
     *         String d4[] = { "2", "ISBN1", "Actor1" };
     *         String tr4[] = { "1", "2", "-1" };
     *         ap.Search_book(d4, tr4);
     *         first means , second arg data2 one means . 
     * @param data1 {CMD=0, ISBN} | {CMD=2, ISBN, Actor} title_book is created from this array. Object will be created only for searching, and later will be assigned as title with setMTitle_book
     * @param data2 {CMD=0, BOOK_NUMBER} | {CMD=1, BOOK_NUMBER, AVAILABLE_IN_N_DAYS} book has to have number=2 and was given back at least 1 days ago
     * @return
     */
    @Override
    public synchronized TBook Search_book(String[] data1, String[] data2) {
        TTitle_book titlebook_forsearching = new TFactory().create_title_book(data1);
        TTitle_book titlebook = search_title_book(titlebook_forsearching);
        if(titlebook==null)
            return null; //title does not even exist
        TBook book_details = new TFactory().create_book(data2);
        book_details.setmTitle_book(titlebook);
        return titlebook.search_book(book_details);
    }

    /***
     * 
     * @param data1
     * @param data2
     * @return 
     */
    @Override
    public synchronized TBook Search_accessible_book(String data1[],
            String data2) {
        TTitle_book title_book_help = new TFactory().create_title_book(data1);
        TTitle_book title_exist = search_title_book(title_book_help);
        if(title_exist!=null) {
            return title_exist.search_accessible_book(data2);
        }
        return null;
    }
    /**
     * Searches for borrowable instance of given book and returns it. 
     * @param data1 information about book title, e.g. new String[]{"0","ISBN"} or new String[]{"1", "ISBN", "Actor"}
     * @param data2 in how many days should book be available.
     * @return 
     */
    @Override
    public synchronized TBook Search_borrowable_book(String data1[],
            String data2) {
        TTitle_book title_book_help = new TFactory().create_title_book(data1);
        TTitle_book title_exist = search_title_book(title_book_help);
        if(title_exist!=null) {
            return title_exist.search_accessible_book(data2);
        }
        return null;
    }
    
    @Override
    public synchronized ArrayList<String> gettitle_books_arr() {
        ArrayList<String> title_books = new ArrayList<>();
        Iterator<TTitle_book> iterator = getmTitle_books().iterator();
        do {
            title_books.add(iterator.next().toString());
        } while (iterator.hasNext()); 
        return title_books;
    }

    @Override
    public synchronized void Print_books() {
        System.out.print("\nBooks");
        for (int i = 0; i < getmTitle_books().size(); i++) {
            ArrayList<String> help_list = getmTitle_books().get(i).getbooks();
            for (int j = 0; j < help_list.size(); j++) {
                System.out.print(help_list.get(j).toString());
            }
        }
    }

    @Override
    public synchronized void Print_title_books() {
        System.out.print("\nTitles of books");
        ArrayList<String> help_list = gettitle_books_arr();
        for (int i = 0; i < help_list.size(); i++) {
            System.out.print(help_list.get(i));
        }
    }

    
    ///////////////////////////////////////////////////////////////////////
    
    //						NEWS FOR LAB 2
    
    @Override
    public synchronized Client search_client(String login)
    {
    	for(int i=0;i<clients.size();i++)
    	{
    		if(clients.elementAt(i).getLogin().equals(login)) return clients.elementAt(i);
    	}
    	return null;
    }
    
    
    @Override
    public synchronized void add_client(String name)
    {
    	Client client = new Client(name);
    	clients.add(client);
    }
    
    
    @Override
    public synchronized void delete_client(String login)
    {
    	for(int i=0;i<clients.size();i++)
    	{
    		if(clients.elementAt(i).getLogin().equals(login)) clients.remove(i);
    	}
    }
    
    @Override
    public void Print_clients()
    {
    	System.out.println("Clients");
    	for(int i=0;i<clients.size();i++)
    	{
    		System.out.println(clients.elementAt(i));
    	}
    }
    
    @Override 
    public List<String> getClients() {
        List<String> ret=new ArrayList<>();
        for (Client client : clients) {
            ret.add(client.getLogin());
        }
        return ret;
    }
    
    @Override
    public synchronized String borrow_book(Client client, TBook book)
    {
        System.out.println("book:"+book);
        if(book!=null) { 
            if(book.getPeriod()!=null) {
        		//start period here
//        		books_b.add(new TBook_borrowed(client, book));
                        book.setBorrower(client.getLogin());
        		return "Book borrowed successfully";
        	}
        	else return "Book not available";
        }
    	return "Book not exists";
    }
    
    ///////////////////////////////////////////////////////
    
    
    
    public static void main(String t[]) {
        TFacade ap = new TFacade();
        
        fillWithExampleData(ap);
    }

    public static void fillWithExampleData(TFacade ap) {
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
        System.out.println("printing titles");
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
//        if(2==1+1) throw new RuntimeException("only up to here implementation is done");
        System.out.print("\nSearching of a title");
        System.out.print(ap.Search_title_book(t5).toString());
        System.out.print("\nSearching of an accessible book of a select title");
        System.out.print(ap.Search_accessible_book(d4, "2").toString());
        System.out.print("\nSearching for book. Found following books: ");
        System.out.print(ap.Search_title_books(new String[] { "2", "N4", "Actor4" }));
        System.out.println();
        
//      fifth iteration
        
        //add clients
        ap.clients.add(new Client("Client1"));
        ap.clients.add(new Client("Client2"));
        ap.clients.add(new Client("Client3"));
        ap.clients.add(new Client("Client4"));
        ap.clients.add(new Client("Client5"));
        ap.clients.add(new Client("Client6"));
        ap.clients.add(new Client("Client7"));
//        TBook_period period1 = new TBook_period();
//        period1.setPeriod(TFactory.mdays("-2")); 
//        ap.add_book(new String[]{ "0", "ISBN5" }, new String[]{ "1", "5", "-2" });//book was returned 2 days ago
//        ap.Search_book(data1, data2);
//        TBook recentlyAddedBook = ap.mTitle_books.get(ap.mTitle_books.size()-1);
        TBook recentlyAddedBook=ap.Search_book(d4, tr4);
        System.out.println("Borrowing some book, expecting it to be "+recentlyAddedBook);
        
        System.out.print(ap.borrow_book(new Client("Client1"), recentlyAddedBook));
    }

    private Client whoBorrowed(String data[]) {
        try {
            return findBorrowed(data).getClient();
        } catch (Exception ex){
            return null;
        }
    }

    @Override
    public void returnBook(String data_title[], String data_book[]) {
        TBook book = Search_book(data_title, data_book);
        book.setBorrower("");
    }

    @Override
    public void borrowBook(String data_title[], String data_book[], String client) {
//        LOG.info("datatitle: "+Arrays.asList(data_title)+Arrays.asList(data_book));
        TBook book = Search_book(data_title, data_book);
        if(book!=null) {
            Client cli;//=search_client(client);
            cli=new Client(client);
            borrow_book(cli, book);
        } else 
            throw new RuntimeException("Boook can not be borrowed, maybe does not exist in db or is not available ");
    }
    private static final Logger LOG = Logger.getLogger(TFacade.class.getName());

    private TBook_borrowed findBorrowed(String[] data) {
        for (TBook_borrowed tBook_borrowed : books_b) {
            if(tBook_borrowed.getBook().equals(new TFactory().create_book(data))) {
                return tBook_borrowed;
            }
        }
        return null;
    }
}

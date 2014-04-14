/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sub_business_tier.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.*;

import sub_business_tier.TFactory;

/**
 *
 * @author DavArt
 */
@Entity()
@Table(name = "TTitle")

@NamedQueries({
    @NamedQuery(name = "TTitle_book.findAll", query = "SELECT c FROM TTitle_book c")
})
public class TTitle_book implements Serializable, Comparable<Object> {

    private static final long serialVersionUID = 1L;
    private String publisher;
    private String ISBN;
    private String title;
    private String author;
    private List<TBook> mBooks = new java.util.ArrayList<TBook>();

    public TTitle_book() {
    }


    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(getISBN().equals(((TTitle_book)obj).getISBN())) {
            if(getActor().equals(((TTitle_book)obj).getActor())) {
                return true;
            } else
                return false;
        } else 
            return false;
    }
    
    @Override
    public int compareTo(Object obj) {
        if (obj == null) {
            return -1;
        }
//        if (getClass() != obj.getClass()) {
//            return -1;
//        }
        final TTitle_book otherBook = (TTitle_book) obj;
        String book1[]=new String[]{otherBook.getActor(),otherBook.getAuthor(),otherBook.getISBN(),otherBook.getPublisher(),otherBook.getTitle()};
        String book0[]=new String[]{getActor(),getAuthor(),getISBN(),getPublisher(),getTitle()};
        LOG.info("checking "+Arrays.asList(book0)+" for "+Arrays.asList(book1));
        for (int i = 0; i < book0.length; i++) {
            if(book0[i]!=null && !(book0[i]).equals("") && book0[i].toLowerCase().indexOf(book1[i].toLowerCase())!=-1)
                return i+1;
        }
        return -1;
    }
    private static final Logger LOG = Logger.getLogger(TTitle_book.class.getName());

    public void add_book(String[] data) {
    	TFactory factory = new TFactory();
    	TBook newbook = factory.create_book(data);
    	assert newbook!=null;
    	if(search_book(newbook)==null) {
    		getmBooks().add(newbook); // we store all books having the same title
    		newbook.setmTitle_book(this); // and we store the title of each book in itself
    	} else {
    	    System.out.print("");
    	}
    }

    public TBook search_book(TBook book) {
    	int idx;
    	if((idx = getmBooks().indexOf(book))!=-1)
    	{
    		return getmBooks().get(idx);
    	}
        return null;
    }
    
    /**
     * This should not be used for checking for borrowing. Use search_aaccessible_book_for_borrowing instead. 
     * @param data
     * @return 
     */
    public TBook search_accessible_book(String data) {
        for (Iterator<TBook> help = getmBooks().iterator(); help.hasNext();) {
            TBook help_book = (TBook) help.next();
            if (!help_book.period_pass(data)) {
                return help_book;
            }
        }
        return null;
    }
    /**
     * Iterates over book instances having this title and returns first book which is available for borrowing. 
     * @param data string expressing in-how-many-days book will be availabe. 
     * @return available book for borrowing
     */
    public TBook search_accessible_book_for_borrowing(String data) {
        for (Iterator<TBook> help = getmBooks().iterator(); help.hasNext();) {
            TBook help_book = (TBook) help.next();
            if (help_book.getPeriod()!=null && !help_book.period_pass(data)) {
                // checking for not-null skips all books, which are not for borrowing
                return help_book;
            }
        }
        return null;
    }

    public synchronized ArrayList<String> getbooks() {
        ArrayList<String> title_books = new ArrayList<String>();
        for (Iterator<TBook> help = getmBooks().iterator(); help.hasNext();) {
            TBook next = (TBook) help.next();
            title_books.add(next.toString());
        }
        return title_books;
    }
    
    @Column(name="Publisher")
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Column(name="ISBN")
    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Column(name="Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name="Author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    @Column(name="Actor")
    public String getActor() {
        return "";
    }
    public void setActor(String actor) {
        
    }

    @OneToMany(mappedBy = "mtitleBook", cascade = CascadeType.PERSIST)
    public List<TBook> getmBooks() {
        return mBooks;
    }

    public void setmBooks(List<TBook> mBooks) {
        this.mBooks = mBooks;
    }

    @Override
    public String toString() {
            return String.format("\nTitle: %s Author: %s ISBN: %s Publisher: %s", 
                            getTitle(), getAuthor(), getISBN(), getPublisher());
    }
    
    private Integer id;

    public void setId(Integer id) {
        this.id = id;
    }
    
    @Id
    @Column(name="id")
    @GeneratedValue
    public Integer getId() {
        return id;
    }

}
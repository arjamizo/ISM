/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sub_business_tier.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import sub_business_tier.TFactory;

/**
 *
 * @author DavArt
 */
public class TTitle_book implements Serializable, Comparable<Object> {

    private static final long serialVersionUID = 1L;
    private String publisher;
    private String ISBN;
    private String title;
    private String author;
    private ArrayList<TBook> mBooks = new java.util.ArrayList<TBook>();

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
    
    public int compareTo(Object obj) {
        if (obj == null) {
            return -1;
        }
        if (getClass() != obj.getClass()) {
            return -1;
        }
        final TTitle_book otherBook = (TTitle_book) obj;
        if(otherBook.getTitle()!=null && getTitle()!=null && otherBook.getTitle().indexOf(getTitle())!=-1) {
            return 1;
        }
        if(otherBook.getAuthor()!=null && getAuthor()!=null && otherBook.getAuthor().indexOf(getAuthor())!=-1) {
            return 2;
        }
        if(otherBook.getISBN()!=null && getISBN()!=null && otherBook.getISBN().indexOf(getISBN())!=-1) {
            return 3;
        }
        if(otherBook.getPublisher()!=null && getPublisher()!=null && otherBook.getPublisher().indexOf(getPublisher())!=-1) {
            return 4;
        }
        return 0;
    }

    public void add_book(String[] data) {
    	TFactory factory = new TFactory();
    	TBook newbook = factory.create_book(data);
    	assert newbook!=null;
    	if(search_book(newbook)==null) {
    		mBooks.add(newbook); // we store all books having the same title
    		newbook.setmTitle_book(this); // and we store the title of each book in itself
    	} else {
    	    System.out.print("");
    	}
    }

    public TBook search_book(TBook book) {
    	int idx;
    	if((idx = mBooks.indexOf(book))!=-1)
    	{
    		return mBooks.get(idx);
    	}
        return null;
    }

    public TBook search_accessible_book(String data) {
        for (Iterator<TBook> help = mBooks.iterator(); help.hasNext();) {
            TBook help_book = (TBook) help.next();
            if (!help_book.period_pass(data)) {
                return help_book;
            }
        }
        return null;
    }
    public TBook search_accessible_book_for_borrowing(String data) {
        for (Iterator<TBook> help = mBooks.iterator(); help.hasNext();) {
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
        for (Iterator<TBook> help = mBooks.iterator(); help.hasNext();) {
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

    @Id
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getActor() {
        return "";
    }

    @OneToMany(mappedBy = "mTitle_book")
    public ArrayList<TBook> getmBooks() {
        return mBooks;
    }

    public void setmBooks(ArrayList<TBook> mBooks) {
        this.mBooks = mBooks;
    }

	@Override
	public String toString() {
		return String.format("\nTitle: %s Author: %s ISBN: %s Publisher: %s", 
				getTitle(), getAuthor(), getISBN(), getPublisher());
	}

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package davart_lab01;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author DavArt
 */
public class TTitle_book implements Serializable {

    private static final long serialVersionUID = 1L;
    private String publisher;
    private String ISBN;
    private String title;
    private String author;
    private ArrayList<TBook> mBooks = new java.util.ArrayList<TBook>();

    public TTitle_book() {
    }

    public synchronized ArrayList<String> getbooks() {
        return new ArrayList<String>();
    }

    public int hashCode() {
        return 0;
    }

    public boolean equals(Object obj) {
        if(!this.getClass().equals(obj.getClass())) return false;
        TTitle_book title_book = (TTitle_book) obj;
        if(getISBN()==null) {
        	throw new RuntimeException("book is not valid in book "+toString());
        }
        if(getISBN().equals(title_book.getISBN())) {
//            if(get().equals(title_book.getAuthor())) {
//                System.out.println("comparing "+this+" to "+title_book+" ret true");
                return true;
//            }
        }
        return false;
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

    public TBook search_accessible_book(Object data) {
        return null;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

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
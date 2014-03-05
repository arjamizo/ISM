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
        return true;
    }

    public void add_book(String[] data) {
    }

    public TBook search_book(TBook book) {
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
}
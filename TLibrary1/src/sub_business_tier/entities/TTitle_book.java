package sub_business_tier.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;
import sub_business_tier.TFactory;

@Entity
@Table(name = "TTITLE_BOOK")

public class TTitle_book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ISBN")
    private String ISBN;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "PUBLISHER")
    private String publisher;
    @Column(name = "TITLE")
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "mTitle_book")
    private Collection<TBook> books;

    @XmlTransient
    public Collection<TBook> getBooks() {
        return books;
    }

    public void setBooks(Collection<TBook> books) {
        this.books = books;
    }

    @Transient
    private ArrayList<TBook> mBooks = new java.util.ArrayList<TBook>();

    public TTitle_book() {
        id = null;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public synchronized ArrayList<String> getbooks() {
        ArrayList<String> titleList = new ArrayList<String>();
        Iterator iterator = mBooks.iterator();
        while (iterator.hasNext()) {
            titleList.add(iterator.next().toString());
        }
        return titleList;
    }

    public String getActor() {
        return "";
    }

    public void setActor() {
    }

    public String toString() {
        return String.format("\nTitle: %s Author: %s ISBN: %s Publisher: %s",
                getTitle(), getAuthor(), getISBN(), getPublisher());
    }

    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        boolean result;
        TTitle_book other = (TTitle_book) obj;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        TTitle_book title_book = (TTitle_book) obj;
        if (getISBN().equals(title_book.getISBN())) {
            if (getActor().equals(title_book.getActor())) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void add_book(String[] data) {
        TFactory factory = new TFactory();
        TBook newbook = factory.create_book(data);
        if (search_book(newbook) == null) {
            mBooks.add(newbook);
            newbook.setmTitle_book(this);
        }
    }

    public TBook search_book(TBook book) {
        int idx;
        if ((idx = mBooks.indexOf(book)) != -1) {
            return mBooks.get(idx);
        }
        return null;
    }

    public TBook search_available_book(String days) {
        Iterator iterator = mBooks.iterator();
        while (iterator.hasNext()) {
            TBook help_book = (TBook) iterator.next();
            if (help_book.getPeriod() != null && !help_book.period_pass(days)) {
                return help_book;
            }
        }
        return null;
    }

    public TBook search_accessible_book(Object data) {
        for (Iterator<TBook> help = mBooks.iterator(); help.hasNext();) {
            TBook help_book = (TBook) help.next();
            if (!help_book.period_pass(data)) {
                return help_book;
            }
        }
        return null;
    }

}

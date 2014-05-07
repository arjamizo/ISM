package sub_business_tier.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.CascadeType;
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

    //private static final long serialVersionUID = new java.util.Random().nextLong();
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
    @OneToMany(mappedBy = "mTitle_book", cascade = CascadeType.PERSIST)
    private List<TBook> books = new ArrayList<TBook>();

    @XmlTransient
    public List<TBook> getBooks() {
        LOG.info("TTitle_book.books"+this.getTitle()+".size="+books.size());
        return books;
    }

    public void setBooks(List<TBook> books) {
        LOG.info("set.books.size="+books.size());
        this.books = books;
    }

//    @Transient
//    private ArrayList<TBook> mBooks = new java.util.ArrayList<TBook>();

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

    public List<TBook> getmBooks() {
        return getBooks();
    }

    public void setmBooks(ArrayList<TBook> mBooks) {
        setBooks(mBooks);
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
        Iterator iterator = getmBooks().iterator();
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
        return String.format("\nAuthor: %s Title: %s ISBN: %s Publisher: %s Number of books: %d",
                getAuthor(), getTitle(), getISBN(), getPublisher(), getBooks().size());
    }

    public int hashCode() {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
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
        LOG.info("add_book: created book: "+newbook);
        if (search_book(newbook) == null) {
            getBooks().add(newbook);
            newbook.setmTitle_book(this);
        }
    }
    private static final Logger LOG = Logger.getLogger(TTitle_book.class.getName());

    public TBook search_book(TBook book) {
        int idx;
        if ((idx = getmBooks().indexOf(book)) != -1) {
            return getmBooks().get(idx);
        } else {
        }
        return null;
    }

    public TBook search_available_book(String days) {
        Iterator iterator = getBooks().iterator();
        while (iterator.hasNext()) {
            TBook help_book = (TBook) iterator.next();
            if (help_book.getPeriod() != null && !help_book.period_pass(days)) {
                return help_book;
            }
        }
        return null;
    }

    public TBook search_accessible_book(Object data) {
        for (Iterator<TBook> help = getBooks().iterator(); help.hasNext();) {
            TBook help_book = (TBook) help.next();
            if (!help_book.period_pass(data)) {
                return help_book;
            }
        }
        return null;
    }

}

package sub_business_tier.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import sub_business_tier.TFactory;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UML05
 */
@Entity
@Table(name = "TBook")

@NamedQueries({
    @NamedQuery(name = "TBook.findAll", query = "SELECT c FROM TBook c")
})
public class TBook implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    @GeneratedValue
    private Integer id;
    @NotNull
    @Column(name = "NUMBER")
    private Integer number;
    @Column(name = "PERIOD")
    @Temporal(TemporalType.DATE)
    private Date period;
    @JoinColumn(name = "MTITLE_BOOK", referencedColumnName = "ID")
    @ManyToOne
    private TTitle_book mtitleBook;

    public TBook() {
    }

    public TBook(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public TTitle_book getMtitleBook() {
        return mtitleBook;
    }
    
    public void setMtitleBook(TTitle_book mtitleBook) {
        this.mtitleBook = mtitleBook;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (number != null ? number.hashCode() : 0);
        return hash;
    }

    
    public TTitle_book getmTitle_book() {
        return getMtitleBook();
    }

    public void setmTitle_book(TTitle_book title_book) {
        setMtitleBook(title_book);
    }

    /**
     * Basing on reverse engineering of STDOUT output, 
     * this method returns when book will be available for borrowing. 
     * (Note that book with earlier date was choosen as being available).
     * @return
     */
    @Override
    public String toString() {
        if(getmTitle_book()==null) {
            LOG.warning("book "+getNumber()+" does not have mTitle_book set.");
        }
        return getmTitle_book().toString() + " Number: " + getNumber();
    }
    private static final Logger LOG = Logger.getLogger(TBook.class.getName());

    public boolean equals(Object obj) {
    	//we assume that this is called only with TBook object, 
    	//therefore no instance of is needed
    	TBook book=(TBook)obj;
        return getNumber()==book.getNumber();
    }
    
    public boolean period_pass(String data) {
//        int acceptIfAvailableInNDaysFromNow = Integer.parseInt(data);
        if(getPeriod()==null) {
            //Strategy design patter (we use getPeriod() method, but we are not sure on whcih object we will do it - it depends on TBook) 
            return false; // in this case false means book is always inaccessible
        }
        return TFactory.mdays(data).compareTo(getPeriod())<=0; //it is important to compare <= because example says so. It shows that getPeriod returns date when book will be again available. 
    }
    
    public void startPeriod(Date date) {
        System.err.println("startingPeriod for this book "+ toString()+ " is not supported.");
//        throw new RuntimeException("not implemented");
    }
    @Column(name="Borrower")
    String borrower="";
        public String getBorrower() {
        return borrower;
    }
    
    public void setBorrower(String borrower) {
        this.borrower=borrower;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
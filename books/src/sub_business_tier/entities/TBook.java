package sub_business_tier.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;

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
    private int number;
    private TTitle_book mTitle_book;

    public TBook() {
    }

    @ManyToOne
    public TTitle_book getmTitle_book() {
        return mTitle_book;
    }

    public void setmTitle_book(TTitle_book title_book) {
        mTitle_book = title_book;
    }

    /**
     * Basing on reverse engineering of STDOUT output, 
     * this method returns when book will be available for borrowing. 
     * (Note that book with earlier date was choosen as being available).
     * @return
     */
    @Column(name = "PERIOD_time")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getPeriod() {
//        throw new RuntimeException("not implemented");
        return null;
    }

    public void setPeriod(Date period) {
//        throw new RuntimeException("not implemented");
    }

    @Id
    @Basic(optional = false)
    @Column(name = "NUMBER")
    public int getNumber() {
        return number;
    }

    public void setNumber(int val) {
        number=val;
    }

    public int hashCode() {
        throw new RuntimeException("not implemented");
    }

    @Override
    public String toString() {
        if(mTitle_book==null) {
            LOG.warning("book "+getNumber()+" does not have mTitle_book set.");
        }
        return mTitle_book.toString() + " Number: " + getNumber();
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
    @Column(name="Actor")
    public String getActor() {
        return "";
    }
    public void setActor(String actor) {
    }
}
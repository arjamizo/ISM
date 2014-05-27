package sub_business_tier.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class TBook implements Serializable {

    private static final long serialVersionUID = new java.util.Random().nextLong();
    private int number;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne
    private TTitle_book mTitle_book;
    
    @OneToOne(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private TLend lend;//=new TLend();

    public TLend getLend() {
        return lend;
    }

    public void setLend(TLend lend) {
        this.lend = lend;
    }

    public TBook() {
        id = null;
    }

    public TTitle_book getmTitle_book() {
        return mTitle_book;
    }

    public void setmTitle_book(TTitle_book title_book) {
        this.mTitle_book = title_book;
    }

    public Date getPeriod() {
        return null;
    }

    public void setPeriod(Date period) {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int val) {
        this.number = val;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TBook other = (TBook) obj;
        if (getNumber() != other.getNumber()) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.number;
        return hash;
    }

    public String toString() {
        return (mTitle_book!=null?mTitle_book.toString():"nullTtitle") + " Number: " + getNumber();
    }

    public boolean period_pass(String data) {
        return true;
    }

    public void startPeriod(String data) {
    }

}

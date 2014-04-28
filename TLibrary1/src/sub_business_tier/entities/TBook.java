package sub_business_tier.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TBook implements Serializable {

    private static final long serialVersionUID = 1L;
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
        return mTitle_book.toString() + " Number: " + getNumber();
    }

    public boolean period_pass(Object data) {
        return true;
    }

    public void startPeriod(Object data) {
    }

}

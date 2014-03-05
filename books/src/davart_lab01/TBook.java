package davart_lab01;

import java.io.Serializable;
import java.util.Date;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author UML05
 */
public class TBook implements Serializable {

    private static final long serialVersionUID = 1L;
    private int number;
    private TTitle_book mTitle_book;

    public TBook() {
    }

    public TTitle_book getmTitle_book() {
        return null;
    }

    public void setmTitle_book(TTitle_book title_book) {
    }

    public Date getPeriod() {
        return null;
    }

    public void setPeriod(Date period) {
    }

    public int getNumber() {
        return 0;
    }

    public void setNumber(int val) {
    }

    public int hashCode() {
        return 0;
    }

    public String toString() {
        return null;
    }

    public boolean equals(Object obj) {
        return true;
    }
    
    public boolean period_pass(Date date) {
        return true;
    }
    
    public void startPeriod(Date date) {
        
    }

}
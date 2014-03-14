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
    public Date getPeriod() {
//        throw new RuntimeException("not implemented");
        return null;
    }

    public void setPeriod(Date period) {
//        throw new RuntimeException("not implemented");
    }

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
            throw new RuntimeException("book "+getNumber()+" does not have mTitle_book set.");
        }
        return mTitle_book.toString() + " Number: " + getNumber();
    }

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

}
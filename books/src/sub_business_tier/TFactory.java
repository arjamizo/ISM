/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sub_business_tier;

import java.util.Arrays;
import java.util.Date;

import javax.management.RuntimeErrorException;

import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TBook_period;
import sub_business_tier.entities.TTitle_book;
import sub_business_tier.entities.TTitle_book_on_tape;

/**
 *
 * @author UML05
 */
public class TFactory {
    static final long day = 24 * 60 * 60 * 1000;

    static public Date mdays(String data) {
        int howManyDaysAgo = Integer.parseInt(data);
        
        long initialDate;
        initialDate=new Date().getTime();
//        initialDate=Date.parse("Wed Feb 13 18:51:45 CET 2011");
        return new Date(initialDate+howManyDaysAgo*day);
    }

    public TTitle_book create_title_book(String[] data) 
    {
    	TTitle_book title_book;
    	switch(Integer.parseInt(data[0]))
    	{
	    	case 0:	
	    		title_book = new TTitle_book();
	    		title_book.setISBN(data[1]);
//	    		System.out.println("created "+title_book+" for searching purposes.");
	    		return title_book;
	    	case 1:
	    		title_book = new TTitle_book();
	    		title_book.setAuthor(data[1]);
	    		title_book.setTitle(data[2]);
	    		title_book.setISBN(data[3]);
	    		title_book.setPublisher(data[4]);
//	    		System.out.println("created "+title_book+" for insertion purposes.");
	    		return title_book;
	    	case 2:
	    		TTitle_book_on_tape title_book1 = new TTitle_book_on_tape();
	    		title_book1.setISBN(data[1]);
	    		title_book1.setActor(data[2]);
//	    		System.out.println("created "+title_book1+" for searching purposes.");
	    		return title_book1;
	    	case 3:
	    		TTitle_book_on_tape title_book2 = new TTitle_book_on_tape();
	    		title_book2.setAuthor(data[1]);
	    		title_book2.setTitle(data[2]);
	    		title_book2.setISBN(data[3]);
	    		title_book2.setPublisher(data[4]);
	    		title_book2.setActor(data[5]);
//	    		System.out.println("created "+title_book2+" for insertion purposes.");
	    		return title_book2;
                default:
                        throw new RuntimeException("unknown method for creating title book");
    	}
    }

    @SuppressWarnings("deprecation")
    /***
     * creates book. If first argument [0] is 0, then TBook is created. If 
     * first arg [0] is 1, then borrowable TBook_period is created. 
     * [further arguments are number and in-how-many-days-book-will-be-available; 
     * negative, e.g. -1 means book is available since 1 day]
     */
	public TBook create_book(String[] data) 
    {
    	TBook tbook = null;
    	if(Integer.parseInt(data[0])==0)
    	{
    		tbook = new TBook();
    		tbook.setNumber(Integer.parseInt(data[1]));
    	}
    	else if(Integer.parseInt(data[0])==1)
    	{
    		tbook = new TBook_period();
    		tbook.setNumber(Integer.parseInt(data[1]));
                tbook.startPeriod(mdays(data[2]));
    	} else 
            throw new RuntimeException(data[0]+" is not proper parameter for create_book");
        return tbook;
    }
}

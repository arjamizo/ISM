/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package davart_lab01;

import java.util.Date;

import javax.management.RuntimeErrorException;

/**
 *
 * @author UML05
 */
public class TFactory {
    static final long day = 24 * 60 * 60 * 1000;

    static public Date mdays(String data) {
        return null;
    }

    public TTitle_book create_title_book(String[] data) 
    {
    	TTitle_book title_book;
    	switch(Integer.parseInt(data[0]))
    	{
	    	case 0:	
	    		title_book = new TTitle_book();
	    		title_book.setISBN(data[1]);
	    		return title_book;
	    	case 1:
	    		title_book = new TTitle_book();
	    		title_book.setAuthor(data[1]);
	    		title_book.setTitle(data[2]);
	    		title_book.setISBN(data[3]);
	    		title_book.setPublisher(data[4]);
	    		return title_book;
	    	case 2:
	    		TTitle_book_on_tape title_book1 = new TTitle_book_on_tape();
	    		title_book1.setISBN(data[1]);
	    		title_book1.setActor(data[2]);
	    		return title_book1;
	    	case 3:
	    		TTitle_book_on_tape title_book2 = new TTitle_book_on_tape();
	    		title_book2.setAuthor(data[1]);
	    		title_book2.setTitle(data[2]);
	    		title_book2.setISBN(data[3]);
	    		title_book2.setPublisher(data[4]);
	    		title_book2.setActor(data[5]);
	    		return title_book2;
			default:
				throw new RuntimeException("unknown method for creating title book");
    	}
    }

    @SuppressWarnings("deprecation")
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
    		tbook = new TBook();
    		tbook.setNumber(Integer.parseInt(data[1]));
    		int howManySecAgo = Integer.parseInt(data[2]);
    		tbook.startPeriod(new Date(new Date().getTime()+howManySecAgo*1000));
    	}
        return tbook;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.pwr;

import domainstore.TBookController;
import domainstore.TBookControllerAnnotation;
import domainstore.TTitle_bookController;
import domainstore.TTitle_bookControllerAnnotation;
import domainstore.util.EntityManagerProvider;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sub_business_tier.TFacade;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TTitle_book;

/**
 *
 * @author azochniak
 */
@Stateless
public class Facade extends TFacade implements FacadeRemote {
    /**
     * actually not used, but prooves existance of other possibility of accessing entities.
     */
//    @PersistenceContext(unitName = "booksPUJTA")
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }
    
    public Facade() {
        EntityManagerProvider emp;
        try {
            tBookController = new TBookController();
            tTitle_bookController = new TTitle_bookController();
            tTitle_bookController.getTTitle_books();
        } catch (Throwable e) {
            LOG.severe("Due to some problems on linux with RESOURCE_LOCAL, it is needed to uncomment @PersistanceContext annotation in Facade.java. ");
            tTitle_bookController = new TTitle_bookControllerAnnotation(emp=new EntityManagerProvider() {

                @Override
                public EntityManager getEm() {
                    return em;
                }
            });
            tBookController = new TBookControllerAnnotation(emp);
        }
        LOG.info("initialized");
    }
    private static final Logger LOG = Logger.getLogger(Facade.class.getName());

    @Override
    public synchronized void setmTitle_books(List<TTitle_book> title_books) {
        LOG.info("setting title books");
        throw new RuntimeException("can not call set mtitlebooks");
        //super.setmTitle_books(title_books);
    }

    @Override
    public synchronized List<TTitle_book> getmTitle_books() {
        LOG.info("fetching title books: WOW! that many "+tTitle_bookController.getTTitle_books().size());
//        for (TTitle_book tTitle_book : tTitle_bookController.getTTitle_books()) {
//            for (TBook tBook : tTitle_book.getmBooks()) {
//                LOG.warning("is processed? "+tBook.toString());
//            }
//        }
        //Caused by: java.lang.ClassCastException: sub_business_tier.entities.TTitle_book_on_tape cannot be cast to sub_business_tier.entities.TTitle_book
	//at pl.pwr.Facade.getmTitle_books(Facade.java:65)
        return (tTitle_bookController.getTTitle_books());
    }
    public TBookController tBookController;
    public TTitle_bookController tTitle_bookController;

    @Override
    public synchronized TTitle_book add_book(String[] data1, String[] data2) {
        LOG.info("Adding book.");
        TTitle_book title = super.add_book(data1, data2);
        TBook book=Search_book(data1, data2);
        tBookController.addTBook(book);
        return title;
    }

    @Override
    public synchronized TTitle_book add_title_book(String[] data) {
        LOG.info("Adding title.");
        TTitle_book title_book = super.add_title_book(data);
        tTitle_bookController.addTTitle_book(title_book);
        return title_book;
    }

    
}

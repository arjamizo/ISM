/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.pwr;

import integration_tier.TBase;
import integration_tier.TBookController;
import integration_tier.TBookControllerAnnotation;
import integration_tier.TTitle_bookController;
import integration_tier.TTitle_bookControllerAnnotation;
import integration_tier.util.EntityManagerProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
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
    @PersistenceContext(unitName = "booksPUJTA")
    private EntityManager em;
    
    TBase base;
    
    @PostConstruct
    public void init() {
        base = new TBase(this, em);
    }

    public EntityManager getEm() {
        return em;
    }
    
    public Facade() {
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
        LOG.info("fetching title books: WOW! that many "+super.getmTitle_books().size());
//        for (TTitle_book tTitle_book : tTitle_bookController.getTTitle_books()) {
//            for (TBook tBook : tTitle_book.getmBooks()) {
//                LOG.warning("is processed? "+tBook.toString());
//            }
//        }
        //Caused by: java.lang.ClassCastException: sub_business_tier.entities.TTitle_book_on_tape cannot be cast to sub_business_tier.entities.TTitle_book
	//at pl.pwr.Facade.getmTitle_books(Facade.java:65)
        return (super.getmTitle_books());
    }

    @Override
    public synchronized TTitle_book add_book(String[] data1, String[] data2) {
        LOG.info("Adding book.");
        TTitle_book title = super.add_book(data1, data2);
        TBook book=Search_book(data1, data2);
        em.persist(book); //should be done via Tbase?
        return title;
    }

    @Override
    public synchronized TTitle_book add_title_book(String[] data) {
        LOG.info("Adding title.");
        TTitle_book title_book = super.add_title_book(data);
        em.persist(title_book);//should be done via Tbase?
        return title_book;
    }

    @Override
    public synchronized void update_data(TTitle_book[] titles, TBook[] books) {

        getmTitle_books().clear();
        for (TTitle_book t : titles) {
            getmTitle_books().add(t);
        }
        for (TTitle_book title : getmTitle_books()) {
            for (TBook book : books) {
                TTitle_book title1 = book.getmTitle_book();
                if (title1
                        != null) {
                    if (title1.equals(title)) {
                        title.getmBooks().add(book);
                    }
                }
            }
        }
    }
    
    public void update_titles() throws Exception {
        base.update_titles();
    }

    public void update_books() throws Exception {
        base.update_books();
    }

    public void update_data() throws Exception {
        base.update_data();
    }

    public void add_titles() throws Exception {
        base.add_titles();
    }

    public void add_books() throws Exception {
        base.add_books();
    }

    public ArrayList<ArrayList<String>> titles() throws Exception {
        return base.titles();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.pwr;

import domainstore.TBookController;
import domainstore.TTitle_bookController;
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
    @PersistenceContext
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }
    
    public Facade() {
        tTitle_bookController = new TTitle_bookController(em);
        tBookController = new TBookController(em);
        LOG.info("initialized");
    }
    private static final Logger LOG = Logger.getLogger(Facade.class.getName());

    @Override
    public synchronized void setmTitle_books(List<TTitle_book> title_books) {
        LOG.info("setting title books");
        throw new RuntimeException("can not call set mtitlebooks");
        //super.setmTitle_books(title_books); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public synchronized List<TTitle_book> getmTitle_books() {
        TTitle_bookController.em=em;
        LOG.info("fetching title books");
        return (tTitle_bookController.getTTitle_books());
    }
    public TBookController tBookController;
    public TTitle_bookController tTitle_bookController;

    @Override
    public synchronized TTitle_book add_book(String[] data1, String[] data2) {
        TBookController.em=em;
        LOG.info("Adding book.");
        TTitle_book title = super.add_book(data1, data2);
        TBook book=Search_book(data1, data2);
        tBookController.addTBook(book);
        return title;
    }

    @Override
    public synchronized TTitle_book add_title_book(String[] data) {
        TTitle_bookController.em=em;
        LOG.info("Adding title.");
        TTitle_book title_book = super.add_title_book(data);
        tTitle_bookController.addTTitle_book(title_book);
        return title_book;
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domainstore;

import domainstore.util.EntityManagerProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.*;
import sub_business_tier.entities.TTitle_book;

/**
 *
 * @author azochniak
 */
public class TTitle_bookController {

    private EntityManagerFactory emf = null;
    
    public static EntityManagerProvider em;

    public TTitle_bookController(EntityManagerProvider em) {
        this.em=em;
    }

    private EntityManager getEntityManager() {
        if(em!=null) 
            return em.getEm(); 
        else 
            throw new RuntimeException("no em in ttitlbebook_controller");
    }
    public boolean addTTitle_book(TTitle_book title_book) {
        EntityManager em = getEntityManager();
        try {
            em.persist(title_book);
            LOG.fine("creating "+title_book);
        } catch (Exception e) {
            LOG.severe(e.getMessage());
            throw new RuntimeException(e.getMessage());
        } finally {
            return false;
        }
    }
    public boolean addTTitle_books(List<TTitle_book> titles) {
        EntityManager em = getEntityManager();
        TTitle_book newTTitle_book = null;
        try {
            Iterator it = titles.iterator();
            while (it.hasNext()) {
                newTTitle_book = (TTitle_book) it.next();
                if (newTTitle_book.getId() == null) {
                    em.persist(newTTitle_book);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            return false;
        }
    }
    public TTitle_book[] getTTitle_books_() {
        return (TTitle_book[]) getTTitle_books().toArray(new TTitle_book[0]);
    }

    public List<TTitle_book> getTTitle_books() {
        LOG.info("logging");
        
        EntityManager em = getEntityManager();
        try {
            javax.persistence.Query q = em.createNamedQuery("TTitle_book.findAll", TTitle_book.class);
            List ret = q.getResultList();
            LOG.info("Fetched "+ret.size() + " titles.");
            return ret;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
        }
    }
    private static final Logger LOG = Logger.getLogger(TTitle_bookController.class.getName());
}

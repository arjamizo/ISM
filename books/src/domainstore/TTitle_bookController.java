/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domainstore;

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

    protected EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("Library1PU");
        }
        return emf.createEntityManager();
    }
    public boolean addTTitle_book(TTitle_book title_book) {
        EntityManager em = getEntityManager();
        try {
            if(emf!=null)
                em.getTransaction().begin();
            em.persist(title_book);
            if(emf!=null)
                em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(emf!=null)
            em.close();
            return false;
        }
    }
    public boolean addTTitle_books(List<TTitle_book> titles) {
        EntityManager em = getEntityManager();
        TTitle_book newTTitle_book = null;
        try {
            Iterator it = titles.iterator();
            if(emf!=null)
                em.getTransaction().begin();
            while (it.hasNext()) {
                newTTitle_book = (TTitle_book) it.next();
                if (newTTitle_book.getId() == null) {
                    em.persist(newTTitle_book);
                }
            }
            if(emf!=null)
                em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(emf!=null)
            em.close();
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
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            if(emf!=null)
                em.close();
        }
    }
    private static final Logger LOG = Logger.getLogger(TTitle_bookController.class.getName());
}

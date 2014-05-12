/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integration_tier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sub_business_tier.entities.TTitle_book;


public class TTitle_bookController {

    private EntityManagerFactory emf = null;
    private EntityManager em = null;

    public void setEm(Object em) {
        this.em = (EntityManager) em;
    }

    private EntityManager getEntityManager() {
        if(em!=null) 
            return em;
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("Library1PU");
        }
        return emf.createEntityManager();
    }

    public boolean addTTitle_book(TTitle_book title_book) {
        EntityManager em = getEntityManager();
        try {
            if(this.em==null) em.getTransaction().begin();
            em.merge(title_book);
            if(this.em==null) em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(this.em==null) em.close();
            return false;
        }
    }

    public boolean addTTitle_books(ArrayList<TTitle_book> titles) {
        EntityManager em = getEntityManager();
        TTitle_book newTTitle_book = null;
        try {
            Iterator it = titles.iterator();
            if(this.em==null) em.getTransaction().begin();
            while (it.hasNext()) {
                newTTitle_book = (TTitle_book) it.next();
                if (newTTitle_book.getId() == null) {
                    em.merge(newTTitle_book);
                }
            }
            if(this.em==null) em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(this.em==null) em.close();
            return false;
        }
    }

    public TTitle_book[] getTTitle_books_() {
        final List<TTitle_book> list = getTTitle_books();
        LOG.info("list contains "+list + " size=" + list.size());
        TTitle_book[] ret=new TTitle_book[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ret[i]=list.get(i);
        }
        LOG.info("list contains "+list);
        LOG.info("arr[] contains "+list.toArray(new TTitle_book[0]));
        return ret;
//        return list.toArray(new TTitle_book[0]);
    }
    private static final Logger LOG = Logger.getLogger(TTitle_bookController.class.getName());

    public List<TTitle_book> getTTitle_books() {
        EntityManager em = getEntityManager();
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            javax.persistence.Query q
                    = em.createQuery("select c from TTitle_book as c");
            return q.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(this.em==null) em.close();
        }
    }
}

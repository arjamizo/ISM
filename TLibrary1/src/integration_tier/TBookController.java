/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package integration_tier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TTitle_book;

/**
 *
 * @author azochniak
 */
public class TBookController {

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

    public TBook[] getTBooks_() {
        return (TBook[]) getTBooks().toArray(new TBook[0]);
    }

    public List<TBook> getTBooks() {
        EntityManager em = getEntityManager();
        em.getEntityManagerFactory().getCache().evictAll();
        try {
            javax.persistence.Query q = em.createQuery("select c from TBook as c");
            return q.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(this.em==null) em.close();
        }
    }

    public boolean addTBook(TBook book) {
        EntityManager em = getEntityManager();
        try {
            if(this.em==null) em.getTransaction().begin();
            em.persist(book);
            if(this.em==null) em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(this.em==null) em.close();
            return false;
        }
    }

    public boolean addTBooks(ArrayList<TTitle_book> titles) {
        EntityManager em = getEntityManager();
        TBook newBook = null;
        Iterator it = titles.iterator();
        if(this.em==null) em.getTransaction().begin();
        try {
            while (it.hasNext()) {
                TTitle_book newTitle_book = (TTitle_book) it.next();
                if (newTitle_book.getId() == null) {
                    continue;
                }
                Iterator it_ = newTitle_book.getmBooks().iterator();
                while (it_.hasNext()) {
                    newBook = (TBook) it_.next();
                    if (newBook.getId() == null) {
                        em.persist(newBook);
                    }
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

}

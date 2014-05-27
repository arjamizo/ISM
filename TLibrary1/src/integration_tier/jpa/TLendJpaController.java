/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package integration_tier.jpa;

import integration_tier.jpa.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sub_business_tier.entities.TLend;
import sub_business_tier.entities.TTitle_book;
import sub_business_tier.entities.TUser;

/**
 *
 * @author azochniak
 */
public class TLendJpaController implements Serializable {

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
    
    public TLendJpaController() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void create(TLend tLend) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            if(this.em==null) em.getTransaction().begin();
            em.persist(tLend);
            if(this.em==null) em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (em != null) {
                if(this.em==null) em.close();
            }
        }
    }

    public void edit(TLend TLend) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            if(this.em==null) em.getTransaction().begin();
            em.persist(TLend);
            if(this.em==null) em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = TLend.getId();
                if (findTLend(id) == null) {
                    throw new NonexistentEntityException("The tLend with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                if(this.em==null) em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            if(this.em==null) em.getTransaction().begin();
            TLend TLend;
            try {
                TLend = em.getReference(TLend.class, id);
                TLend.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TLend with id " + id + " no longer exists.", enfe);
            }
            em.remove(TLend);
            if(this.em==null) em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (em != null) {
                if(this.em==null) em.close();
            }
        }
    }

    public List<TLend> findTLendEntities() {
        return findTLendEntities(true, -1, -1);
    }

    public List<TLend> findTLendEntities(int maxResults, int firstResult) {
        return findTLendEntities(false, maxResults, firstResult);
    }

    private List<TLend> findTLendEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        em.getEntityManagerFactory().getCache().evictAll();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TLend.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(this.em==null) em.close();
        }
    }

    public TLend findTLend(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TLend.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(this.em==null) em.close();
        }
    }

    public int getTLendCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TLend> rt = cq.from(TLend.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(this.em==null) em.close();
        }
    }

    public void addLends(List<TLend> borrows) {
        LOG.info("EXECUTING: addLends");
        EntityManager em = getEntityManager();
        try {
            if(this.em==null) em.getTransaction().begin();
            for (TLend lend : borrows) {
                    LOG.info("adding "+lend);
                    em.persist(lend);
            }
            if(this.em==null) em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(this.em==null) em.close();
        }
    }
    private static final Logger LOG = Logger.getLogger(TLendJpaController.class.getName());

    public boolean addLend(TLend lend) {
        EntityManager em = getEntityManager();
        try {
            if(this.em==null) em.getTransaction().begin();
            em.persist(lend);
            if(this.em==null) em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(this.em==null) em.close();
            return false;
        }
    }

    public TLend[] getTLends_() {
       return findTLendEntities().toArray(new TLend[0]); //To change body of generated methods, choose Tools | Templates.
    }

    public void removeLend(TLend lend) throws NonexistentEntityException {
        destroy(lend.getId());
    }

}

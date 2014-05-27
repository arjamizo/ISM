/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package integration_tier.jpa;

import integration_tier.jpa.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sub_business_tier.entities.TUser;

/**
 *
 * @author azochniak
 */
public class TUserJpaController implements Serializable {

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
    
    public void create(TUser TUser) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            if(this.em==null) em.getTransaction().begin();
            em.persist(TUser);
            if(this.em==null) em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TUser TUser) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            if(this.em==null) em.getTransaction().begin();
            em.persist(TUser);
            if(this.em==null) em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = TUser.getId();
                if (findTUser(id) == null) {
                    throw new NonexistentEntityException("The tUser with id " + id + " no longer exists.");
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
            TUser TUser;
            try {
                TUser = em.getReference(TUser.class, id);
                TUser.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The TUser with id " + id + " no longer exists.", enfe);
            }
            em.remove(TUser);
            if(this.em==null) em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (em != null) {
                if(this.em==null) em.close();
            }
        }
    }

    public List<TUser> findTUserEntities() {
        return findTUserEntities(true, -1, -1);
    }

    public List<TUser> findTUserEntities(int maxResults, int firstResult) {
        return findTUserEntities(false, maxResults, firstResult);
    }

    private List<TUser> findTUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        em.getEntityManagerFactory().getCache().evictAll();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TUser.class));
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

    public TUser findTUser(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TUser.class, id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(this.em==null) em.close();
        }
    }

    public int getTUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TUser> rt = cq.from(TUser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(this.em==null) em.close();
        }
    }

    public void addUsers(List<TUser> users) {
        for (TUser user : users) {
            addUser(user);
        }
    }

    public boolean addUser(TUser user) {
        EntityManager em = getEntityManager();
        try {
            if(this.em==null) em.getTransaction().begin();
            em.persist(user);
            if(this.em==null) em.getTransaction().commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(this.em==null) em.close();
            return false;
        }
    }

    public TUser[] getTUsers_() {
       return findTUserEntities().toArray(new TUser[0]);
    }
    
}

package domainstore;

/**
 *
 * @author azochniak
 */
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TTitle_book;

public class TBookController {

    private EntityManagerFactory emf = null;

    protected EntityManager getEntityManager() {
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
        try {
            javax.persistence.Query q = em.createQuery("select c from TBook as c");
            List ret = q.getResultList();
            LOG.info("Fetched "+ret.size() + " titles.");
            return ret;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(emf!=null)
                em.close();
        }
    }
    private static final Logger LOG = Logger.getLogger(TBookController.class.getName());
    
    public boolean addTBook(TBook book) {
        EntityManager em = getEntityManager();
        try {
            if(emf!=null)
                em.getTransaction().begin();
            em.persist(book);
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
    public boolean addTBooks(List<TTitle_book> titles) {
        EntityManager em = getEntityManager();
        TBook newBook = null;
        Iterator it = titles.iterator();
        if(emf!=null)
                em.getTransaction().begin();
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
} // end of TBookController

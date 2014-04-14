package domainstore;

/**
 *
 * @author azochniak
 */
import domainstore.util.EntityManagerProvider;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import sub_business_tier.entities.TBook;
import sub_business_tier.entities.TTitle_book;

public class TBookControllerAnnotation extends TBookController {

    private EntityManagerFactory emf = null;

    public static EntityManagerProvider em;

    public TBookControllerAnnotation(EntityManagerProvider em) {
        this.em=em;
    }

    @Override
    protected EntityManager getEntityManager() {
        if(em!=null) 
            return em.getEm(); 
        else 
            throw new RuntimeException("no em in tbook_controller");
    }
    
} // end of TBookControllerAnnotation

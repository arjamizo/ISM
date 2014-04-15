package pl.pwr;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sub_business_tier.TFacade;

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
}

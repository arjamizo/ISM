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
public class TTitle_bookControllerAnnotation extends TTitle_bookController {

    private EntityManagerFactory emf = null;
    
    public static EntityManagerProvider em;

    public TTitle_bookControllerAnnotation(EntityManagerProvider em) {
        this.em=em;
    }

    @Override
    protected EntityManager getEntityManager() {
        if(em!=null) 
            return em.getEm(); 
        else 
            throw new RuntimeException("no em in ttitlbebook_controller");
    }
}

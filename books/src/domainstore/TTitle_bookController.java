/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domainstore;

import javax.persistence.*;

/**
 *
 * @author azochniak
 */
public class TTitle_bookController {

    private EntityManagerFactory emf = null;

    private EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("booksPU");
        }
        return emf.createEntityManager();
    }
}

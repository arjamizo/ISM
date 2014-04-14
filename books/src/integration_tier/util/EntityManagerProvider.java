/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package integration_tier.util;

import javax.persistence.EntityManager;

/**
 *
 * @author azochniak
 */
public interface EntityManagerProvider {
    public EntityManager getEm();
}

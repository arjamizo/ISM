/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.pwr;

import javax.ejb.Stateless;
import sub_business_tier.TFacade;

/**
 *
 * @author azochniak
 */
@Stateless
public class Facade implements FacadeRemote {
    
    @Override
    public TFacade getFacade() {
        return null;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}

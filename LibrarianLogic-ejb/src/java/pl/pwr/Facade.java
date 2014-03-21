/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.pwr;

import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import sub_business_tier.TFacade;

/**
 *
 * @author azochniak
 */
@Stateless
public class Facade implements FacadeRemote {

    private TFacade facade = new TFacade();    
    
    @Override
    public TFacade getFacade() {
        Logger.getLogger(Facade.class.getName()).info("executed!"+new Date().toString());
        return facade;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}

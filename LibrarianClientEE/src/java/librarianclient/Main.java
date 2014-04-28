/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package librarianclient;

import java.util.logging.Logger;
import javax.ejb.EJB;
import library_client_2014.Client;
import pl.pwr.remote.FacadeRemote;

/**
 *
 * @author azochniak
 */
public class Main {

    /*
        This injection was causing error: 
            Exception attempting to inject Remote ejb-ref
        Solution:
            add _static_ keyword to property
    */
    @EJB
    private static FacadeRemote facade;

    static public FacadeRemote getFacade() {
        return facade;
    }
    /**
     * This is just a wrapper to Client::main. It makes managing projects easier. 
     * @param args the command line arguments
     */

    public static void main(String[] args) {
//        LOG.log(Level.INFO, "Using facade: {0}", facade.toString());
        Client.setFacadeStatic(facade);
        Client.main(args);
    }
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    
}

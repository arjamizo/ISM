/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package librarianclient;

import business_tire.FacadeRemote;
import java.util.logging.Logger;
import javax.ejb.EJB;
import library_client_2014.Client;

/**
 *
 * @author azochniak
 */
public class Main {

    /*
    Problem:
        This injection was causing error: 
        Exception attempting to inject Remote ejb-ref
    Solution:
        add _static_ keyword to property
            
    Problem:
        There was problem allowing deploying app-client separately, but due to error https://java.net/jira/browse/GLASSFISH-16181?focusedCommentId=310179&page=com.atlassian.jira.plugin.system.issuetabpanels%3Acomment-tabpanel#action_310179 it was not possible to deploy in EAR. 
    Solution: 
        Fixed by moving all client classes to EE's-client's project (previously everything was in separate SE'S-client's project)
    */
    @EJB// (lookup = "java:global/LibrarianEAR/LibrarianLogic-ejb/Facade!pl.pwr.remote.FacadeRemote")
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.pwr.web;

import integration_tier.TTitle_bookController;
import java.util.Arrays;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author azochniak
 */
@WebService(serviceName = "NewWebService")
@Stateless()
public class FacadeWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !" 
                + Arrays.asList(new TTitle_bookController().getTTitle_books_())
                ;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package davart_lab01;

import java.io.Serializable;

/**
 *
 * @author UML05
 */
class TTitle_book_on_tape extends TTitle_book implements Serializable {
    
    private String actor;

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    
    
}

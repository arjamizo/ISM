/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sub_business_tier.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author UML05
 */
public class TTitle_book_on_tape extends TTitle_book implements Serializable {
    
    private String actor;

    @Override
    public String getActor() {
        //in opposite to TTitle_book::getActor(), this returns not-null because audio-book has actor acting as a lector
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
    
    @Override
    public String toString() {
        return super.toString() + " Actor: "+getActor();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.actor);
        return hash;
    }    
    
}

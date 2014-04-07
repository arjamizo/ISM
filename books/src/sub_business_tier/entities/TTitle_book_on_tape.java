/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sub_business_tier.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author UML05
 */
@Entity
@Table(name="TTitle_book_on_tape")
public class TTitle_book_on_tape extends TTitle_book implements Serializable {
    
    private String actor;

    @Override
    public String getActor() {
        //in opposite to TTitle_book::getActor(), this returns not-null because audio-book has actor acting as a lector
        return actor;
    }

    @Override
    public void setISBN(String ISBN) {
        super.setISBN(ISBN); //To change body of generated methods, choose Tools | Templates.
    }

    @Id
    @Override
    public String getISBN() {
        return super.getISBN(); //To change body of generated methods, choose Tools | Templates.
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

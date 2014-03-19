/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sub_business_tier.entities;

import java.io.Serializable;

/**
 *
 * @author UML05
 */
public class TTitle_book_on_tape extends TTitle_book implements Serializable {
    
    private String actor;

    @Override
    public String getActor() {
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
    public boolean equals(Object obj) {
        if(!getClass().equals(obj.getClass())) return false;
        if(obj instanceof TTitle_book_on_tape) {
//            System.out.println("comparing using ONTAPE operator");
            TTitle_book_on_tape tape = (TTitle_book_on_tape)obj;
            return super.equals(obj) && this.getActor().equals(tape.getActor());
        }
//        System.out.println("comparing using regular operator");
        return !(obj instanceof TTitle_book_on_tape) && super.equals(obj);
    }
    
}

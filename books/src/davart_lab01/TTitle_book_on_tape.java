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

package sub_business_tier.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 * @author kruczkiewicz
 */
@Entity
public class TTitle_book_on_tape extends TTitle_book implements Serializable {

    private static final long serialVersionUID = 1L;

    private String actor;

    public String getActor() {
        return actor;
    }

    public void setActor(String val) {
        actor = val;
    }

    @Override
    public String toString() {
        return super.toString() + " Actor: " + getActor();
    }

}

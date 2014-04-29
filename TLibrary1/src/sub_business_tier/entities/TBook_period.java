package sub_business_tier.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import sub_business_tier.TFactory;

@Entity
public class TBook_period extends TBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date periodDate;

    public Date getPeriod() {
        return periodDate;
    }

    public void setPeriod(Date date) {
        this.periodDate = date;
    }

    public boolean period_pass(Object data) {
        return true;
    }

    public void startPeriod(String data) {
        this.periodDate = TFactory.mdays(data.toString());
    }

    public String toString() {
        return super.toString() + " Period: " + periodDate;
    }

}

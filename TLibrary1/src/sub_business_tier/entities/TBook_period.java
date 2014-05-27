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

    public void startPeriod(String data) {
        this.periodDate = TFactory.mdays(data.toString());
    }

    public String toString() {
        return super.toString() + " Period: " + periodDate;
    }
    
    
    @Override
    public boolean equals(Object obj) {
           //we assume that this is called only with TBook object, 
           //therefore no instance of is needed
           TBook book=(TBook)obj;
        return getNumber()==book.getNumber();
    }

    @Override
    public boolean period_pass(String data) {
        if(getPeriod()==null) {
            //Strategy design patter (we use getPeriod() method, but we are not sure on whcih object we will do it - it depends on TBook) 
            return false; // in this case false means book is always inaccessible
        }
        return TFactory.mdays(data).compareTo(getPeriod())<=0; //it is important to compare <= because example says so. It shows that getPeriod returns date when book will be again available. 
    }

}

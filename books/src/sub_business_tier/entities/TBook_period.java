package sub_business_tier.entities;

import java.io.Serializable; 
import java.util.Date;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author azochniak
 */
@Entity
@Table(name = "TTitle_period")
public class TBook_period extends TBook {

	private Date period;

       @Override
       @Column(name = "PERIOD_")
       @Temporal(javax.persistence.TemporalType.DATE)
	public Date getPeriod() {
           return super.getPeriod(); //To change body of generated methods, choose Tools | Templates.
	}

       @Override
	public void setPeriod(Date date) {
		period = date;
	}

       @Override
	public String toString() {
		return super.toString() + " Period: " + getPeriod();
	}

	public boolean period_pass(Object data) {
		return true;
	}

       @Override
	public void startPeriod(Date date) {
		period = date;
	}
	
}

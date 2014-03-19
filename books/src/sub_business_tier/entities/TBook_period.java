package sub_business_tier.entities;

import java.io.Serializable; 
import java.util.Date;

public class TBook_period extends TBook implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date period;

	public Date getPeriod() {
		return period;
	}

	public void setPeriod(Date date) {
		period = date;
	}

	public String toString() {
		return super.toString() + " Period: " + getPeriod();
	}

	public boolean period_pass(Object data) {
		return true;
	}

	public void startPeriod(Date date) {
		period = date;
	}
	
}

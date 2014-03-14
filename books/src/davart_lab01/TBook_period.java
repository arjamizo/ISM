package davart_lab01;

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

	public boolean period_pass(String data) {
        return TFactory.mdays(data).compareTo(getPeriod())<=0; //it is important to compare <= because example says so. It shows that getPeriod returns date when book will be again available.
	}

	public void startPeriod(Date date) {
		period = date;
	}
	
}

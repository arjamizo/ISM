package davart_lab01;

import java.io.Serializable; 
import java.util.Date;

public class TBook_period extends TBook implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date period;
	public Date getPeriod () { return null; }
	public void setPeriod (Date date) { }
	public String toString () { return null; }
	public boolean period_pass(Object data)
	{ return true; }
	public void startPeriod(Date data)
	{
		
	}
}

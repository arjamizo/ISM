package davart_lab01;

public class TBook_borrowed {

	private TBook_period period;
	private Client client;
	private TTitle_book book;
	
	
	
	public TBook_borrowed(TBook_period period, Client client, TTitle_book book) {
		super();
		this.period = period;
		this.client = client;
		this.book = book;
	}
	
	
	public TBook_period getPeriod() {
		return period;
	}
	public void setPeriod(TBook_period period) {
		this.period = period;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public TTitle_book getBook() {
		return book;
	}
	public void setBook(TTitle_book book) {
		this.book = book;
	}
	
	
	
	
	
	
	
	
	
	
	
}

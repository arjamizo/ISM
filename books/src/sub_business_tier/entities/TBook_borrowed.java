package sub_business_tier.entities;

/**
 * This class represents only relation between client and borrowed book. 
 * @author artur
 *
 */
public class TBook_borrowed {

	private Client client;
	private TBook book;
	
	public TBook_borrowed(Client client, TBook book) {
		super();
		this.client = client;
		this.book = book;
	}
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public TBook getBook() {
		return book;
	}
	public void setBook(TBook book) {
		this.book = book;
	}
	
	
	
	
	
	
	
	
	
	
	
}

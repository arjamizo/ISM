package davart_lab01;

import java.util.Vector;

public class Client {

	
	private String login;
	private Vector<TBook_borrowed> borrowed;
	
	
	public Client(String login) {
		super();
		this.login = login;
	}
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Vector<TBook_borrowed> Books_Borrowed() {
		return borrowed;
	}
	public void add_Borrowed(TBook_borrowed borrowed) {
		this.borrowed.add(borrowed);
	}
	
	
	
	
	
	
}

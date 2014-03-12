package davart_lab01;

public class Client {

	
	private String login;
	private boolean borrowed;
	
	
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
	public boolean isBorrowed() {
		return borrowed;
	}
	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}
	
	
	
	
	
	
}

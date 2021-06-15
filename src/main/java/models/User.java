package models;

public class User {
	private String user_name;
	private String password;
	private Agenda my_agenda;
	
	public User(String name_, String password_) {
		this.user_name = name_;
		this.password = password_;
	}
	
	private void setUserName(String name_)
	{
		this.user_name = name_;
	}
	
	public String getUserName() {
		return this.user_name;
	}
	
	
	private void setPassword(String pass_) {
		this.password = pass_;
	}
	
	
	public Boolean authorizedConnect(String pass_){
		if(pass_.equals(this.password)) {
			return true;
		}
		return false;
	}
	
	public Agenda getAgenda() {
		return this.my_agenda;
	}
	
}

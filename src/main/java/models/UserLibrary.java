package models;

import java.util.ArrayList;

public class UserLibrary {
	ArrayList<User> user_library = new ArrayList<User>() ;
	String library_id;
	
	
	public UserLibrary() {
	
	}
	
	public Boolean addUser(String s_, String pw_) {
		if (!getUsers().isEmpty()){
			for(User user_ : getUsers()) {
				if ( user_.getUserName().equals(s_)) {
					return false;
				}
				if (!isLegitPassword(pw_)) {
					return false;
				}
			}
		}
		User new_user = new User(s_, pw_);
		user_library.add(new_user);
		return true;
	}
	
	private Boolean isLegitPassword(String pw_) {
		if(pw_.length()>9) {
			return true;
		}
		return false;
	}
	
	
	public Boolean isUser(String s_, String pw_) {//check if user exists AND information given are ok
		if (getUsers().isEmpty()){
			return false;
		}
		else {
			for(User user_ : getUsers()) {
				if ( user_.getUserName().equals(s_) && user_.authorizedConnect(pw_)) {
					return true;	
				}
			}
		}
		return false;
	}
	
	
	public ArrayList<User> getUsers() {
		return this.user_library;
	}
}

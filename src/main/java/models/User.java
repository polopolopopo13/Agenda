package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.mindrot.jbcrypt.BCrypt;

public class User implements Crud {
	private int id;
	private String name;
	private String password;
	
	public User(String name_, String pwd_) {
		this.name = name_;
		this.password = pwd_;
	}
	
	public User() {}
	
	
	public void setName(String n_) {
		this.name = n_;
	}
	
	private void setId(int id_) {
		this.id = id_;
	}
	
	public int getId() {
		return this.id;
	}
	
	private Boolean nameNotExisting(String s_) {
		String query = "SELECT name FROM users WHERE name = ? LIMIT 1;";

		try {
			PreparedStatement prep = DbConnect.getConnector().prepareStatement(query);
			prep.setString(1, s_);
			ResultSet result =  prep.executeQuery();
			while (result.next()) {
				System.out.println("already a name like this");
				return false;
			}
			System.out.println("name is not used already");
			return true;
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
		
	
	
	private Boolean containDigit(String s_) {
		for(char c : s_.toCharArray()){
	        if(Character.isDigit(c)){
	        	return true;
	        }
		}
		return false;
	}
	
	private Boolean passwordAvailable(String pw_) {

		if(pw_.length()>=2 && containDigit(pw_)) {
			return true;
		}
		return false;
	}
	
	
	public void addUser() {
		String n_ = getName();
		String pw_ = getPassword();
		String query = "INSERT INTO users (name, password) VALUES(?, ?); ";
		

		
		if(passwordAvailable(pw_) && nameNotExisting(n_)) {
			try {
			PreparedStatement prep = DbConnect.getConnector().prepareStatement(query);
			prep.setString(1, n_);
			prep.setString(2, pw_);
			prep.executeUpdate();
			System.out.println("user " +n_+ " added.");

			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("unavailable inputs");
		}
	}
	
	
	
	public Boolean isUser() {
		String query = "SELECT name, password, id FROM users WHERE name = ?";
		
		
		try {
			PreparedStatement prep = DbConnect.getConnector().prepareStatement(query);
			prep.setString(1, getName());
			ResultSet result =  prep.executeQuery();
			
			if(result.next()) {
				int id_ = result.getInt("id");
				String pwd_ = result.getString("password");
				if(BCrypt.checkpw(getPassword(), pwd_)) {
					setPassword(pwd_);
					setId(id_);
					DbConnect.getConnector().close();
					return true;
				}
				else {
					System.out.println("wrong connection");
					return false;
				}
			}
			else {
				System.out.println("wrong connection");
				return false;
			}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	public String getName() {
		return this.name;
	}
	
	private String getPassword() {
		return this.password;
	}
	
	
	public void setPassword(String pass_) {
		this.password = pass_;
	}
	
	
	public Boolean authorizedConnect(String pass_){
		if(pass_.equals(this.password)) {
			return true;
		}
		return false;
	}
	
	
	public ArrayList<Event> getAgenda() {
		String query = "SELECT id, description, datum FROM events WHERE user_id = ? ORDER BY datum;";
		ArrayList<Event> datas = new ArrayList<Event>();
		
		try {
		PreparedStatement prep = DbConnect.getConnector().prepareStatement(query);
		int u_id = getId();
		prep.setInt( 1, u_id );
		ResultSet result = prep.executeQuery();
		while(result.next()) {//true or false if nothing left
			String descript_ = result.getString("description");
			Date datum = result.getDate("datum");
			int e_id = result.getInt("id");
			Event e_ = new Event(descript_, datum, u_id, e_id);
			datas.add(e_);
		}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return datas;	
	}
	

	@Override
	public void insert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> selectAll() {
		
		ArrayList<String> datas = new ArrayList<String>();		
		try {
		String query = "SELECT name FROM users;";
		PreparedStatement prep = DbConnect.getConnector().prepareStatement(query);
		ResultSet result = prep.executeQuery();
		while(result.next()) {//true or false if nothing left
			String username = result.getString("name");
			datas.add(username);
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return datas;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	
}

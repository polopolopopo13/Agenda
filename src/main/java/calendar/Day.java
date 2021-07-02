package calendar;

import models.DbConnect;
import models.Event;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Day {
	private boolean hasevent;
	private java.sql.Date date;
	private int user_id;
	private String name;
	private int day;
	private int month;
	private int year;
	private ArrayList<Event> events = new ArrayList<Event>();
	
	public Day(java.sql.Date date, int user_id, int year, int month, int day, int i) {
		super();
		this.user_id = user_id;
		this.date = date;
		this.day = day;
		this.month = month;
		this.year = year;
		setName(i);
		setEvents();
		setHasEvent();

	}
	
	private static java.sql.Date getSqlDate(java.util.Date date_) {
        java.sql.Date sqlDate = new java.sql.Date(date_.getTime());
        return sqlDate;
    }
	
	public boolean getHasevent() {
		return hasevent;
	}

	public void setHasevent(boolean hasevent) {
		this.hasevent = hasevent;
	}

	public void setHasEvent() {
		if (getEvents().isEmpty()){
			this.hasevent = false;
		}
		else{this.hasevent = true;}
	}
	
	public java.sql.Date getDate() {
		return date;
	}
	public void setDate(java.sql.Date date) {
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(int i) {
		this.name = Week.DAYS[i];
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public ArrayList<Event> getEvents() {
		return events;
	}
	
	public void setEvents() {
		
		String query = "SELECT id, description FROM events WHERE user_id = ? AND datum = ? ORDER BY datum;";
		
		try {
		PreparedStatement prep = DbConnect.getConnector().prepareStatement(query);
		prep.setInt( 1,  this.user_id );
		prep.setDate( 2, this.date );
		ResultSet result = prep.executeQuery();
		while(result.next()) {//true or false if nothing left
			String descript_ = result.getString("description");
			int e_id = result.getInt("id");
			Event e_ = new Event(descript_, getUtilDate(this.date), this.user_id, e_id);
			this.events.add(e_);
		}
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
	}
	
	private static java.util.Date getUtilDate(java.sql.Date sqlDate){
		java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
		return utilDate;
	}
	
	
	@Override
	public String toString() {
		return "Day [name=" + name + ", day=" + day + ", month=" + month + ", year=" + year + "]";
	}

}

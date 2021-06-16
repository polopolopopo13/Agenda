package models;
import java.util.Date;
import java.sql.Timestamp;

public class Event {
	
	private String date;
    private String title;
    private String reference;
     
    public Event(String title, String date, String username) {
    this.title = title;
    this.date = date;
    setReference(username);
    }
    
    private void setReference(String username) {
    	Date date = new Date();
    	long ts = date.getTime();
    	System.out.println(ts);
		this.reference = username+ts;
		
	}
    
    public String getReference() {
    	return this.reference;
    }
    
	public String getTitle() {
    	return this.title;
    }
    
    public String getDate() {
    	return this.date;
    }
    
    
}

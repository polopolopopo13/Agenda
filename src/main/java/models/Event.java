package models;
import java.util.Date;

public class Event {
	
	private String date;
    private String title;
     
    public Event(String title, String date) {
    this.title = title;
    this.date = date;
    }
    
    public String getTitle() {
    	return this.title;
    }
    
    public String getDate() {
    	return this.date;
    }
}

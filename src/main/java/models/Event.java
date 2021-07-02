package models;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Event implements Crud{
	private int id;
	private Date date;
    private String description;
    private int user_id;
     
    public Event(String description, Date date, int user_id) {
    this.description = description;
    this.date = date;
    this.user_id = user_id;
    }
    public Event(String description, Date date, int user_id, int id_) {
    	//constructor when called from data base: to extract id!
        this.description = description;
        this.date = date;
        this.user_id = user_id;
        this.id = id_;
        }
    
    
    
    public Boolean isIncoming() {
    	Calendar cal = Calendar.getInstance();
    	if(getDate().after(cal.getTime())) {
    		return true;
    	}
    	return false;
    }
    
    public int getUserId() {
    	return this.user_id;
    }
    
    public void setUserId(int user_id) {
    	this.user_id = user_id;
    }
    
    public int getId() {
    	return this.id;
    }
    
	public String getDescription() {
    	return this.description;
    }
    
    public java.util.Date getDate() {
    	return this.date;
    }

	@Override
	public void insert() {

    	String query = "INSERT INTO events (user_id, description, datum) VALUES (?, ?, ?);";
    	
		try {
			PreparedStatement prep = DbConnect.getConnector().prepareStatement(query);
			prep.setInt(1, getUserId());
			prep.setString(2, getDescription());
			prep.setDate(3, getSqlDate(getDate()));

			prep.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	
	private static java.util.Date getUtilDate(java.sql.Date sqlDate){
		java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
		return utilDate;
	}
	
	
	private static java.sql.Date getSqlDate(java.util.Date uDate) {
        java.sql.Date sqlDate = new java.sql.Date(uDate.getTime());
        return sqlDate;
    }
	
	
	@Override
	public ResultSet select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<?> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update() {
		
	}
	
	public void modify(String description, Date datum, int user_id) {
		String query = "UPDATE events SET user_id=?, description=? , datum=? WHERE id=?;";
		
		try {
			PreparedStatement prep = DbConnect.getConnector().prepareStatement(query);
			prep.setInt(1, user_id);
			prep.setString(2, description);
			prep.setDate(3, getSqlDate(datum));
			prep.setInt(4, getId());
			prep.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
    
	
    
}

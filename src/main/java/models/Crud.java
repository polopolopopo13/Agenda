package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Crud {
	public void insert();
	public ResultSet select();
	public ArrayList<?> selectAll();
	public void update();
	public void delete();
	

}

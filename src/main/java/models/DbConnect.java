package models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnect {
	private static DbConnect connect;
	private Connection connection;
	
	private DbConnect() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp_agenda?characterEncoding=utf8", "admin", "admin");
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnector() throws SQLException {
		if(connect == null) {
			connect = new DbConnect();
		}
		else if(connect.getConnection().isClosed()) {
			connect = new DbConnect();
		}
		
		return connect.getConnection();
	}

	public static void setConnect(DbConnect connect) {
		DbConnect.connect = connect;
	}

	private Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
	

}

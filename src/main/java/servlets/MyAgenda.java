package servlets;
import calendar.Month;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Agenda;
import models.DbConnect;
import models.Event;
import models.User;

/**
 * Servlet implementation class MyAgenda
 */
@WebServlet(name = "myAgenda",
urlPatterns = {"/myAgenda"})
public class MyAgenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private User user;
	private ArrayList<Event> userAgenda = new ArrayList<Event>();
	private HttpSession session;

       
    /**
     * @throws IOException 
     * @throws ServletException 
     * @see HttpServlet#HttpServlet()
     */
    public MyAgenda() throws ServletException, IOException {
    	super();        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		this.session = request.getSession();
		this.user = (User) request.getSession().getAttribute("user_");
		
		Calendar c = Calendar.getInstance();
		
		
		if(request.getParameter("date") != null) {
			try {
				java.sql.Date d = java.sql.Date.valueOf(request.getParameter("date")+"-01");
				c.setTime(d);
				
			}catch (IllegalArgumentException e){
				//e.printStackTrace();
				java.sql.Date d = java.sql.Date.valueOf(LocalDate.now());
				c.setTime(d);
			}
			
		}
		else {
			java.sql.Date d = java.sql.Date.valueOf(LocalDate.now());
			c.setTime(d);
		}	
		
//		int maxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
//		int minDay = c.getActualMinimum(Calendar.DAY_OF_MONTH);
		Month month = new Month(c.get(Calendar.MONTH), c.get(Calendar.YEAR), this.user.getId());
		
		
		
		if(request.getParameter("action") != null && request.getParameter("reference") != null) {
					
			if(request.getParameter("action").equals("delete")) {	
				delEvent((request.getParameter("reference")));
			}
			else if(request.getParameter("action").equals("modify")) {
				Event selectedEvent = (Event) find(request.getParameter("reference"));
				request.setAttribute("selectedEvent", selectedEvent);
				
				request.getSession().setAttribute("selectedEventId", selectedEvent.getId());
			}
		}
		
		request.setAttribute("userName", user.getName());
		request.setAttribute("userEvents", user.getAgenda());
		request.setAttribute("month", month);
		this.getServletContext().getRequestDispatcher("/jsp/myAgenda.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// add event		
		if(request.getParameter("addORmod") != null) {
			try {
				String event_describ = request.getParameter("eventTitle");
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date event_date = df.parse(request.getParameter("eventDate"));
				
				//CREATE and ADD event
				if(request.getParameter("addORmod").equals("addEvent")) {
					Event e_ = new Event( event_describ, event_date, getUser(request).getId() );
					e_.insert();
				}
				//CREATE and MODIFY event
				else if(request.getParameter("addORmod").equals("modEvent")) {
					Event selectedEvent = (Event) find( (String.valueOf(request.getSession().getAttribute("selectedEventId"))) );
					selectedEvent.modify( event_describ, event_date, getUser(request).getId() );
				}
			}catch(ParseException e) {
				return;
			}
		}
		
		doGet(request, response);
	}
	
	public void delEvent(String id_s) {
		int id_ = Integer.valueOf(id_s);
		String query = "DELETE FROM events WHERE id=?";
		
		try {
			PreparedStatement prep = DbConnect.getConnector().prepareStatement(query);
			prep.setInt( 1, id_ );
			prep.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Event find(String id_s) {
		int id_ = Integer.valueOf(id_s);
		String query = "SELECT user_id, id, description, datum FROM events WHERE id = ? ;";
		
		try {
			PreparedStatement prep = DbConnect.getConnector().prepareStatement(query);
			prep.setInt( 1, id_ );
			ResultSet result =  prep.executeQuery();
			Event event_ = null;
				while(result.next()) {
					String descript_ = result.getString("description");
					Date datum = result.getDate("datum");
					int u_id = result.getInt("user_id");
					event_ = new Event(descript_, datum, u_id, id_);
				}
				return event_;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public User getUser(HttpServletRequest request) {
		User u_ = (User) request.getSession().getAttribute("user_");
		return u_;
	}
	
}

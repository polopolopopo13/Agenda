package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Agenda;
import models.Event;
import models.User;

/**
 * Servlet implementation class MyAgenda
 */
@WebServlet(name = "myAgenda",
urlPatterns = {"/myAgenda"})
public class MyAgenda extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private HttpSession mySession;
	private HttpServletRequest request;
	private User user;
	private Agenda userAgenda;

       
    /**
     * @throws IOException 
     * @throws ServletException 
     * @see HttpServlet#HttpServlet()
     */
    public MyAgenda() throws ServletException, IOException {
    	super();        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stu
		HttpSession session = request.getSession();
		this.user = (User) session.getAttribute("user_");
		this.userAgenda = user.getAgenda();
		
		request.setAttribute("userName", user.getUserName());
		request.setAttribute("userEvents", userAgenda.getEvents());
		//System.out.println(mySession.getAttribute("session"));
		this.getServletContext().getRequestDispatcher("/jsp/agenda.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String event_title = request.getParameter("eventTitle");
		String event_date = request.getParameter("eventDate");
		if (request.getParameter("addEvent") != null) {
			Event e_ = new Event(event_title, event_date, getUser().getUserName());
			userAgenda.addEvent(e_);	
		}
		
		
		doGet(request, response);
	}

	
	public User getUser() {
		return this.user;
	}
	
}

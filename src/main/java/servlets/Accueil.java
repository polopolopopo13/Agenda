package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.User;
import models.UserLibrary;

/**
 * Servlet implementation class Accueil
 */
@WebServlet(name = "Accueil",
urlPatterns = {"/accueil", ""})
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserLibrary library_user = new UserLibrary();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
        // TODO Auto-generated constructor stub
        library_user.addUser("Admin", "monadminfrance");
        System.out.println("hello");
        System.out.println(getLibraryUser().getUsers());
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getAttribute("user")!=null) {
			response.sendRedirect("myAgenda");
		}
		else{
			if(!getLibraryUser().getUsers().isEmpty()) {
				request.setAttribute("allUsers", getLibraryUser().getUsers());
			}
			
			this.getServletContext().getRequestDispatcher("/jsp/accueil.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username_ = request.getParameter("user");
		String password_ = request.getParameter("password");
		//depending if sign in or sign up
		if (request.getParameter("signIn") != null) {
			System.out.println("signin");
			Boolean isValid = checkAndConnect(username_, password_);
			if(isValid) {
				//request.getRequestDispatcher("/jsp/agenda.jsp");
				request.setAttribute("user", username_);
			}
			
		}
		else if(request.getParameter("signUp") != null) {
			Boolean isValid = registerNewUser(username_, password_);
			if(isValid) {
				//request.getRequestDispatcher("/jsp/agenda.jsp");
				request.setAttribute("user", username_);
			}
		}		
		doGet(request, response);
	}
	
	
	
	
	private UserLibrary getLibraryUser(){
		return this.library_user;
	}
	
	private Boolean checkAndConnect(String s_, String pw_) {//works
		if (getLibraryUser().isUser(s_, pw_)) {
			return true;
		}
		else {//works
			return false;
		}
	}
	
	private Boolean registerNewUser(String s_, String pw_) {//works
		if (getLibraryUser().addUser(s_, pw_)) {
			System.out.println("Welcome " +s_);
			return true;
		}
		else {
			System.out.println("Information unavailable");//works
			return false;
		}
	}
	
}

package servlets;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

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
        System.out.println("hello");
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			this.getServletContext().getRequestDispatcher("/jsp/accueil.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username_ = request.getParameter("user");
		String password_ = request.getParameter("password");
		//depending if sign in or sign up
		User u_ = new User();
		u_.setName(username_);

		if (request.getParameter("signIn") != null) {
			u_.setPassword(password_);
			if(checkAndConnect(u_)) {
				goToAgenda(request, response, u_);
				return;//OBLIGATOIRE SI ON VEUT PAS APPELER LE "doGet()" à suivre
			}	
		}
		else if(request.getParameter("signUp") != null) {
			u_.setPassword(BCrypt.hashpw(password_, BCrypt.gensalt()));
			u_.addUser();
		}
		
		doGet(request, response);
	}
	
	
	
	
	private UserLibrary getLibraryUser(){
		return this.library_user;
	}
	
	private Boolean checkAndConnect(User u_) {//works
		System.out.println("connect");
		if (u_.isUser()){
			return true;//a User object
		}
		else {//works
			return false;
		}
	}
	
	
	public void goToAgenda(HttpServletRequest request, HttpServletResponse response, User elem) throws IOException {
		HttpSession mySession = request.getSession(true);
		mySession.setAttribute("user_", elem);
		request.setAttribute("session", mySession);
		response.sendRedirect("myAgenda");
	}
}

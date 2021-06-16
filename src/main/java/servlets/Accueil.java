package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
//        library_user.addUser("Admin", "monadminfrance");
        System.out.println("hello");
//        System.out.println(getLibraryUser().getUsers());
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			request.setAttribute("allUsers", getLibraryUser().getUsers());
			
			this.getServletContext().getRequestDispatcher("/jsp/accueil.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username_ = request.getParameter("user");
		String password_ = request.getParameter("password");
		//depending if sign in or sign up
		if (request.getParameter("signIn") != null) {
			Object elem = checkAndConnect(username_, password_);
			if(elem instanceof User) {
				goToAgenda(request, response, (User) elem);
				return;
			}	
		}
		else if(request.getParameter("signUp") != null) {
			Object elem = registerNewUser(username_, password_);
			if(elem instanceof User) {
				goToAgenda(request, response, (User) elem);
				return;
			}
		}		
		doGet(request, response);
	}
	
	
	
	
	private UserLibrary getLibraryUser(){
		return this.library_user;
	}
	
	private Object checkAndConnect(String s_, String pw_) {//works
		System.out.println("connect");
		Object elem = getLibraryUser().isUser(s_, pw_);
		System.out.println(elem);
		if (elem instanceof User) {
			return elem;//a User object
		}
		else {//works
			return false;
		}
	}
	
	private Object registerNewUser(String s_, String pw_) {//works
		Object elem = getLibraryUser().addUser(s_, pw_);
		if (elem instanceof User) {
			System.out.println("Welcome " +s_);
			return elem; //return User
		}
		else {
			System.out.println("Information unavailable");//works
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

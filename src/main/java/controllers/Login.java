package controllers;

import java.io.IOException;

import java.io.PrintWriter;

import bl.Auth;
import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("user") != null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/dashboard.jsp").forward(request, response);
		} else {
			
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
        User u = Auth.login(email, password);

		if(u != null ) {
			HttpSession session = request.getSession();
            session.setAttribute("user", u);
            response.setContentType("text/html");
	        response.setCharacterEncoding("UTF-8");
	        
	        PrintWriter out = response.getWriter();
	        out.println(" Welcome ");
		} else {
			response.setContentType("text/html");
	        response.setCharacterEncoding("UTF-8");
	        
	        PrintWriter out = response.getWriter();
		}
		System.out.println("========================================imhere");
		response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
	}

}

package controllers;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import bl.DataSender;
import bl.TicketManager;
import dal.TicketDal;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.TicketModel;

/**
 * Servlet implementation class StatisticsController
 */
@WebServlet("/StatisticsController")
public class StatisticsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public StatisticsController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
		} else {
			request.setAttribute("data", DataSender.getSections());
			this.getServletContext().getRequestDispatcher("/WEB-INF/statistics.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Static Op = " + request.getParameter("op"));
		if (request.getParameter("op") != null) {
			if (request.getParameter("op").equals("load")) {

				response.setContentType("application/json");
				Gson json = new Gson();
				models.Statistics Statistics = bl.Statistics.getStatistics();
				response.getWriter().write(json.toJson(Statistics));

			}
		}
	}

}

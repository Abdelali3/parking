package controllers;

import java.io.IOException;

import java.util.List;

import com.google.gson.Gson;

import bl.DataSaver;
import bl.DataSender;
import bl.TicketManager;
import dal.TicketDal;
import entities.Section;
import entities.Vehicle;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.TicketModel;

/**
 * Servlet implementation class TicketsController
 */
@WebServlet("/TicketsController")
public class TicketsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public TicketsController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("user") == null) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
		} else {
			request.setAttribute("data", DataSender.getSections());
			this.getServletContext().getRequestDispatcher("/WEB-INF/tickets.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Ticket Op = " + request.getParameter("op"));
		if (request.getParameter("op") != null) {
			if (request.getParameter("op").equals("load")) {

				response.setContentType("application/json");
				Gson json = new Gson();
				List<TicketModel> tickets = DataSender.getTickets();
				response.getWriter().write(json.toJson(tickets));

			} else {
				int ticketId = Integer.parseInt(request.getParameter("id"));
				TicketDal td = TicketDal.getInstance();
				if(td.findById(ticketId) != null) {
					TicketManager.pay(td.findById(ticketId));
				}
				response.setContentType("application/json");
				Gson json = new Gson();
				List<TicketModel> tickets = DataSender.getTickets();
				response.getWriter().write(json.toJson(tickets));
			}
		}
	}

}

package controllers;

import java.io.IOException;

import java.util.List;

import com.google.gson.Gson;

import bl.DataSaver;
import bl.DataSender;
import entities.Section;
import entities.Vehicle;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class VehicleController
 */
@WebServlet("/VehicleController")
public class VehicleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public VehicleController() {
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
			this.getServletContext().getRequestDispatcher("/WEB-INF/vehicleForm.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("V Op = " + request.getParameter("op"));
		if (request.getParameter("op") != null) {
			if (request.getParameter("op").equals("load")) {

				response.setContentType("application/json");
				Gson json = new Gson();
				List<Vehicle> vehicles = DataSender.getVehicles();
				response.getWriter().write(json.toJson(vehicles));

			} else {
				try {
					String brand = request.getParameter("brand");
					String matriculation = request.getParameter("matriculation");
					String type = request.getParameter("type");

					if (DataSaver.addVehicle(type, matriculation, brand)) {
						response.setContentType("application/json");
						Gson json = new Gson();
						List<Vehicle> vehicles = DataSender.getVehicles();
						response.getWriter().write(json.toJson(vehicles));
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

}

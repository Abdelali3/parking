package controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import bl.DataSaver;
import bl.DataSender;
import bl.OccupationManager;
import bl.PlacesManager;
import dal.PlaceDal;
import dal.VehicleDal;
import entities.Occupation;
import entities.Place;
import entities.Section;
import entities.Vehicle;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.OccupationModel;
import models.PlaceModel;

/**
 * Servlet implementation class Parking
 */
@WebServlet("/Parking")
public class Parking extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Parking() {
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
			this.getServletContext().getRequestDispatcher("/WEB-INF/occupation.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Parking Op = " + request.getParameter("op"));
		if (request.getParameter("op") != null) {
			if (request.getParameter("op").equals("loadVehicles")) {

				response.setContentType("application/json");
				Gson json = new Gson();
				List<Vehicle> vehicles = DataSender.getUnparkedVehicles();
				response.getWriter().write(json.toJson(vehicles));

			} else if (request.getParameter("op").equals("loadPlaces")) {

				response.setContentType("application/json");
				Gson json = new Gson();
				List<PlaceModel> places = PlacesManager.getFreePlaces();
				response.getWriter().write(json.toJson(places));

			} else {
				VehicleDal vd = VehicleDal.getInstance();
				PlaceDal pd = PlaceDal.getInstance(null);
				
				int placeId = Integer.parseInt(request.getParameter("placeId"));
				int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
				String[] services = request.getParameterValues("services");
				

				
				Vehicle v = vd.findById(vehicleId);
				Place p = pd.findById(placeId);
				OccupationManager.startOccupation(p, v, services);

				response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/"));
			}
		}
	}

}

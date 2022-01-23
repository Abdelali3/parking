package controllers;

import java.io.IOException;

import java.util.List;

import com.google.gson.Gson;

import bl.DataSaver;
import bl.DataSender;
import bl.OccupationManager;
import entities.Occupation;
import entities.Section;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.OccupationModel;

/**
 * Servlet implementation class OccupationController
 */
@WebServlet("/OccupationController")
public class OccupationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public OccupationController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Occupation Op = " + request.getParameter("op"));
		if (request.getParameter("op") != null) {
			if (request.getParameter("op").equals("load")) {

				response.setContentType("application/json");
				Gson json = new Gson();
				List<OccupationModel> occupations = DataSender.getOccupations();
				
				response.getWriter().write(json.toJson(occupations));

			} else if(request.getParameter("op").equals("free")){
				try {

					OccupationManager.endOccupation(Integer.parseInt(request.getParameter("occupationid")));
						response.setContentType("application/json");
						Gson json = new Gson();
						System.out.println("after Change 0");
						List<OccupationModel> occupations = DataSender.getOccupations();
						System.out.println("after Change 1");
						System.out.println(occupations);
						System.out.println("after Change 2");
						response.getWriter().write(json.toJson(occupations));
						System.out.println("after Change 3");
					
					
					

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
	}

}

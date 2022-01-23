package controllers;

import java.io.IOException;

import java.util.List;

import com.google.gson.Gson;

import bl.DataSaver;
import bl.DataSender;
import entities.Section;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class PlaceController
 */
@WebServlet("/PlaceController")
public class PlaceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public PlaceController() {
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
			this.getServletContext().getRequestDispatcher("/WEB-INF/placeForm.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Op = " + request.getParameter("op"));
		if (request.getParameter("op") != null) {
			if (request.getParameter("op").equals("load")) {

				response.setContentType("application/json");
				Gson json = new Gson();
				List<Section> sections = DataSender.getSections();
				response.getWriter().write(json.toJson(sections));

			} else {
				try {
					
					int sectionId = -1;
					try {
						sectionId = Integer.parseInt(request.getParameter("sectionId"));
					} catch (Exception e) {
						sectionId = -1;
					}
					
					String sectionName = request.getParameter("sectionName");
					String type = request.getParameter("type");
					
					float ph1 = Float.parseFloat(request.getParameter("pH1"));
					float ph2 = Float.parseFloat(request.getParameter("pH2"));
					float phn = Float.parseFloat(request.getParameter("pHn"));
					boolean added = false;
					System.out.println(sectionName);
					if (sectionName != null) {
						added = DataSaver.addPlace(sectionName, type, ph1, ph2, phn);
						
					} else {
						added = DataSaver.addPlace(sectionId, type, ph1, ph2, phn);
						
					}
					if(added) {
						response.setContentType("application/json");
						Gson json = new Gson();
						List<Section> sections = DataSender.getSections();
						response.getWriter().write(json.toJson(sections));
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
	}
}

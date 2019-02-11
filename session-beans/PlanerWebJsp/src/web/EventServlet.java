package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.EventType;
import model.User;
import rzk.EventTypeBeanRemote;
import rzk.PlanerBeanRemote;

/**
 * Servlet implementation class PlanerServlet
 */
@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EventServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EventTypeBeanRemote bean = (EventTypeBeanRemote) request.getAttribute("bean");
		if (bean == null) {
			try {
				InitialContext ic = new InitialContext();
				bean = (EventTypeBeanRemote) ic.lookup("ejb:PlanerEAR/PlanerEJB/EventTypeBean!rzk.EventTypeBeanRemote");

				request.setAttribute("bean", bean);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		List<EventType> types = bean.getTypes();
		request.getSession().setAttribute("types", types);

		request.getRequestDispatcher("addEvent.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PlanerBeanRemote bean = (PlanerBeanRemote) request.getSession().getAttribute("bean");
		
		String description = request.getParameter("description");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");
		Integer eventTypeId = Integer.parseInt(request.getParameter("types"));
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			boolean created = bean.createAnEvent(description, sdf.parse(fromDate), sdf.parse(toDate), eventTypeId);
			if (!created) {
				request.getRequestDispatcher("error.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("success.jsp").forward(request, response);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
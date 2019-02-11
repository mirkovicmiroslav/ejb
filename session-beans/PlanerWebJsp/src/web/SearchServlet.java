package web;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BadRequestException;

import model.Event;
import rzk.PlanerBeanRemote;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
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
		PlanerBeanRemote bean = (PlanerBeanRemote) request.getSession().getAttribute("bean");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = request.getParameter("fromDate");
		Date dateFrom;
		try {
			dateFrom = sdf.parse(date);
			List<Event> events = bean.searchEvents(dateFrom);
			request.setAttribute("events", events);
			if (events != null) {
				request.getRequestDispatcher("searchEvent.jsp").forward(request, response);
			}
			else {
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
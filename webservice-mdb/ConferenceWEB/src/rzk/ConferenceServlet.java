package rzk;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConferenceServlet
 */
@WebServlet("/ConferenceServlet")
public class ConferenceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	ConferenceBeanRemote cbr;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConferenceServlet() {
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
		String title = request.getParameter("title");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String field = request.getParameter("field");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date dateFrom = sdf.parse(request.getParameter("dateFrom"));
			Date dateTo = sdf.parse(request.getParameter("dateTo"));

			cbr.sendConference(title, country, city, dateFrom, dateTo, field);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Ogla;
import rzk.OglasiBeanRemote;

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
		OglasiBeanRemote bean = (OglasiBeanRemote) request.getSession().getAttribute("bean");

		Integer id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("id", id);
		
		request.getRequestDispatcher("javljanjeOglas.jsp").forward(request, response);;

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OglasiBeanRemote bean = (OglasiBeanRemote) request.getSession().getAttribute("bean");

		String text = request.getParameter("text");
		List<Ogla> oglasi = bean.search(text);
		
		if (oglasi != null) {
			request.setAttribute("oglasi", oglasi);
			request.getRequestDispatcher("searchOglas.jsp").forward(request, response);
		}
		else
			request.getRequestDispatcher("error.jsp").forward(request, response);
	}

}

package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rzk.OglasiBeanRemote;

/**
 * Servlet implementation class OglasiServlet
 */
@WebServlet("/OglasiServlet")
public class OglasiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OglasiServlet() {
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
		OglasiBeanRemote bean = (OglasiBeanRemote) request.getSession().getAttribute("bean");
		
		String text = request.getParameter("text");
		boolean succ = bean.addOglas(text);
		if (succ) 
			request.getRequestDispatcher("success.jsp").forward(request, response);
		else
			request.getRequestDispatcher("error.jsp").forward(request, response);
	}

}

package web;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rzk.OglasiBeanRemote;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		OglasiBeanRemote bean = (OglasiBeanRemote) request.getSession().getAttribute("bean");
		if (bean == null) {
			try {
				InitialContext ic = new InitialContext();
				bean = (OglasiBeanRemote) ic
						.lookup("ejb:OglasiEAR/OglasiEJB/OglasiBean!rzk.OglasiBeanRemote?stateful");

				request.getSession().setAttribute("bean", bean);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		String email = bean.login(username, pass);
		if (email.equals("")) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.setAttribute("bean", bean);
			request.setAttribute("username", username);
			
			request.getRequestDispatcher("oglasi.jsp").forward(request, response);
		}

	}
}
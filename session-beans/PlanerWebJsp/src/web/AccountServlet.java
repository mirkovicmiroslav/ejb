package web;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rzk.AccountBeanRemote;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountServlet() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AccountBeanRemote bean = (AccountBeanRemote) request.getAttribute("bean");
		if (bean == null) {
			try {
				InitialContext ic = new InitialContext();
				bean = (AccountBeanRemote) ic.lookup("ejb:PlanerEAR/PlanerEJB/AccountBean!rzk.AccountBeanRemote");

				request.setAttribute("bean", bean);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}

		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		boolean reg = bean.createAccount(username, pass, firstName, lastName);

		if (!reg) {
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("success.jsp").forward(request, response);
		}

	}

}

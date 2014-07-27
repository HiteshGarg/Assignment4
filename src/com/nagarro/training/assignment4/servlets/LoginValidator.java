package com.nagarro.training.assignment4.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.DAO.UserDAO;
import com.nagarro.training.assignment4.customException.NewCustomException;

/**
 * Servlet implementation class LoginValidator
 */
@WebServlet(description = "Validates the login credentials entered by the user by validating them against already stored values in database", urlPatterns = { "/LoginValidator" })
public class LoginValidator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginValidator() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		RequestDispatcher dispatcher;
		//
		// if (null == session.getAttribute("username")) {
		// request.setAttribute("invalidLogin",
		// "Please Login To access other parts...");
		// dispatcher = request
		// .getRequestDispatcher("login.jsp");
		// dispatcher.forward(request, response);
		// }

		String uname = request.getParameter("username");
		String pwd = request.getParameter("password");

		try {
			Integer userId = UserDAO.validateUser(uname, pwd);
			if (null != userId) {
				out.println("Welcome Dude");
				session.setAttribute(Constants.SESSION_USER_ID, userId); // Setting userId as a
														// session variable on
														// Successful Login
				out.println(session.getAttribute(Constants.SESSION_USER_ID));

				response.sendRedirect("imageRepository.jsp");
			} else {
				request.setAttribute("invalidLogin",
						"Login Credentials are wrong ... Please try again..");
				dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		} catch (NewCustomException exception) {
			exception.printMessage();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

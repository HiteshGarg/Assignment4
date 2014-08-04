package com.nagarro.training.assignment4.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.customException.NewCustomException;
import com.nagarro.training.assignment4.services.LoginService;

/**
 * Servlet implementation class LoginValidator
 */
@WebServlet(description = "Validates the login credentials entered by the user by validating them against already stored values in database", urlPatterns = { "/LoginValidator" })
public class LoginValidator extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -69400862935284156L;

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

		RequestDispatcher dispatcher;
		
		try {
			if(new LoginService().validateLogin(request)){
				request.getRequestDispatcher(Constants.IMAGE_RETRIEVER).forward(request, response);
				return;
			} else {
				request.setAttribute("invalidLogin",
						"Login Credentials are wrong ... Please try again..");
			}
		} catch (NewCustomException exception) {
			request.setAttribute("invalidLogin",
					exception.getErrorMessage());
			
		}finally{
			dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
			
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

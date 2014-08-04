package com.nagarro.training.assignment4.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.customException.NewCustomException;
import com.nagarro.training.assignment4.services.ImageDeleteService;

/**
 * Servlet implementation class DeleteImage
 */
@WebServlet("/DeleteImage")
public class DeleteImage extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3261634470844386362L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		deleteImage(request, response);
//		response.sendRedirect("imageRepository.jsp");
		request.getRequestDispatcher("imageRepository.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		deleteImage(request, response);
	}

	protected void deleteImage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			Boolean deleted = new ImageDeleteService().imageDelete(request);
			if (deleted) {
				request.setAttribute(Constants.IMAGE_REPOSITORY_MESSAGES,
						Constants.IMAGE_DELETE_SUCCESS);
			} else {
				request.setAttribute(Constants.IMAGE_REPOSITORY_MESSAGES,
						Constants.IMAGE_DELETE_FAIL);
			}

		} catch (NewCustomException exception) {
			request.setAttribute(Constants.IMAGE_REPOSITORY_MESSAGES,
					exception.getErrorMessage());
		} finally {
			request.getRequestDispatcher(Constants.IMAGE_RETRIEVER).forward(request, response);
		}
	}

}

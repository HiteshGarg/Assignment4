package com.nagarro.training.assignment4.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.customException.NewCustomException;
import com.nagarro.training.assignment4.services.ImageUpdateService;

/**
 * Servlet implementation class UpdateImage
 */
@WebServlet("/UpdateImage")
@MultipartConfig
public class UpdateImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateImage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doUpdate(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doUpdate(request, response);
		return;
	}

	protected void doUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if(! ServletFileUpload.isMultipartContent(request)){
			request.setAttribute("uploadImage", "No file is Uploaded.. Please upload a file");
			request.getRequestDispatcher("imageRepository").forward(request, response);
		}
		try {
			
			Boolean updated = new ImageUpdateService().updateUserImage(request);
			if (updated) {
				request.setAttribute(Constants.IMAGE_REPOSITORY_MESSAGES,
						Constants.IMAGE_UPDATE_SUCCESS);
			} else {
				request.setAttribute(Constants.IMAGE_REPOSITORY_MESSAGES,
						Constants.IMAGE_UPDATE_FAIL);
			}

		} catch (NewCustomException exception) {
			request.setAttribute(Constants.IMAGE_REPOSITORY_MESSAGES,
					exception.getErrorMessage());
		}
		finally {
			request.getRequestDispatcher(Constants.IMAGE_RETRIEVER).forward(request, response);
			
		}
		return;
	}

}

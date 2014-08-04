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
import com.nagarro.training.assignment4.services.ImageUploadService;

/**
 * Servlet implementation class ImageUpload
 */
@WebServlet(description = "Uploads image provided by the user", urlPatterns = { "/ImageUpload" })
@MultipartConfig(maxFileSize = 1024 * 1024)
public class ImageUpload extends HttpServlet {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6226550430676716902L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("uploadImage",
					"No file is Uploaded.. Please upload a file");
			request.getRequestDispatcher("imageRepository").forward(request,
					response);
		}
		try {
	
			Boolean uploaded = new ImageUploadService().uploadUserImage(request);

			if (uploaded) {
				request.setAttribute(Constants.IMAGE_REPOSITORY_MESSAGES,
						Constants.IMAGE_UPLOAD_SUCCESS);
			} else {
				request.setAttribute(Constants.IMAGE_REPOSITORY_MESSAGES,
						Constants.IMAGE_UPLOAD_FAIL);
			}
		} catch (NewCustomException exception) {
			request.setAttribute(Constants.IMAGE_REPOSITORY_MESSAGES,
					exception.getErrorMessage());
		} finally {
			request.getRequestDispatcher(Constants.IMAGE_RETRIEVER).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

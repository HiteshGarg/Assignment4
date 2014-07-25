package com.nagarro.training.assignment4.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.DAO.ImageHandler;
import com.nagarro.training.assignment4.services.FormDataHandler;
import com.nagarro.training.assignment4.services.ImageServices;

/**
 * Servlet implementation class UpdateImage
 */
@WebServlet("/UpdateImage")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doEdit(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doEdit(request, response);
	}
	protected void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			new ImageServices().updateUserImage(request);
//			Integer imageId = Integer.parseInt(request.getParameter("id"));
//			String name = request.getParameter("name");
//			
//			List<FileItem> filesList = new FormDataHandler().getFilesList(request);
//			
//			if((filesList.size() ==0) && (name!="")){
//			
//				String hql = "Update UserImage set imageName = ? where id = ?";
//		
//			}else if(filesList.size()==1){
//				InputStream inputStream = null;
//				FileItem fileItem = filesList.get(0);
//				inputStream = fileItem.getInputStream();
//				
//				byte[] imageDatainBytes = new byte[(int)fileItem.getSize()];
//				inputStream.read(imageDatainBytes);
//				inputStream.close();
//				if(name == ""){
//					
//				}
//			}
//			
			
			
			response.getWriter().println(request.getParameter("id"));
			response.getWriter().println(request.getParameter("name"));
			response.getWriter().println(request.getParameter("image"));
			
//			Integer updated = new ImageHandler().updateImageInDB(imageId, request.getParameter("name"));
			
			
//			if (deleted == 1) {
//				request.setAttribute(Constants.IMAGE_REPOSITORY_MESSAGES,
//						Constants.IMAGE_DELETE_SUCCESS);
//			} else {
//				request.setAttribute(Constants.IMAGE_REPOSITORY_MESSAGES,
//						Constants.IMAGE_DELETE_FAIL);
//			}

		} catch (NumberFormatException exception) {
			exception.printStackTrace();
			request.setAttribute(Constants.IMAGE_REPOSITORY_MESSAGES,
					Constants.UNEXPECTED_ERROR);
		} finally {
			//request.getRequestDispatcher("imageRepository.jsp").forward(request, response);
		}
	}

}

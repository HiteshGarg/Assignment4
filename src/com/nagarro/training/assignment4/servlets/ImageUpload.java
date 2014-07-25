package com.nagarro.training.assignment4.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.DAO.ImageHandler;
import com.nagarro.training.assignment4.POJO.UserDetails;
import com.nagarro.training.assignment4.POJO.UserImage;
import com.nagarro.training.assignment4.services.FormDataHandler;

/**
 * Servlet implementation class ImageUpload
 */
@WebServlet(description = "Uploads image provided by the user", urlPatterns = { "/ImageUpload" })
@MultipartConfig(maxFileSize = 1024*1024)
public class ImageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		if(! ServletFileUpload.isMultipartContent(request)){
//			request.setAttribute("uploadImage", "No file is Uploaded.. Please upload a file");
//			request.getRequestDispatcher("imageRepository").forward(request, response);
//		}
		
		List<FileItem> filesList = FormDataHandler.getFilesList(request);
		List<UserImage> imageList = new ArrayList<>();

		InputStream inputStream = null;
		FileItem fileItem = null;
		
		Iterator<FileItem> iterator = filesList.iterator();
		while(iterator.hasNext()){
			
			fileItem = iterator.next();
			inputStream = fileItem.getInputStream();
			
			byte[] imageDatainBytes = new byte[(int)fileItem.getSize()];
			inputStream.read(imageDatainBytes);
			inputStream.close();
			
			UserDetails user = new UserDetails();
			user.setId((Integer)request.getSession().getAttribute(Constants.SESSION_USER_ID));
			UserImage userImg = new UserImage(fileItem.getName(), imageDatainBytes, user);
			
			imageList.add(userImg);
		}
        
		Boolean uploaded = new ImageHandler().uploadImageList(imageList);
		if(uploaded){
			request.setAttribute(Constants.IMAGE_REPOSITORY_MESSAGES, Constants.IMAGE_UPLOAD_SUCCESS);
		}else{
			request.setAttribute(Constants.IMAGE_REPOSITORY_MESSAGES, Constants.IMAGE_UPLOAD_FAIL);
		}
		request.getRequestDispatcher("imageRepository.jsp").forward(request, response);
	}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}

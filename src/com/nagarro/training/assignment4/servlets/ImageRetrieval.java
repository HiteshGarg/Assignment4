package com.nagarro.training.assignment4.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.Base64;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.customException.NewCustomException;
import com.nagarro.training.assignment4.dao.ImageDao;
import com.nagarro.training.assignment4.pojo.UserImage;

/**
 * Servlet implementation class getUserImages
 */
@WebServlet("/ImageRetrieval")
public class ImageRetrieval extends HttpServlet {
       
    /**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2671699181733165086L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public ImageRetrieval() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<UserImage> imageList = ImageDao
					.listUserImages((Integer) request.getSession().getAttribute(Constants.SESSION_USER_ID));
			List<String> base64url = new ArrayList<>();
			List<Integer> size = new ArrayList<>();
			for(UserImage image: imageList){
				String url = "data:image/png;base64," + Base64.encode(image.getImage());
				base64url.add(url);
				size.add(image.getImage().length);
			}

			request.setAttribute("imageList", imageList);
			request.setAttribute("base64List", base64url);
			request.setAttribute("imageLength", size);
					
		} catch (NewCustomException exception) {
			request.setAttribute(Constants.IMAGE_REPOSITORY_MESSAGES,
					exception.getErrorMessage());
		}
		finally{
			request.getRequestDispatcher("imageRepository.jsp").forward(request, response);
			
		}
	}

}

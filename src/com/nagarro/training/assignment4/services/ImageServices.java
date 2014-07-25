package com.nagarro.training.assignment4.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItem;

import com.nagarro.training.assignment4.DAO.ImageHandler;
import com.nagarro.training.assignment4.POJO.UserImage;

public class ImageServices {

	public void updateUserImage(HttpServletRequest request) {
		System.out.println(request.getParameter("id"));
		System.out.println();
		System.out.println(request.getParameter("image"));
		
		Integer imageId = Integer.parseInt(request.getParameter("id"));
		UserImage image = ImageHandler.getImageById(imageId);
		
		if(request.getParameter("name")!=null){
			image.setImageName(request.getParameter("name"));
		}
		if(request.getParameter("image")!= null){
			List<FileItem> filesList = FormDataHandler.getFilesList(request);
			byte[] imageByteArray = ;
		}
	}

}

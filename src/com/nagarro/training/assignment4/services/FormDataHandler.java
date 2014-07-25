package com.nagarro.training.assignment4.services;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

public class FormDataHandler {

	public static List<FileItem> getFilesList(HttpServletRequest request) {
		FileItemFactory fileFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fileFactory);
		
		List<FileItem> files= null;
		
		try {
			files =  upload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Iterator<FileItem> iter = ((List<FileItem>) files).iterator();
        while (iter != null && iter.hasNext()) {
             FileItem fileItem1 = (FileItem) iter.next();
             
             if (fileItem1.isFormField()) {
            	 iter.remove();
             }
        }
		return files;
	}
}

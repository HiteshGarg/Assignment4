package com.nagarro.training.assignment4.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.customException.NewCustomException;

public class FormDataService {

	/**
	 * Parse the request object and returns a map of filename and FileItem of
	 * all the FileItems in the request object
	 * 
	 * @param request
	 * @return
	 * @throws NewCustomException
	 */
	public static Map<String, FileItem> getFilesList(HttpServletRequest request)
			throws NewCustomException {
		FileItemFactory fileFactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fileFactory);

		upload.setFileSizeMax(Constants.MAX_UPLOAD_FILE_SIZE);
		Map<String, FileItem> formData = new HashMap<>();

		List<FileItem> files = null;

		try {
			files = upload.parseRequest(request);
		} catch (FileSizeLimitExceededException exception) {
			throw new NewCustomException(Constants.FILE_SIZE_EXCEEDED);
		} catch (FileUploadException e) {
			throw new NewCustomException(Constants.FILE_UPLOAD_ERROR);
		}

		if (files != null) {
			Iterator<FileItem> iter = ((List<FileItem>) files).iterator();
			while (iter != null && iter.hasNext()) {
				FileItem fileItem = (FileItem) iter.next();
				formData.put(fileItem.getFieldName(), fileItem);
			}
		}

		return formData;
	}
}

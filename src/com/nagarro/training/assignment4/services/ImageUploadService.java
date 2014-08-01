/**
 * 
 */
package com.nagarro.training.assignment4.services;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItem;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.customException.NewCustomException;
import com.nagarro.training.assignment4.dao.ImageDao;
import com.nagarro.training.assignment4.pojo.UserDetails;
import com.nagarro.training.assignment4.pojo.UserImage;

/**
 * @author hiteshgarg
 * 
 */
public class ImageUploadService {
	/**
	 * Uploads user image and set it in the database updating the total size of
	 * user images
	 * 
	 * @param request
	 * @return
	 * @throws NewCustomException
	 */
	public Boolean uploadUserImage(HttpServletRequest request)
			throws NewCustomException {

		Boolean uploaded = false;
		try {
			Integer userid = (Integer) request.getSession().getAttribute(
					Constants.SESSION_USER_ID);
			Map<String, FileItem> formData = FormDataService
					.getFilesList(request);

			new ImageService().validateFormData(userid, formData); // Throws
																	// NewCustomException
																	// if
			// Total image Size of user
			// Exceeds Limit

			FileItem image = formData.get("image");
			byte[] imageDataInBytes = image.get();

			UserDetails user = new UserDetails();
			user.setId(userid);
			UserImage userImg = new UserImage(image.getName(),
					imageDataInBytes, user);

			uploaded = new ImageDao().uploadUserImage(userImg);

			new UserService().updateTotalImageSize(userid,
					imageDataInBytes.length, -1);

		} catch (NewCustomException exception) {
			throw exception;
		}
		return uploaded;
	}

}

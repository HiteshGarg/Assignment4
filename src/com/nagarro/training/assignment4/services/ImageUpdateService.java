package com.nagarro.training.assignment4.services;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItem;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.customException.NewCustomException;
import com.nagarro.training.assignment4.dao.ImageDao;
import com.nagarro.training.assignment4.pojo.UserImage;

public class ImageUpdateService {
	/**
	 * updates the userImage or name or both
	 * @param request
	 * @return
	 * @throws NewCustomException
	 */
	public Boolean updateUserImage(HttpServletRequest request)
			throws NewCustomException {

		Boolean updated = false;
		Map<String, FileItem> formData = new HashMap<String, FileItem>();
		try {
			formData = FormDataService.getFilesList(request);
			Integer imageId = Integer.parseInt(formData.get("id").getString());
			Integer userid = (Integer) request.getSession().getAttribute(
					Constants.SESSION_USER_ID);
			
			new ImageService().validateFormData(userid, formData);

			UserImage image = ImageDao.getImageById(imageId);

			String name = formData.get("name").getString();
			if (name.length() > 0) {
				image.setImageName(name);
			}

			String imageName = formData.get("update_image").getName();
			byte[] imageByteArray = null;
			if (imageName != "") {

				FileItem fileItem = formData.get("update_image");
				imageByteArray = fileItem.get();
				image.setImage(imageByteArray);
				
				new UserService().updateTotalImageSize(userid,
						imageByteArray.length, imageId);
			}
			updated = new ImageDao().updateImageInDB(image);
		} catch (NewCustomException exception) {
			throw exception;
		}
		return updated;
	}

}

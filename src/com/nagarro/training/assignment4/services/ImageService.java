package com.nagarro.training.assignment4.services;

import java.util.Map;

import org.apache.tomcat.util.http.fileupload.FileItem;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.customException.NewCustomException;
import com.nagarro.training.assignment4.dao.ImageDao;
import com.nagarro.training.assignment4.dao.UserDao;
import com.nagarro.training.assignment4.pojo.UserDetails;
import com.nagarro.training.assignment4.pojo.UserImage;

public class ImageService {


	/**
	 * Validates all the non form items available in the Map of name and FileItem
	 * @param userid
	 * @param formData
	 * @throws NewCustomException
	 */
	public void validateFormData(Integer userid, Map<String, FileItem> formData)
			throws NewCustomException {
		for (Map.Entry<String, FileItem> entry : formData.entrySet()) {
			if (!entry.getValue().isFormField()) {
				Boolean validate = validateTotalImageSize(userid, entry.getValue().getSize(), -1);
				if (!validate) {
					throw new NewCustomException(
							Constants.TOTAL_IMAGE_SIZE_EXCEEDS);
				}
			}
		}
	}

	
	/**
	 * Validates total size of images set by the user
	 * @param userId
	 * @param imageSize
	 * @param imageId
	 * @return
	 * @throws NewCustomException
	 */
	public boolean validateTotalImageSize(Integer userId,
			Long imageSize, Integer imageId) throws NewCustomException {
		Boolean validate = false;
		Long updateImageSize = (long) 0;

		try {
			UserDetails user = UserDao.getUserById(userId);
			Long totalSize = user.getTotalSize();

			if (imageId > 0) {
				UserImage image = ImageDao.getImageById(imageId);
				updateImageSize = (long) image.getImage().length;
			}
			totalSize = totalSize + imageSize - updateImageSize;

			if (totalSize < Constants.TOTAL_USER_UPLOAD_SIZE) {
				validate = true;
			}

		} catch (NewCustomException exception) {
			throw exception;
		}
		return validate;
	}
}
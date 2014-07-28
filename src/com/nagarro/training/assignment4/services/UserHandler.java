/**
 * 
 */
package com.nagarro.training.assignment4.services;

import com.nagarro.training.assignment4.DAO.ImageHandler;
import com.nagarro.training.assignment4.DAO.UserDAO;
import com.nagarro.training.assignment4.POJO.UserDetails;
import com.nagarro.training.assignment4.POJO.UserImage;
import com.nagarro.training.assignment4.customException.NewCustomException;

/**
 * @author Hitesh
 *
 */
public class UserHandler {

	/*
	 * "userId" Is the Id of the user Currently in the session .. "imageSize" Is
	 * the size of the image uploaded in bytes (as new or updated image) .. "imageId" -> is
	 * the Id of existing image in case of updation or deletion.
	 */
	public Boolean updateTotalImageSize(Integer userId, Integer imageSize, Integer imageId)
			throws NewCustomException {
		try {
			UserDetails user = UserDAO.getUserById(userId);
			long totalSize = user.getTotalSize();
			if(imageSize> 0){
				totalSize = totalSize + imageSize;
			}
			if(imageId>0){
			UserImage image = ImageHandler.getImageById(imageId);
			totalSize = totalSize - image.getImage().length;
			}
			user.setTotalSize(totalSize);
			UserDAO.updateUser(user);
		} catch (NewCustomException exception) {
			String message = exception.getErrorMessage()
					+ "Error in Updating Total Image Size";
			exception.setMessage(message);
			throw exception;
		}
		return null;
	}

}

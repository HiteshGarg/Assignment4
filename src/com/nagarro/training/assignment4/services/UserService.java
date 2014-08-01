/**
 * 
 */
package com.nagarro.training.assignment4.services;


import com.nagarro.training.assignment4.customException.NewCustomException;
import com.nagarro.training.assignment4.dao.ImageDao;
import com.nagarro.training.assignment4.dao.UserDao;
import com.nagarro.training.assignment4.pojo.UserDetails;
import com.nagarro.training.assignment4.pojo.UserImage;

/**
 * @author Hitesh
 * 
 */
public class UserService {

	/**
	 *  "userId" Is the Id of the user Currently in the session .. "imageSize" Is
	 * the size of the image uploaded in bytes (as new or updated image) ..
	 * "imageId" -> is the Id of existing image in case of updation or deletion.
	 * 
	 * @param userId
	 * @param imageSize
	 * @param imageId
	 * @return
	 * @throws NewCustomException
	 */
	public Boolean updateTotalImageSize(Integer userId, Integer imageSize,
			Integer imageId) throws NewCustomException {
		try {
			UserDetails user = UserDao.getUserById(userId);
			long totalSize = user.getTotalSize();
			if (imageSize > 0) {
				totalSize = totalSize + imageSize;
			}
			if (imageId > 0) {
				UserImage image = ImageDao.getImageById(imageId);
				totalSize = totalSize - image.getImage().length;
			}
			user.setTotalSize(totalSize);
			new UserDao().updateUser(user);
		} catch (NewCustomException exception) {
			String message = exception.getErrorMessage()
					+ "Error in Updating Total Image Size";
			exception.setMessage(message);
			throw exception;
		}
		return null;
	}

}

/**
 * 
 */
package com.nagarro.training.assignment4.services;

import javax.servlet.http.HttpServletRequest;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.customException.NewCustomException;
import com.nagarro.training.assignment4.dao.ImageDao;

/**
 * @author hiteshgarg
 * 
 */
public class ImageDeleteService {

	/**
	 * Deletes User Image using its Id
	 * @param request
	 * @return
	 * @throws NewCustomException
	 */
	public Boolean imageDelete(HttpServletRequest request)
			throws NewCustomException {
		Boolean deleted = false;
		try {
			Integer imageId = Integer.parseInt(request.getParameter("id"));
			Integer userId = (Integer) request.getSession().getAttribute(
					Constants.SESSION_USER_ID);
			new UserService().updateTotalImageSize(userId, -1, imageId);
			new ImageDao().removeimagefromDB(imageId);
			deleted = true;
		} catch (NewCustomException exception) {
			throw exception;
		}
		return deleted;
	}

}

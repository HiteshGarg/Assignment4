/**
 * 
 */
package com.nagarro.training.assignment4.services;

import javax.servlet.http.HttpServletRequest;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.customException.NewCustomException;
import com.nagarro.training.assignment4.dao.impl.UserDaoImpl;

/**
 * @author hiteshgarg
 *
 */
public class LoginService {

	public Boolean validateLogin(HttpServletRequest request)
			throws NewCustomException {
		String uname = request.getParameter("username");
		String pwd = request.getParameter("password");
		Boolean validated = false;
		try {
			Integer userId = UserDaoImpl.validateUser(uname, pwd);
			if (null != userId) {
				validated = true;
				setSessionAttributes(request, userId);
			}
		} catch (NewCustomException exception) {
			throw exception;
		}
		return validated;
	}

	public void setSessionAttributes(HttpServletRequest request,
			Integer userId) throws NewCustomException {
	
//			UserDetails user = UserDaoImpl.getUserById(userId);
			request.getSession()
					.setAttribute(Constants.SESSION_USER_ID, userId);
//			request.getSession().setAttribute(
//					Constants.SESSION_USER_IMAGE_SIZE, user.getTotalSize());
		
	
	}

}

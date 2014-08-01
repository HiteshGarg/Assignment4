/**
 * 
 */
package com.nagarro.training.assignment4.services;

import javax.servlet.http.HttpServletRequest;

import com.nagarro.training.assignment4.Constants.Constants;
import com.nagarro.training.assignment4.customException.NewCustomException;
import com.nagarro.training.assignment4.dao.UserDao;

/**
 * @author hiteshgarg
 * 
 */
public class LoginService {
	/**
	 * validate User based on the username and Password
	 * @param request
	 * @return
	 * @throws NewCustomException
	 */
	public Boolean validateLogin(HttpServletRequest request)
			throws NewCustomException {
		String uname = request.getParameter("username");
		String pwd = request.getParameter("password");
		Boolean validated = false;
		try {
			Integer userId = UserDao.validateUser(uname, pwd);
			if (null != userId) {
				validated = true;
				request.getSession().setAttribute(Constants.SESSION_USER_ID, userId);
			}
		} catch (NewCustomException exception) {
			throw exception;
		}
		return validated;
	}

}

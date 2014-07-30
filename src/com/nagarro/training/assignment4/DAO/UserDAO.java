package com.nagarro.training.assignment4.dao;

import com.nagarro.training.assignment4.pojo.UserDetails;

public interface UserDao {

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Integer validateUser(String username, String password); 

	/**
	 * 
	 * @param userId
	 * @return
	 */
	public UserDetails getUserById(Integer userId);
	
	/**
	 * 
	 * @param user
	 */
	public void updateUser(UserDetails user);
}

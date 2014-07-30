/**
 * 
 */
package com.nagarro.training.assignment4.dao;

import java.util.List;

import com.nagarro.training.assignment4.customException.NewCustomException;
import com.nagarro.training.assignment4.pojo.UserImage;

/**
 * @author hiteshgarg
 * 
 */
public interface ImageDao {

	/**
	 * 
	 * @param imageList
	 * @return
	 * @throws NewCustomException 
	 */
	public Boolean uploadUserImage(UserImage image) throws NewCustomException;
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public List<UserImage> listUserImages(Integer userId);

	/**
	 * 
	 * @param imageId
	 * @return
	 */
	public Integer removeimagefromDB(Integer imageId); 

	/**
	 * 
	 * @param image
	 * @return
	 */
	public Boolean updateImageInDB(UserImage image);

	/**
	 * 
	 * @param imageId
	 * @return
	 */
	public UserImage getImageById(Integer imageId);

}

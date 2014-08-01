/**
 * @author hiteshgarg
 * This class is used to delcare various constants that are used at various locations in the project.
 */

package com.nagarro.training.assignment4.Constants;

public class Constants {

	/**
	 * name used to set session Id
	 */
	public static final String SESSION_USER_ID = "userId";
	/**
	 * name used to set total Image Size of a User
	 */
	public static final String SESSION_USER_IMAGE_SIZE = "totalImageSize";
	/**
	 * name of jsp which displays the Image List
	 */
	public static final String IMAGE_REPOSITORY_MESSAGES = "repositoryMessages";
	/**
	 * Successful Image upload message
	 */
	public static final String IMAGE_UPLOAD_SUCCESS = "Image Uploaded Successfully";
	/**
	 * Failed Image upload message
	 */
	public static final String IMAGE_UPLOAD_FAIL = "Image upload unsuccessful.... Please try again";

	/**
	 * Successful Image Deletion message
	 */
	public static final String IMAGE_DELETE_SUCCESS = "Image Deleted Successfully";
	/**
	 * Failed Image Deletion message
	 */
	public static final String IMAGE_DELETE_FAIL = "Image deletion was unsuccessful.... Please try again";
	/**
	 * Successful Image Updation message
	 */
	public static final String IMAGE_UPDATE_SUCCESS = "Image updated Successfully";
	/**
	 * Failed Image updation message
	 */
	public static final String IMAGE_UPDATE_FAIL = "Image updation was unsuccessful.... Please try again";

	/**
	 * Unexpected Error message
	 */
	public static final String UNEXPECTED_ERROR = "Unexpected Error Occured...Please try again";
	/**
	 * Individual Image size exceeding error message
	 */
	public static final String FILE_SIZE_EXCEEDED = "File Size is greater than Limit(1 MB)";
	/**
	 * File upload Error message
	 */
	public static final String FILE_UPLOAD_ERROR = "Error in uploading File Please try again";
	/**
	 *Max upload size of file
	 */
	public static final long MAX_UPLOAD_FILE_SIZE = 1024 * 1024 * 1;// MB
	/**
	 *Max upload size of files of a user
	 */
	public static final long TOTAL_USER_UPLOAD_SIZE = 1024 * 1024 * 10;// MB
	/**
	 * Total Image Size exceeding Error
	 */
	public static final String TOTAL_IMAGE_SIZE_EXCEEDS = "Total Image Size has exceeded 10 MB.. "
			+ "Plase try again with smaller Size";
	/**
	 * error message for server related problems
	 */
	public static final String ERROR_CONTACTING_SERVER = "Error in contacting Server ... Please try again after sometime";
	/**
	 * Servlet name that is to be called every time we go to image repository
	 */
	public static final String IMAGE_RETRIEVER = "ImageRetrieval";
}

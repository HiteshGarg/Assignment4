/**
 * @author hiteshgarg
 * This class is used to delcare various constants that are used at various locations in the project.
 */

package com.nagarro.training.assignment4.Constants;

public class Constants {

	public static final String SESSION_USER_ID = "userId";
	public static final String SESSION_USER_IMAGE_SIZE = "totalImageSize";
	public static final String IMAGE_REPOSITORY_MESSAGES = "repositoryMessages";
	public static final String IMAGE_UPLOAD_SUCCESS = "Image Uploaded Successfully";
	public static final String IMAGE_UPLOAD_FAIL = "Image upload unsuccessful.... Please try again";

	public static final String IMAGE_DELETE_SUCCESS = "Image Deleted Successfully";
	public static final String IMAGE_DELETE_FAIL = "Image deletion was unsuccessful.... Please try again";
	public static final String IMAGE_UPDATE_SUCCESS = "Image updated Successfully";
	public static final String IMAGE_UPDATE_FAIL = "Image updation was unsuccessful.... Please try again";

	public static final String UNEXPECTED_ERROR = "Unexpected Error Occured...Please try again";
	public static final String FILE_SIZE_EXCEEDED = "File Size is greater than Limit(1 MB)";
	public static final String FILE_UPLOAD_ERROR = "Error in uploading File Please try again";
	public static final long MAX_UPLOAD_FILE_SIZE = 1024 * 1024 * 1;// MB
	public static final long TOTAL_USER_UPLOAD_SIZE = 1024 * 1024 * 10;// MB
	public static final String TOTAL_IMAGE_SIZE_EXCEEDS = "Total Image Size has exceeded 10 MB.. "
			+ "Plase try again with smaller Size";
	public static final String ERROR_CONTACTING_SERVER = "Error in contacting Server ... Please try again after sometime";
	public static final String IMAGE_RETRIEVER = "ImageRetrieval";
}

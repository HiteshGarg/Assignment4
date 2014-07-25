/**
 * @author hiteshgarg
 * This class is used to delcare various constants that are used at various locations in the project.
 */

package com.nagarro.training.assignment4.Constants;

public class Constants {

	public static final String WELCOME_MESSAGE = "Welcome to Flight Search program."
			+ " Please Enter the Details as Asked.";
	public static final String ENTER_DEPARTURE_LOCATION = "Departure Location : ";
	public static final String ENTER_ARRIVAL_LOCATION = "Arrival Location : ";
	public static final String ENTER_FLIGHT_DATE = "Flight Date(Format dd-mm-yyyy) : ";
	public static final String ENTER_FLIGHT_CLASS = "Flight Class(E = Economy or B = Business) : ";
	public static final String ENTER_OUTPUT_PREFERENCES = "Output Preferences(For Fare press 1  "
			+ "for fare and flight duration press 2) : ";
	public static final String INCORRECT_LOCATION_ERROR = "Please enter location in correct format"
			+ "(Use 3 digit standard code format)";
	public static final String INCORRECT_CLASS_ERROR = "Please enter Proper class";
	public static final String INCORRECT_PREFERENCE_ERROR = "Please enter preference from the given options only";
	public static final String INCORRECT_DATE_ERROR = "Please enter date in correct format";
	public static final String CSV_FILES_URL = "AirlinesCsvFiles";
	public static final String CSV_SPLIT_DELIMITTER = "\\|";

	
	
	public static final String SESSION_USER_ID = "userId";
	public static final String IMAGE_REPOSITORY_MESSAGES = "imageUploadStatus";
	public static final String IMAGE_UPLOAD_SUCCESS = "Image Uploaded Successfully";
	public static final String IMAGE_UPLOAD_FAIL = "Image upload unsuccessful.... Please try again";
	
	public static final String IMAGE_DELETE_SUCCESS = "Image Deleted Successfully";
	public static final String IMAGE_DELETE_FAIL = "Image deletion was unsuccessful.... Please try again";
	public static final String UNEXPECTED_ERROR = "Unexpected Error Occured...Please try again";
}

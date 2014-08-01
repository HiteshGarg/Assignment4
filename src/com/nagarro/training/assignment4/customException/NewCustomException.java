/**
 * @author hiteshgarg
 * A custom Exception class.
 */
package com.nagarro.training.assignment4.customException;

public class NewCustomException extends Exception {

	
	private static final long serialVersionUID = 4076611916863296393L;
	private String message = null;

	/**
	 * Default Constructor
	 */
	public NewCustomException(){
		super();
	}
	/**
	 * Parameterised constructor
	 * @param message
	 */
	public NewCustomException(String message) {
		super();
		this.message = message;
	}
	
	/**
	 * 
	 * @return
	 */
	
	public String getErrorMessage(){
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

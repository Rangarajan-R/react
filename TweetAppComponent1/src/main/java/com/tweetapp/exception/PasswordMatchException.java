package com.tweetapp.exception;

public class PasswordMatchException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordMatchException(String message) {
        super(message);
    }
}

package com.rental.exception;

/**
 * throws when authentication fails
 */


public class AuthenticationException extends RuntimeException {
    /**
     * Constructs a new AuthenticationException with the given message 
     *
     * @param message the detail message explaining the reason for the exception reason
     */
	
	
	
    public AuthenticationException(String message) {
        super(message);
    }
}

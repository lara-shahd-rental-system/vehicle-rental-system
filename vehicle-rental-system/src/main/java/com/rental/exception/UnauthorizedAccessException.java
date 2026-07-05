


package com.rental.exception;

/**
 * when a user attempts an action they are not authorized to act
 */ 


public class UnauthorizedAccessException extends Exception 
{

    /** 
     *   Constructs a new UnauthorizedAccessException with the given message
     *
     *  @param message the detail message explaining the exception reason 
     * 
     */
    public UnauthorizedAccessException(String message) {
        super(message);
        
    }
    
    
}



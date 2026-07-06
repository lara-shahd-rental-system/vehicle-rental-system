package com.rental.service;

import com.rental.domain.model.User;
import com.rental.exception.AuthenticationException;
import com.rental.persistence.UserRepository;

/**
 * Handles user authentication: login,logout,and tracking the currently logged-in user.
 */
public class AuthenticationService
{

    private UserRepository userRepository ;
    private User currentUser ;

    /**
     * Constructs a new AuthenticationService with the given UserRepository.
     *
     * @param userRepository the repository used to look up users
     */
    
    // *************************  Dependency Injection 
    
    public AuthenticationService(UserRepository userRepository)  
    {
        this.userRepository = userRepository ;
        this.currentUser = null ;
    }

    /**
     * Attempts to log in a user with the given username and password.
     *
     * @param username the username to log in with
     * @param password the password to log in with
     * @throws AuthenticationException if the username or password is invalid
     */
    public void login(String username, String password) throws AuthenticationException
    {
        if (username == null || username.trim().isEmpty())
        {
            throw new AuthenticationException("Username cannot be null or empty") ;
        }
        if (password == null || password.trim().isEmpty())
        {
            throw new AuthenticationException("Password cannot be null or empty") ;
        }

        User foundUser = userRepository.findByUsername(username) ;

        if (foundUser == null)                                                                 //Brute Force attack protection,we used the same msg
        {
            throw new AuthenticationException("Invalid username or password") ;
        }
        if (!foundUser.getPassword().equals(password))
        {
            throw new AuthenticationException("Invalid username or password") ;
        }

        this.currentUser = foundUser ;
    }

    /**
     * Logs out the current user. If no user is logged in, does nothing 
     */
 // safelyyyy nothing done 
    
    public void logout()
    {
        this.currentUser = null ;
    }

    /**
     * Checks whether a user is currently logged in.
     *
     * @return true if a user is logged in, false otherwise 
     * 
     */
    public boolean isLoggedIn ()
    {
        return this.currentUser != null ;
    }

    /**
     * Returns the currently logged-in user.
     *
     * @return the current User, or null if no user is logged in
     */
    public User getCurrentUser()
    {
        return this.currentUser ;
    }

}
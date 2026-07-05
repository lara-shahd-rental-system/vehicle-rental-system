
package com.rental.persistence;



import java.util.HashMap;
import java.util.Map;
import com.rental.domain.model.User;


/**
 * Stores and retrieves User objects using HashMap 
 */


public class UserRepository 
{

    private Map <String, User>  users;

   /**
     * Constructs a new, empty UserRepository
     */
    
    public UserRepository() {
        this.users = new HashMap<>();
    }
    
    // **************************************************
   
    /**
     * Adds a new user to the repository.
     *
     * @param user the user to add
     * @throws IllegalArgumentException if user is null or a user with the same username already exists
     */
    
    public void  addUser(User user) 
    { 
        if (user ==null) 
        {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (users.containsKey(user. getUsername()) )
        {
            throw new IllegalArgumentException("Username already exists: " + user. getUsername() );
        }
        users.put(user. getUsername (), user );
    }
    
    // ***** lookfor the user name and get it or null 
    
    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return the User if found, or null if no user with that username exists
     * @throws IllegalArgumentException if username is null or empty
     */
    public User findByUsername(String username)
    {
        if (username == null || username.trim().isEmpty()) 
        {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        return users.get(username);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
package com.rental.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.rental.persistence.UserRepository;

// class tests the UserRepository (adding and finding users)

public class UserRepositoryTest
{

    private UserRepository userRepository ;
    private User user ;

    
    @BeforeEach                                        // before test 
    public void setUp()
    {
        userRepository = new UserRepository() ;
        user = new User("lara123", "pass123", "ENGINEER") ;
    }

    
    // ************************************************************************edge 
    
 // Add a user, then find them by username. Should return the same user.
    @Test
    public void testAddUser_ThenFindByUsername_ReturnsSameUser()
    {
        userRepository.addUser(user) ;
        User found = userRepository.findByUsername("lara123") ;
        assertEquals(user, found) ;
    }

    // Search for a username that was never added. Should return null.
    @Test
    public void testFindByUsername_UserNotFound_ReturnsNull()
    {
        User found = userRepository.findByUsername("notExist") ;
        assertNull(found) ;
    }

    // Try to add a null user. Should throw an exception.
    @Test
    public void testAddUser_WithNullUser_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userRepository.addUser(null) ;
        }) ;
        assertEquals("User cannot be null", exception.getMessage()) ;
    }

    // (DUPLICATION )Try to add a second user with the same username as an existing one. Should throw an exception.
    @Test
    public void testAddUser_WithDuplicateUsername_ThrowsException()
    {
        userRepository.addUser(user) ;
        User duplicateUser = new User("lara123", "otherPass", "MANAGER") ;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userRepository.addUser(duplicateUser) ;
        }) ;
        assertEquals("Username already exists: lara123", exception.getMessage()) ;
    }

    // Search using a null username. Should throw an exception.
    @Test
    public void testFindByUsername_WithNull_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userRepository.findByUsername(null) ;
        }) ;
        assertEquals("Username cannot be null or empty", exception.getMessage()) ;
    }

    // Search using an empty username (""). Should throw an exception.
    @Test
    public void testFindByUsername_WithEmpty_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userRepository.findByUsername("") ;
        }) ;
        assertEquals("Username cannot be null or empty", exception.getMessage()) ;
    }

    // Search using a username that is just spaces ("   "). Should throw an exception.
    @Test
    public void testFindByUsername_WithBlank_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            userRepository.findByUsername("   ") ;
        }) ;
        assertEquals("Username cannot be null or empty", exception.getMessage()) ;
    }

    // IMPRTANT ** Add two different users, and make sure both can be found correctly (no mixing up data)// check the map work well
  
    @Test
    public void testAddUser_MultipleUsers_AllRetrievable()
    {
        userRepository.addUser(user) ;
        User secondUser = new User("sara456", "pass456", "MANAGER") ;
        userRepository.addUser(secondUser) ;

        assertEquals(user, userRepository.findByUsername("lara123")) ;
        assertEquals(secondUser, userRepository.findByUsername("sara456")) ;
    }

    //  INTEGRITY When adding a duplicate username fails, the original user's data must stay unchanged 
    @Test
    public void testAddUser_DuplicateUsername_OriginalUserRemainsUnchanged()
    {
        userRepository.addUser(user) ;
        User duplicateUser = new User("lara123", "differentPass", "MANAGER") ;

        try
        {
            userRepository.addUser(duplicateUser) ;
        }
        catch (IllegalArgumentException e)
        {
            // Expected— do nothing
        }

        User found = userRepository.findByUsername("lara123") ;
        assertEquals("pass123", found.getPassword()) ;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
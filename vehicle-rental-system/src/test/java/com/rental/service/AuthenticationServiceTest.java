package com.rental.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.rental.domain.model.User;
import com.rental.persistence.UserRepository;
import com.rental.exception.AuthenticationException;

public class AuthenticationServiceTest
{

    private UserRepository userRepository ;
    private AuthenticationService authService ;
    private User testUser ;

    @BeforeEach
    public void setUp()
    {
        userRepository = mock(UserRepository.class) ;
        authService = new AuthenticationService(userRepository) ;    // inject the fake repository into the service
        testUser = new User("lara123", "pass123", "ENGINEER") ;
    }

    
    @Test            
    // login with correct username and password should succeed
    public void testLogin_WithCorrectCredentials_SetsCurrentUser() throws AuthenticationException
    {
        when(userRepository.findByUsername("lara123")).thenReturn(testUser) ;

        authService.login("lara123", "pass123") ;

        assertEquals(testUser, authService.getCurrentUser()) ;
    }

    @Test    
    // login with correct username and password should mark user as logged in
    public void testLogin_WithCorrectCredentials_IsLoggedInTrue() throws AuthenticationException
    {
        when(userRepository.findByUsername("lara123")).thenReturn(testUser) ;

        authService.login("lara123", "pass123") ;

        assertTrue(authService.isLoggedIn()) ;
    }

    @Test    
    // login with a username that does not exist should throw exception
    public void testLogin_WithNonExistentUsername_ThrowsException()
    {
        when(userRepository.findByUsername("notExist")).thenReturn(null) ;

        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authService.login("notExist", "pass123") ;
        }) ;
        assertEquals("Invalid username or password", exception.getMessage()) ;
    }

    
    @Test    
    // login with correct username but wrong password should throw exception
    public void testLogin_WithWrongPassword_ThrowsException()
    {
        when(userRepository.findByUsername("lara123")).thenReturn(testUser) ;

        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authService.login("lara123", "wrongPass") ;
        }) ;
        assertEquals("Invalid username or password", exception.getMessage()) ;
    }

    
    @Test    // login with null username should throw exception            // no when function no need for mock !!!
    public void testLogin_WithNullUsername_ThrowsException()
    {
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authService.login(null, "pass123") ;
        }) ;
        assertEquals("Username cannot be null or empty", exception.getMessage()) ;
    }

    
    @Test   
    // login with empty username should throw exception
    public void testLogin_WithEmptyUsername_ThrowsException()
    {
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authService.login("", "pass123") ;
        }) ;
        assertEquals("Username cannot be null or empty", exception.getMessage()) ;
    }

    
    @Test   
    // login with null password should throw exception
    public void testLogin_WithNullPassword_ThrowsException()
    {
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authService.login("lara123", null) ;
        }) ;
        assertEquals("Password cannot be null or empty", exception.getMessage()) ;
    }

    
    @Test    
    // login with empty password should throw exception
    public void testLogin_WithEmptyPassword_ThrowsException()
    {
        Exception exception = assertThrows(AuthenticationException.class, () -> {
            authService.login("lara123", "") ;
        }) ;
        assertEquals("Password cannot be null or empty", exception.getMessage()) ;
    }

    
    @Test    
    // after logging in, logout should clear the current user
    public void testLogout_AfterLogin_ClearsCurrentUser() throws AuthenticationException
    {
        when(userRepository.findByUsername("lara123")).thenReturn(testUser) ;
        authService.login("lara123", "pass123") ;

        authService.logout() ;

        assertNull(authService.getCurrentUser()) ;
    }

    
    @Test   
    // after logout, isLoggedIn should return false
    public void testLogout_AfterLogin_IsLoggedInFalse() throws AuthenticationException
    {
        when(userRepository.findByUsername("lara123")).thenReturn(testUser) ;
        authService.login("lara123", "pass123") ;

        authService.logout() ;

        assertFalse(authService.isLoggedIn()) ;
    }

    
    @Test   
    // calling logout without logging in first should not cause a crash 
    
    public void testLogout_WithoutLogin_DoesNothing()
    {
        authService.logout() ;

        assertNull(authService.getCurrentUser()) ;
    }

    
    
    @Test   
    // before any login, isLoggedIn should return false
    public void testIsLoggedIn_BeforeLogin_ReturnsFalse()
    {
        assertFalse(authService.isLoggedIn()) ;
    }
    

    @Test   
    // before any login, getCurrentUser should return null
    public void testGetCurrentUser_BeforeLogin_ReturnsNull()
    {
        assertNull(authService.getCurrentUser()) ;
    }

}
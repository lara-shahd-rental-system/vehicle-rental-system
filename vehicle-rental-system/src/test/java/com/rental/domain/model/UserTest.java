package com.rental.domain.model;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
  
public class UserTest 
{
    private User user;
 
    @BeforeEach
    public void setUp()
    {
      user = new User("lara123", "pass123", "ENGINEER");
    }

    @Test
    public void testGetUsername_ReturnsCorrectUsername() 
    {
        assertEquals("lara123", user.getUsername());
    }
   
    @Test
    public void testGetPassword_ReturnsCorrectPassword() 
    {
        assertEquals("pass123", user.getPassword() );
    }
    
    @Test
    public void testGetRole_ReturnsCorrectRole() 
    {
        assertEquals("ENGINEER", user.getRole());
    }
    
    // new values test if = new not old 
    
    @Test  // testing new value if = new not old 
    public void testSetUsername_UpdatesUsername() 
    {
        user.setUsername("newLara");
        assertEquals("newLara", user.getUsername());
    }
    
    @Test
    public void testSetPassword_UpdatesPassword()  
    {
        user.setPassword("newPass456");
        assertEquals("newPass456", user.getPassword());
    }
    @Test
    public void testSetRole_UpdatesRole()
    {
        user.setRole("ADMIN");
        assertEquals("ADMIN", user.getRole());
    }
    
    // test exception 
    @Test
    public void testConstructor_WithNullUsername_ThrowsException() 
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new User(null, "pass123", "ENGINEER");
        });
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }
     // edge cases********************************************************************
    
    @Test          // NULL PASSWORD 
    public void testConstructor_WithNullPassword_ThrowsException() 
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        {
            new User("lara123", null, "ENGINEER");
        });
        assertEquals("Password cannot be null or empty", exception.getMessage());
    }

    @Test          // NULL TEST 
    public void testConstructor_WithNullRole_ThrowsException() 
    
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new User("lara123", "pass123", null);
        });
        assertEquals("Role cannot be null or empty", exception.getMessage());
    }

    @Test          // EMPTY TEST 
    public void testConstructor_WithEmptyUsername_ThrowsException() 
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new User("", "pass123", "ENGINEER");
        });
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }

    @Test           // EMPTY TEST WITH "" 
    public void testConstructor_WithBlankUsername_ThrowsException() 
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> 
        {
            new User("   ", "pass123", "ENGINEER");
        });
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }
    
    
    @Test             // EMPTY PASSWORD
    public void testConstructor_WithEmptyPassword_ThrowsException() 
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
        {
            new User("lara123", "", "ENGINEER");
        });
        assertEquals("Password cannot be null or empty", exception.getMessage());
    }
   
    @Test           // EMPTY ROLE
    public void testConstructor_WithEmptyRole_ThrowsException() 
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new User("lara123", "pass123", "");
        });
        assertEquals("Role cannot be null or empty", exception.getMessage());
    }
    
 
    @Test                      // CHECKS NO NULL VALUES IN SETUSERNAME
    public void testSetUsername_WithNull_ThrowsException() 
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername(null);
        });
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }

   
    @Test                     // CHECKS NO EMPTY VALUES IN SETUSERNAME
    public void testSetUsername_WithEmpty_ThrowsException() 
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername("");
        });
        assertEquals("Username cannot be null or empty", exception.getMessage());
    }

   
    @Test                    // CHECKS NO NULL VALUES IN SETPASSWORD
    public void testSetPassword_WithNull_ThrowsException() 
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword(null);
        });
        assertEquals("Password cannot be null or empty", exception.getMessage());
    }

    //  // CHECKS NO EMPTY VALUES IN SETPASSWORD
    @Test
    public void testSetPassword_WithEmpty_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword("");
        });
        assertEquals("Password cannot be null or empty", exception.getMessage());
    }

    //  // CHECKS NO NULL VALUES IN SETROLE
    @Test
    public void testSetRole_WithNull_ThrowsException()
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.setRole(null);
        });
        assertEquals("Role cannot be null or empty", exception.getMessage());
    }

    //  // CHECKS NO EMPTY VALUES IN SETROLE
    @Test
    public void testSetRole_WithEmpty_ThrowsException() 
    {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            user.setRole("");
        });
        assertEquals("Role cannot be null or empty", exception.getMessage());
    }
    // new test 
    @Test
    public void testConstructorWithEmail_SetsEmailCorrectly()
    {
        User userWithEmail = new User("lara123", "pass123", "ENGINEER", "lara@email.com");
        assertEquals("lara@email.com", userWithEmail.getEmail());
    }
    // from sprint  5 
    @Test
    public void testFullConstructor_SetsAgeAndLicenseTypeCorrectly()
    {
        User user = new User("lara123", "pass123", "ENGINEER", "lara@email.com", 25, "STANDARD");
        assertEquals(25, user.getAge());
        assertEquals("STANDARD", user.getLicenseType());
    }
}
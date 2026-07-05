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
        assertThrows(IllegalArgumentException.class, () -> {
            new User(null, "pass123", "ENGINEER");
        });
    }
     // edge cases********************************************************************
    
    @Test          // NULL PASSWORD 
    public void testConstructor_WithNullPassword_ThrowsException() 
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new User("lara123", null, "ENGINEER");
        });
    }

    @Test          // NULL TEST 
    public void testConstructor_WithNullRole_ThrowsException() 
    
    {
        assertThrows(IllegalArgumentException.class, () -> {
            new User("lara123", "pass123", null);
        });
    }

    @Test          // EMPTY TEST 
    public void testConstructor_WithEmptyUsername_ThrowsException() 
    {
        assertThrows(IllegalArgumentException.class, () -> {
            new User("", "pass123", "ENGINEER");
        });
    }

    @Test           // EMPTY TEST WITH "" 
    public void testConstructor_WithBlankUsername_ThrowsException() 
    {
        assertThrows(IllegalArgumentException.class, () -> 
        {
            new User("   ", "pass123", "ENGINEER");
        });
    }
    
    
    @Test             // EMPTY PASSWORD
    public void testConstructor_WithEmptyPassword_ThrowsException() 
    {
        assertThrows(IllegalArgumentException.class, () ->
        {
            new User("lara123", "", "ENGINEER");
        });
    }
   
    @Test           // EMPTY ROLE
    public void testConstructor_WithEmptyRole_ThrowsException() 
    {
        assertThrows(IllegalArgumentException.class, () -> {
            new User("lara123", "pass123", "");
        });
    }
    
 
    @Test                      // CHECKS NO NULL VALUES IN SETUSERNAME
    public void testSetUsername_WithNull_ThrowsException() 
    {
        assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername(null);
        });
    }

   
    @Test                     // CHECKS NO EMPTY VALUES IN SETUSERNAME
    public void testSetUsername_WithEmpty_ThrowsException() 
    {
        assertThrows(IllegalArgumentException.class, () -> {
            user.setUsername("");
        });
    }

   
    @Test                    // CHECKS NO NULL VALUES IN SETPASSWORD
    public void testSetPassword_WithNull_ThrowsException() 
    {
        assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword(null);
        });
    }

    //  // CHECKS NO EMPTY VALUES IN SETPASSWORD
    @Test
    public void testSetPassword_WithEmpty_ThrowsException()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            user.setPassword("");
        });
    }

    //  // CHECKS NO NULL VALUES IN SETROLE
    @Test
    public void testSetRole_WithNull_ThrowsException()
    {
        assertThrows(IllegalArgumentException.class, () -> {
            user.setRole(null);
        });
    }

    //  // CHECKS NO EMPTY VALUES IN SETROLE
    @Test
    public void testSetRole_WithEmpty_ThrowsException() 
    {
        assertThrows(IllegalArgumentException.class, () -> {
            user.setRole("");
        });
    }
    
}

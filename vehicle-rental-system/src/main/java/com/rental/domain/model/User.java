package com.rental.domain.model;
/**
 * Represents a system user.
 * Contains the user's login credentials and role information.
 */


public class User
{

	private String username;
	private String password;
	private String role ;


	/**
	 * Constructs a new User with the given credentials and role.
	 *
	 * @param username the user's login username
	 * @param password the user's login password
	 * @param role the user's role (e.g., "Engineer")
	 * @throws IllegalArgumentException if username, password, or role is null or empty
	 */

	public User (String username, String password, String role)
	{
	    if (username == null || username.trim().isEmpty())
	    {
	        throw new IllegalArgumentException("Username cannot be null or empty");
	    }
	    if (password == null || password.trim().isEmpty() )
	    {
	        throw new IllegalArgumentException("Password cannot be null or empty");
	    }
	    if (role == null || role.trim().isEmpty())
	    {
	        throw new IllegalArgumentException("Role cannot be null or empty");
	    }

	    this.username = username;
	    this.password = password;
	    this.role = role;
	}

	/**
	 * Returns the user's username.
	 *
	 * @return the username
	 */
	public String getUsername()
	{
	    return username;
	}

	/**
	 * Sets the user's username.
	 *
	 * @param username the new username
	 * @throws IllegalArgumentException if username is null or empty
	 */
	public void setUsername(String username )
	{
	    if (username == null || username.trim().isEmpty())
	    {
	        throw new IllegalArgumentException("Username cannot be null or empty");
	    }
	    this.username = username ;
	}

	/**
	 * Returns the user's password.
	 *
	 * @return the password
	 */
	public String getPassword()
	{
	    return password;
	}

	/**
	 * Sets the user's password.
	 *
	 * @param password the new password
	 * @throws IllegalArgumentException if password is null or empty
	 */
	public void setPassword( String password)
	{
	    if (password == null || password.trim().isEmpty())
	    {
	        throw new IllegalArgumentException("Password cannot be null or empty");
	    }
	    this.password= password;
	}

	/**
	 * Returns the user's role.
	 *
	 * @return the role
	 */
	public String getRole()
	{
	    return role;
	}

	/**
	 * Sets the user's role.
	 *
	 * @param role the new role
	 * @throws IllegalArgumentException if role is null or empty
	 */
	public void setRole(String role )
	{
	    if (role == null || role.trim().isEmpty())
	    {
	        throw new IllegalArgumentException("Role cannot be null or empty");
	    }
	    this.role =role;
	}

}
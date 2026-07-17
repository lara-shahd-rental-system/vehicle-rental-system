package com.rental.domain.model;

/**
 * Represents a system user.
 * Contains the user's login credentials and role information.
 */
public class User
{
    private String username;
    private String password;
    private String role;
    private String email;
    private int age;
    private String licenseType;

    /**
     * Constructs a new User with the given credentials and role (no email, age, or license type).
     *
     * @param username the user's login username
     * @param password the user's login password
     * @param role the user's role (e.g., "Engineer")
     * @throws IllegalArgumentException if username, password, or role is null or empty
     */
    public User(String username, String password, String role)
    {
        this(username, password, role, null, 0, null);
    }

    /**
     * Constructs a new User with the given credentials, role, and email.
     *
     * @param username the user's login username
     * @param password the user's login password
     * @param role the user's role (e.g., "Engineer")
     * @param email the user's email address (optional, may be null)
     * @throws IllegalArgumentException if username, password, or role is null or empty
     */
    public User(String username, String password, String role, String email)
    {
        this(username, password, role, email, 0, null);
    }

    /**
     * Constructs a new User with full profile data, including age and license type
     * (needed for Sprint 5 vehicle-type eligibility rules).
     *
     * @param username the user's login username
     * @param password the user's login password
     * @param role the user's role (e.g., "Engineer")
     * @param email the user's email address (optional, may be null)
     * @param age the user's age (0 if unknown/not provided)
     * @param licenseType the user's license type (e.g., "STANDARD", "COMMERCIAL"), optional
     * @throws IllegalArgumentException if username, password, or role is null or empty
     */
    public User(String username, String password, String role, String email, int age, String licenseType)
    {
        if (username == null || username.trim().isEmpty())
        {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (password == null || password.trim().isEmpty())
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
        this.email = email;
        this.age = age;
        this.licenseType = licenseType;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        if (username == null || username.trim().isEmpty())
        {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        if (password == null || password.trim().isEmpty())
        {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        this.password = password;
    }

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        if (role == null || role.trim().isEmpty())
        {
            throw new IllegalArgumentException("Role cannot be null or empty");
        }
        this.role = role;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getLicenseType()
    {
        return licenseType;
    }

    public void setLicenseType(String licenseType)
    {
        this.licenseType = licenseType;
    }
}
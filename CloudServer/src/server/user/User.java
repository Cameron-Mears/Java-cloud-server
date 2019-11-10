package server.user;

import com.security.hash.Salt;

import server.kernel.VHD;

/*
 	Server side implementation of user
 */
public class User 
{
	private String username;
	private String hashedPass;
	private Salt passSalt;
	private String email;
	private VHD data;
	private int computeCredits;
	private int accountSecurityLevel;
	
	public String getUsername() 
	{
		return username;
	}
	
	public String getHashedPass() 
	{
		return hashedPass;
	}
	
	public Salt getPassSalt() 
	{
		return passSalt;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public VHD getData() 
	{
		return data;
	}
	
	public int getComputeCredits() 
	{
		return computeCredits;
	}
	
	public int getAccountSecurityLevel() 
	{
		return accountSecurityLevel;
	}
	
	
		
}

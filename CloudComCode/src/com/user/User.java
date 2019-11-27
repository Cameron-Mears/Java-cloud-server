package com.user;

import com.security.hash.Authentication;
import com.security.hash.Salt;


/*
 	Server side implementation of user
 */
public class User 
{
	private String username;
	private byte[] hashedPass;
	private Salt passSalt;
	private String email;
	private byte[] keyHash; //encryption keys are made from user info is user forgets password, they use recovery key to restore
	
	User(String name, String passRaw)
	{
		this.username = name;
		this.passSalt = new Salt();
		this.hashedPass = Authentication.digest(passRaw, passSalt, 10);
		
	}
	
	public String getUsername() 
	{
		return username;
	}
	
	public byte[] getHashedPass() 
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
	
	
	
	
		
}


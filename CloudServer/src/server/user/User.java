package server.user;

import com.security.hash.Salt;

import server.kernel.VHD;

/*
 	Server implementation of user
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
	
	
		
}

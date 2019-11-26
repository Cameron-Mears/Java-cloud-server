package server.security.authentication;

import com.security.hash.SHA256;
import com.security.logon.LongOnRequest;
import com.user.User;

import server.user.base.UserBase;

public final class UserAuthenticator 
{	
	private UserBase userBase;
	private static int hashTimes = 100;
	public UserAuthenticator(UserBase base)
	{
		this.userBase = base;
	}
	
	public User authenticateLogon(LongOnRequest request)
	{
		User usr = userBase.fetch(request.username);
		byte[] passIn = (usr.getPassSalt().get() + request.password).getBytes();
		if (usr == null) return null;
		
		for (int hash = 0; hash < hashTimes; hash++) passIn = SHA256.doHash((new String(passIn) + (usr.getPassSalt().get() + request.password)).getBytes());
		
		if (usr.getHashedPass().equals(new String(passIn))) return usr;
		
		return null;
	}
}

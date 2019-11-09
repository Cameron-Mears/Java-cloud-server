package server.user.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.security.hash.Fletcher16;

import server.user.User;

public final class UserBase implements Serializable
{
	private static final long serialVersionUID = -3282347239432874233L;
	
	public static final int USER_PUSH_SUCESSS = 0;
	public static final int USER_PUSH_ERROR_NAME_IN_USE = 1;
	public static final int USER_PUSH_ERROR_PHONE_IN_USE = 2;
	public static final int USER_PUSH_ERROR_EMAIL_IN_USE = 3;
	
	
	private static final int numberOfBaseIndexs = 1000000000;
	private HashMap<String, User> userLookup;
	
	public UserBase()
	{
		userLookup = new HashMap<String, User>(numberOfBaseIndexs);
	}
	
	public int pushUser(User user)
	{
		return USER_PUSH_SUCESSS;
	}
	
	
	public User fetch(String username)
	{
		return userLookup.get(username);
		
	}
	
	
	
}

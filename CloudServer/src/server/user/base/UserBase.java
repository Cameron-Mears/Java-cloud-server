package server.user.base;

import java.io.Serializable;

import server.user.User;
import server.user.base.table.HashTable;

public final class UserBase implements Serializable
{
	private static final long serialVersionUID = -3282347239432874233L;
	
	public static final int USER_PUSH_SUCESSS = 0;
	public static final int USER_PUSH_ERROR_NAME_IN_USE = 1;
	public static final int USER_PUSH_ERROR_PHONE_IN_USE = 2;
	public static final int USER_PUSH_ERROR_EMAIL_IN_USE = 3;
	
	
	private static final int numberOfBaseIndexs = 1000;
	private HashTable<String, User> userLookup;
	
	public UserBase()
	{
		userLookup = new HashTable<String, User>(numberOfBaseIndexs);
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

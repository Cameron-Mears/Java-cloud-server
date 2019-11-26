package com.user;

public final class UserFactory
{
	public static User generateTemplate(String name, String passRaw)
	{
		User usr = new User(name, passRaw);
		return usr;
	}
}

package com.security.hash;

import java.io.Serializable;

public class Salt implements Serializable
{
	private static final long serialVersionUID = 1446592015613432129L;
	private static final int saltSize = 500;
	private String salt;
	
	public Salt()
	{
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < saltSize; n ++)
		{
			sb.append((char)(Math.random() * 256));
		}
		
		salt = sb.toString();
	}
	
	public String get()
	{
		return salt;
	}
}

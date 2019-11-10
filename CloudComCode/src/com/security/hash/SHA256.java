package com.security.hash;

import java.security.MessageDigest;

public final class SHA256 
{
	static MessageDigest hash;
	
	static
	{
		try
		{
			hash = MessageDigest.getInstance("SHA-256");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static byte[] doHash(byte[] in)
	{
		return hash.digest(in);
	}
}

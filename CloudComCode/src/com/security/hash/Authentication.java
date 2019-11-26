package com.security.hash;

public final class Authentication
{
	public static byte[] digest(String passRaw, Salt salt, int iterations)
	{
		byte[] hash = new byte[1];
		
		for (int count = 0; count < iterations; count ++)
		{
			String dataIn = new String(hash) + passRaw + salt.get();
			hash = SHA256.doHash(dataIn.getBytes());
		}
		return hash;
	}
}

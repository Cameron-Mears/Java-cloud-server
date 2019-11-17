package com.security.encryption;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

public final class KeyFactory
{
	public static Key generateNewAES256()
	{
		byte[] chars = new byte[32];
		for (int index = 0; index < chars.length; index++)
		{
			chars[index] = (byte)(Math.random() * 256);
		}
	
		return new SecretKeySpec(chars, "AES");
	}
}

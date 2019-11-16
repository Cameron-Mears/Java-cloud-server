package com.security.encryption;

import java.io.File;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.filesys.JFile;

public final class EncrytionEngine 
{
	private static byte[] decrypt(byte[] in, String key)
	{
		try
		{
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.DECRYPT_MODE, aesKey);
	        return cipher.doFinal(in);
		}
		catch (Exception e)
		{
			
		}
		return null;
	}
	
	
	private static byte[] encrypt(byte[] in, String key)
	{
		try
		{
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
	        Cipher cipher = Cipher.getInstance("AES");
	        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
	        return cipher.doFinal(in);
		}
		catch (Exception e)
		{
			
		}
		return null;
	}
	
	
	
	
}

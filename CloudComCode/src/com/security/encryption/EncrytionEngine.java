package com.security.encryption;

import java.io.File;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.filesys.JFile;

public final class EncrytionEngine 
{
	private static final String MASTER_KEY = "hgT74GpZ1BNVCrqHSeTWfjtuXHQeu6WM";
	
	
	private static Cipher publicKey;
	private static Cipher privateKey;
	
	static
	{
		Key aesKey = new SecretKeySpec(MASTER_KEY.getBytes(), "AES");
		try
		{
			publicKey = Cipher.getInstance("AES");
			publicKey.init(Cipher.ENCRYPT_MODE, aesKey);
			
			privateKey = Cipher.getInstance("AES");
			privateKey.init(Cipher.DECRYPT_MODE, aesKey);
			System.out.println(privateKey.equals(publicKey));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e){e.printStackTrace();}
		
	}
	
	public static Cipher getPublicKey()
	{
		return publicKey;
	}
	
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

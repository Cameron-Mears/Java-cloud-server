package com.security.encryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;


/*
 * System will use aes256 for storage on the server itself, and use rsa for receving and transmitted data
 */
public final class KeyFactory
{
	public static KeyPair generateNewRSAPair()
	{
		KeyPairGenerator keygen = null;
		try
		{
			keygen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {e.printStackTrace();}
		if (keygen != null) keygen.initialize(2048);
		else return null;
		
		KeyPair pair = keygen.generateKeyPair();
		return pair;
		
	}
	
	public static SecretKey generateAES256()
	{
		try
		{
			SecureRandom rand = new SecureRandom();
			
			StringBuilder sbkey = new StringBuilder();
			StringBuilder sbsalt = new StringBuilder();
			for (int n = 0; n < 32; n ++)
			{
				sbkey.append(rand.nextDouble() * 256);
				sbsalt.append(rand.nextDouble() * 256);
			}
			String key = sbkey.toString();
			String salt = sbsalt.toString();
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(key.toCharArray(), salt.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
	
			return secret;
		}
		catch (Exception e) {e.printStackTrace();}
		
		return null;
	}
}

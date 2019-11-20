package com.security.encryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.KeyGenerator;
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
		SecureRandom rand = new SecureRandom();
		KeyGenerator keygen = null;
		try
		{
			keygen = KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		keygen.init(256, rand);
		SecretKey key = keygen.generateKey();
		return key;
	}
}

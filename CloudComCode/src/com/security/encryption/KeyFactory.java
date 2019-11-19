package com.security.encryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;


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
}

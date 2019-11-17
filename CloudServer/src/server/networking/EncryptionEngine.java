package server.networking;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public final class EncryptionEngine 
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
	
	public static Cipher getPrivateKey()
	{
		return privateKey;
	}
	
	
	
}
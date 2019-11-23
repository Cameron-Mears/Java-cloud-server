package server.kernel;


import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

import com.security.encryption.AES256;
import com.security.encryption.KeyFactory;

import server.networking.EncryptionEngine;
import server.user.base.table.HashTable;

public final class Boot 
{
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException, InvalidKeySpecException, InvalidParameterSpecException, InvalidAlgorithmParameterException, InterruptedException 
	{	
		
		SecretKey secret = KeyFactory.generateAES256();
		File file = new File("C:\\Users\\Cameron\\Videos\\Desktop\\Desktop 2019.06.19 - 19.05.49.01.mp4");
		byte[] iv = EncryptionEngine.encryptFile(secret, file);
		EncryptionEngine.decryptFile(secret, new File("C:\\Users\\Cameron\\Videos\\Desktop\\Desktop 2019.06.19 - 19.05.49.01.mp4.serverEncrypt"), iv);
		
		//Server server = new Server();
		HashTable<String, Integer> test = new HashTable<String, Integer>(1);
		for (int i = 0; i < 100; i ++)
		{
			String name = "hi" + Integer.toString(i);
			test.put(name, i);
		}
		Scanner scan = new Scanner(System.in);
		
	}
	
	public static int atoi(String in)
	{
		int result = 0;
		for (int index = 0; index < in.length(); index++)
		{
			result += ((int)in.charAt(in.length() - index - 1) - 48) * (Math.pow(10, index));
		}
		return result;
	}

}

package server.kernel;


import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.networking.ObjectWriter;
import com.security.encryption.Encryptor;
import com.security.encryption.KeyFactory;

import server.networking.Server;
import server.user.base.table.HashTable;

public final class Boot 
{
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException 
	{
		
		Server server = new Server();
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

package client.networking;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.Key;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import com.networking.ObjectWriter;
import com.security.encryption.EncrytionEngine;
import com.security.encryption.KeyFactory;

public class Client
{
	private static final int SERVER_DEFUALT_PORT = 6969;
	private static  InetAddress SERVER_ADR;
	private Socket sock;
	private Key commonKey;
	public Client()
	{
		try
		{
			SERVER_ADR = InetAddress.getByName("mears.ca");
		} catch (UnknownHostException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		IOException connectionError;
		
		int port = SERVER_DEFUALT_PORT;
		do
		{
			connectionError = null;
			
			try
			{
				sock = new Socket(SERVER_ADR, port);
			} catch (IOException e)
			{
				connectionError = e;
				port++;
			}
			
			byte[] readKey = new byte[32]; //buffer for aeskey256
			
			try
			{
				sock.getInputStream().read(readKey);
			} catch (IOException e) {}
			
			Cipher publicKey = (Cipher) ObjectWriter.deserialize(readKey);
			
			commonKey = KeyFactory.generateNewAES256();
			byte[] keyToByte = ObjectWriter.serizalize(commonKey);
			try
			{
				publicKey.doFinal(keyToByte);
				sock.getOutputStream().write(keyToByte);
				sock.getOutputStream().flush();
				
			} catch (IllegalBlockSizeException | BadPaddingException | IOException e) {}
			
			
			
		}
		while (connectionError != null);
		
	}
	
	public void read()
	{
		
	}
	
	public void write(byte[] data)
	{
		
	}
}

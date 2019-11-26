package server.networking;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

import com.networking.NetEvent;
import com.networking.NetEventListener;
import com.networking.ObjectWriter;
import com.networking.Packet;
import com.networking.SocketEventListener;
import com.security.encryption.Encryptor;
import com.security.encryption.KeyFactory;
import com.user.User;

public class ClientConnection implements NetEventListener, SocketEventListener
{
	private static final int MAX_PACKET_SIZE = 10000000; //80MB
	private Socket sock;
	private String strkey;
	private PublicKey pubKey;
	private PrivateKey privKey;
	private SecretKey aesKey;
	private KeyPair keyPair;
	private boolean keysExchanged = false;
	private User user;
	
	public ClientConnection(Socket sock)
	{
		this.sock = sock;
	}

	@Override
	public void socketHasData()
	{
		try
		{			
		
			if (!keysExchanged)
			{
				
				byte[] keyBuf = new byte[10000];
				int size = sock.getInputStream().read(keyBuf);
				Arrays.copyOf(keyBuf, size);
				SealedObject aes = (SealedObject) ObjectWriter.deserialize(keyBuf);
				System.out.println(aes.getAlgorithm());
				aesKey = (SecretKey) aes.getObject(privKey);
				System.out.println("keyreceived");
				byte[] test = aesKey.getEncoded();
				for (byte b : test)
				{
					System.out.println(Byte.toString(b) + " ");
				}
				keysExchanged = true;
			}
			else
			{				
				byte[] buffer = new byte[MAX_PACKET_SIZE];
				int packetSize = sock.getInputStream().read(buffer);
				byte[] packetEncrypted = Arrays.copyOf(buffer, packetSize);
				
				ByteArrayInputStream in = new ByteArrayInputStream(packetEncrypted);
				Cipher decrypt = Cipher.getInstance("AES");
				decrypt.init(Cipher.DECRYPT_MODE, aesKey);
				CipherInputStream cin = new CipherInputStream(in, decrypt);
				byte[] packet = new byte[cin.available()];
				cin.read(packet);
				Packet data = (Packet) ObjectWriter.deserialize(packet);
				
				
			}
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void newConnection(NetEvent ev) //send public key
	{
		KeyPair keys = KeyFactory.generateNewRSAPair();
		privKey = keys.getPrivate();
		pubKey = keys.getPublic();
		byte[] message = ObjectWriter.serizalize(pubKey);
		try
		{
			sock.getOutputStream().write(message);
			sock.getOutputStream().flush();
			System.out.println("Message Sent");
		} catch(IOException e) {e.printStackTrace();}
		
	}

	@Override
	public void unexpectedConnnectionLost(NetEvent e)
	{
		System.out.println("Yo");
	}

	@Override
	public void connnectionLost(NetEvent e)
	{
		
		
	}
	
	
	
	
}

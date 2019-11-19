package server.networking;

import java.io.IOException;
import java.net.Socket;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Arrays;

import com.networking.NetEvent;
import com.networking.NetEventListener;
import com.networking.ObjectWriter;
import com.networking.SocketEventListener;
import com.security.encryption.Encryptor;

import server.user.User;

public class ClientConnection implements NetEventListener, SocketEventListener
{
	private static final int MAX_PACKET_SIZE = 10000000;
	private Socket sock;
	private String strkey;
	private PublicKey pubKey;
	private KeyPair keyPair;
	private boolean keysExchanged = false;
	private User user;
	
	public ClientConnection(Socket sock, PublicKey key)
	{
		this.sock = sock;
		this.pubKey = key;
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
				keyPair = (KeyPair) ObjectWriter.deserialize(keyBuf);			
			}
			else
			{				
				byte[] buffer = new byte[MAX_PACKET_SIZE];
				int packetSize = sock.getInputStream().read(buffer);
				Encryptor.decrypt("AES", keyPair.getPrivate(), buffer);
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
		byte[] message = ObjectWriter.serizalize(pubKey);
		try
		{
			sock.getOutputStream().write(message);
			sock.getOutputStream().flush();
		} catch(IOException e) {}
		
	}

	@Override
	public void unexpectedConnnectionLost(NetEvent e)
	{
				
	}

	@Override
	public void connnectionLost(NetEvent e)
	{
		
		
	}
	
	
	
	
}

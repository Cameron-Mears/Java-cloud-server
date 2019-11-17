package server.networking;

import java.io.IOException;
import java.net.Socket;
import java.security.Key;

import javax.crypto.Cipher;

import com.networking.NetEvent;
import com.networking.NetEventListener;
import com.networking.ObjectWriter;
import com.networking.SocketEventListener;
import server.user.User;

public class ClientConnection implements NetEventListener, SocketEventListener
{
	private Socket sock;
	private String strkey;
	private Key commonKey;
	private boolean keysExchanged = false;
	private User user;
	
	public ClientConnection(Socket sock)
	{
		this.sock = sock;
		
	}

	@Override
	public void socketHasData()
	{
		if (!keysExchanged)
		{
			try
			{
				byte[] keyBuf = new byte[1000];
				int size = sock.getInputStream().read(keyBuf);
				Cipher pub = EncryptionEngine.getPublicKey();
			}
			catch (Exception e) {}	
		}
		else
		{
			
		}
	}

	@Override
	public void newConnection(NetEvent ev) //send public key
	{
		Cipher pub = EncryptionEngine.getPublicKey();
		byte[] message = ObjectWriter.serizalize(pub);
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

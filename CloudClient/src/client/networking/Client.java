package client.networking;

import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class Client
{
	private static final int SERVER_DEFUALT_PORT = 6969;
	private static  InetAddress SERVER_ADR;
	private Socket sock;
	public Client()
	{
		try
		{
			SERVER_ADR = InetAddress.getByName("FRICK DYNAMIC IP");
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

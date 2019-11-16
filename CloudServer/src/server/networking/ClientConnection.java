package server.networking;

import java.net.Socket;
import java.security.Key;

import server.user.User;

public class ClientConnection implements NetEventListener, SocketEventListener
{
	private Socket sock;
	private String strkey;
	private Key key;
	
	private User user;
	
	public ClientConnection(Socket sock)
	{
		this.sock = sock;
		
	}

	@Override
	public void socketHasData()
	{
				
	}

	@Override
	public void newConnection(NetEvent e)
	{
		
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

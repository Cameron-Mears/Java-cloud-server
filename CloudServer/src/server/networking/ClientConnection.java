package server.networking;

import java.net.Socket;

import server.user.User;

public class ClientConnection implements NetEventListener, SocketEventListener
{
	private Socket sock;
	
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
				System.out.println("wow");
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

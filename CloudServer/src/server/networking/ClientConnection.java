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
	
	
	
	
}

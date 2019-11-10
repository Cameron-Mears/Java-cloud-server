package server.networking;

import java.io.IOException;
import java.net.ServerSocket;

public final class Server implements NetEventListener
{
	private int port;
	private ServerSocket serverSock;
	private ConnectionHandler netEventHandle;
	private ConnectionAcceptor conncetionAcceptor;
	
	public Server()
	{
		netEventHandle = new ConnectionHandler();
		netEventHandle.addNetEventListener(this);
		
		try
		{
			serverSock = new ServerSocket(port);
		} catch (IOException e)
		{
			
		}
			
	}


	@Override
	public void newConnection(NetEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void unexpectedConnnectionLost(NetEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void connnectionLost(NetEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}

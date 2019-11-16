package server.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;

public final class Server implements NetEventListener
{
	private int port;
	private ServerSocket serverSock;
	private ConnectionHandler netEventHandle;
	private ConnectionAcceptor conncetionAcceptor;
	private Queue<ClientConnection> waitQueue;
	private Thread acceptor;
	
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
		
		startAccepting();
		
	}
	
	private void startAccepting()
	{
		acceptor = new Thread(()->{
			try
			{
				Socket connection = serverSock.accept();
				ClientConnection cc = new ClientConnection(connection);
			
			} catch (IOException e) {}
			
		
		});
	}

	@Override
	public void newConnection(NetEvent e)
	{
				
	}


	@Override
	public void unexpectedConnnectionLost(NetEvent e)
	{
		// TODO Auto-generated method stub
		
	}


	@Override
	public void connnectionLost(NetEvent e)
	{
				
	}
	
	
	
	
}

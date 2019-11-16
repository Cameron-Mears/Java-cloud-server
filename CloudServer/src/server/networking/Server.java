package server.networking;

import java.io.IOException;
import java.io.InputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public final class Server 
{
	private int port = 6969;
	private ServerSocket serverSock;
	private ConnectionHandler connectionHandler;
	private Thread acceptor;
	private boolean hasPort;
	private boolean acceptNewConnections;
	
	public Server()
	{
		
		connectionHandler = new ConnectionHandler();
		
		try
		{
			serverSock = new ServerSocket(port);
		} catch (IOException e)
		{
			
		}
		hasPort = false;
		
		acceptNewConnections = true;
		startAccepting();
		System.out.println(acceptor.isAlive());
		
		
	}
	
	private void startAccepting()
	{
		acceptor = new Thread(()->{
			
			while (acceptNewConnections)
			{
				try
				{
					System.out.println("Hi");
					Socket connection = serverSock.accept();
					ClientConnection cc = new ClientConnection(connection);
					cc.newConnection(null);
					synchronized (cc)
					{
						synchronized (connectionHandler)
						{
							connectionHandler.addConnection(connection, cc, cc);
							cc.newConnection(new NetEvent(connection, port, connection.getInetAddress(), NetEvent.NEW_CONNECTION));
						}
					}
					
				} catch (IOException e) {}
			}		
		
		});
		acceptor.start();
	}
	
	
	public void terminateAllConnections()
	{
		this.connectionHandler.terminate();
		try
		{
			serverSock.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	
	public void pauseAccepting()
	{
		try
		{
			acceptor.wait();
		} catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}
	
	public void continueAccepting()
	{
		acceptor.notify();
	}
	
	
	
	
	
}

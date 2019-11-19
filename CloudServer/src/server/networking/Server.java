package server.networking;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.KeyGenerator;

import com.networking.NetEvent;
import com.security.encryption.KeyFactory;

public final class Server 
{
	private int port = 6969;
	private ServerSocket serverSock;
	private ConnectionHandler connectionHandler;
	private Thread acceptor;
	private Thread server;
	private boolean hasPort;
	private boolean acceptNewConnections;
	private boolean running;
	
	private KeyPair keyPair;
	private PublicKey pubKey;
	private PrivateKey privKey;
	
	public Server()
	{
		
		keyPair = KeyFactory.generateNewRSAPair(); //pub and private key for new connections
		pubKey = keyPair.getPublic();
		privKey = keyPair.getPrivate();
		
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
		loop();
	}
	
	private void startAccepting()
	{
		acceptor = new Thread(()->{
			
			while (acceptNewConnections)
			{
				try
				{
					Socket connection = serverSock.accept();
					ClientConnection cc = new ClientConnection(connection, pubKey);
					synchronized (cc)
					{
						synchronized (connectionHandler)
						{						
							connectionHandler.addConnection(connection, cc, cc);
							
							new Thread(()->{
								cc.newConnection(new NetEvent(connection, port, connection.getInetAddress(), NetEvent.NEW_CONNECTION));
							}).start();
						}
						
						synchronized (server)
						{
							server.notify();
						}
						
						
						
					}
					
				} catch (IOException e) {}
			}		
		
		});
		acceptor.start();
	}
	
	
	public void loop()
	{
		server = new Thread(()->{
			
			Thread thisThread = Thread.currentThread();
			
			while (running)
			{
				int connections = 0;
				synchronized (connectionHandler)
				{
					connections = connectionHandler.getActiveConnections();
				}
				
				
				if (connections == 0)
				{
					try
					{
						thisThread.wait();
					} catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
			}
			
		});
		server.start();
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

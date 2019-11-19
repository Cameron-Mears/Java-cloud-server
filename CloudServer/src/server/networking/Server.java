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
	private boolean hasPort;
	private boolean acceptNewConnections;
	
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

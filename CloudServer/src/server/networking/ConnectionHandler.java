package server.networking;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;



public class ConnectionHandler
{
	
	private LinkedList<SocketPair> listeners;
	
	
	private class SocketPair
	{
		Socket sock;
		SocketEventListener sockListener;
		NetEventListener netListener;
	}
	
	public void listen()
	{
		for (SocketPair socketPair : listeners)
		{
			try
			{
				if (socketPair.sock.getInputStream().available() > 0)
				{
					socketPair.sockListener.socketHasData();
				}
			} catch (IOException e)
			{
				if (!socketPair.sock.isConnected())
				{
					NetEvent ev = new NetEvent(socketPair.sock, socketPair.sock.getLocalPort(), socketPair.sock.getInetAddress(), NetEvent.UNEXCEPTED_CONNECTION_LOSS);
					socketPair.netListener.unexpectedConnnectionLost(ev);
					listeners.remove(socketPair);
				}
				e.printStackTrace();
			}
		}
		
	}
	
	
	public ConnectionHandler()
	{
		listeners = new LinkedList<SocketPair>();
	}
	
	
	
	public boolean addConnection(Socket sock, SocketEventListener sockListener, NetEventListener netlistner)
	{
		SocketPair pair = new SocketPair();
		pair.sock = sock;
		pair.sockListener = sockListener;
		pair.netListener = netlistner;
		
		return listeners.add(pair);
	}
	
	public void terminate()
	{
		
	}
}

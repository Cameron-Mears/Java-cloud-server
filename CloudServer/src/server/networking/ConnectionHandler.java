package server.networking;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

import com.networking.NetEvent;
import com.networking.NetEventListener;
import com.networking.SocketEventListener;



public class ConnectionHandler
{
	
	private LinkedList<SocketPair> listeners;
	private Thread thisThread;
	
	private class SocketPair
	{
		Socket sock;
		SocketEventListener sockListener;
		NetEventListener netListener;
		long connectionID;
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
		thisThread = Thread.currentThread();
	}
	
	public int getActiveConnections()
	{
		return listeners.size();
	}
	
	
	public boolean addConnection(Socket sock, SocketEventListener sockListener, NetEventListener netlistner)
	{
		SocketPair pair = new SocketPair();
		pair.sock = sock;
		pair.sockListener = sockListener;
		pair.netListener = netlistner;
		pair.connectionID = (long) (Math.random() * Long.MAX_VALUE);
		
		return listeners.add(pair);
	}
	
	public Thread getThread()
	{
		return thisThread;
	}
	
	public void terminate()
	{
		
	}
}

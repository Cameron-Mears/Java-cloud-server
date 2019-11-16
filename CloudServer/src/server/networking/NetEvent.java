package server.networking;

import java.net.InetAddress;
import java.net.Socket;

public class NetEvent
{
	public static final int UNEXCEPTED_CONNECTION_LOSS = -1;
	public static final int CONNECTION_LOSS = 1;
	public static final int NEW_CONNECTION = 0;
	
	
	private InetAddress connectionAdr;
	private int connectionPort;
	private Socket socket;
	private long evTimeMillis;
	private int type;
	
	
	
	public NetEvent(Socket sock, int port, InetAddress adr, int type)
	{
		this.socket = sock;
		this.type = type;
		this.connectionAdr = adr;
		this.connectionPort = port;
		this.evTimeMillis = System.currentTimeMillis();
	}
	
	public InetAddress getConnectionAdr()
	{
		return connectionAdr;
	}
	public int getConnectionPort()
	{
		return connectionPort;
	}
	public Socket getSocket()
	{
		return socket;
	}
	public long getEvTimeMillis()
	{
		return evTimeMillis;
	}
	public int getType()
	{
		return type;
	}
	
	
	
}

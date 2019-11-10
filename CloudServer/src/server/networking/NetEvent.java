package server.networking;

import java.net.Inet6Address;
import java.net.Socket;

public class NetEvent
{
	public static final int UNEXCEPTED_CONNECTION_LOSS = -1;
	public static final int CONNECTION_LOSS = 1;
	public static final int NEW_CONNECTION = 0;
	
	
	private Inet6Address connectionAdr;
	private int connectionPort;
	private Socket socket;
	private long evTimeMillis;
	private int type;
	
	
	
	public Inet6Address getConnectionAdr()
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

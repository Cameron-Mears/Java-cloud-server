package server.networking;

import java.util.LinkedList;

public class ConnectionHandler
{
	private LinkedList<NetEventListener> listeners;
	
	public ConnectionHandler()
	{
		listeners = new LinkedList<NetEventListener>();
	}
	
	public void event(NetEvent e)
	{
		switch (e.getType()) {
		case NetEvent.UNEXCEPTED_CONNECTION_LOSS:
			for (NetEventListener netEventListener : listeners)
			{
				netEventListener.unexpectedConnnectionLost(e);
			}
			break;
		case NetEvent.CONNECTION_LOSS:
			for (NetEventListener netEventListener : listeners)
			{
				netEventListener.connnectionLost(e);
			}
			break;
		case NetEvent.NEW_CONNECTION:
			for (NetEventListener netEventListener : listeners)
			{
				netEventListener.newConnection(e);
			}
			break;

		default:
			break;
		}
	}
	
	public boolean addNetEventListener(NetEventListener listener)
	{
		return listeners.add(listener);
	}
	
	public boolean removeNetEventListener(NetEventListener listener)
	{
		return listeners.remove(listener);
	}
}

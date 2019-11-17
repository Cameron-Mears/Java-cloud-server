package com.networking;

public interface NetEventListener
{
	public abstract void newConnection(NetEvent e);
	public abstract void unexpectedConnnectionLost(NetEvent e);
	public abstract void connnectionLost(NetEvent e);
}

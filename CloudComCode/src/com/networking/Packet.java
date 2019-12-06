package com.networking;

import java.io.ByteArrayInputStream;

public abstract class Packet
{
	protected PacketType type;	
	
	protected byte[] data;
	
	public abstract ByteArrayInputStream getDataStream();
	public abstract int dataSize();
	
	public Packet(PacketType type, byte[] data)
	{
		this.data = data;
	}
	
	public PacketType getType()
	{
		return type;
	}
	
	
	
}

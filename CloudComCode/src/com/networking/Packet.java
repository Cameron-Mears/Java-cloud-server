package com.networking;

import java.io.ByteArrayInputStream;

public abstract class Packet
{
	protected PacketType type;	
	
	protected byte[] data;
	
	public abstract ByteArrayInputStream getDataStream();
	public abstract int dataSize();
	
	public PacketType getType()
	{
		return type;
	}
	
}

package com.networking;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjectWriter
{
	public static byte[] serizalize(Object obj)
	{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream objStream = null;
		byte[] objByteArr = null;
		try
		{
			objStream = new ObjectOutputStream(out);
			objStream.writeObject(obj);
			objStream.flush();
			objByteArr = out.toByteArray();
			out.close();
		}
		catch (IOException e) {}
		
		return objByteArr;
		
	}
	
	public static Object deserialize(byte[] data)
	{
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream objStream = null;
		Object toReturn = null;
		try
		{
			objStream = new ObjectInputStream(in);
			toReturn = objStream.readObject();
			objStream.close();
		}
		catch (IOException | ClassNotFoundException e) {e.printStackTrace();}
		
		return toReturn;
		
	}
}

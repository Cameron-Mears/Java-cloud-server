package server.networking;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ByteArrayFormatter
{
	public static byte[] serizalize(Serializable obj)
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
		
	}
}

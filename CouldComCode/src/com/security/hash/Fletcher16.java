package com.security.hash;

public final class Fletcher16 
{
	public static short checkSum(byte[] data)
	{
		short s1 = 0;
		short s2 = 0;
		
		for (int index = 0; index < data.length; index++) 
		{
			s1 = (short) ((s1 + data[index]) % 255);
			s2 = (short) ((s2 + s1) % 255);
		}
		
		return (short) ((s2 << 8) | s1);
	}
}

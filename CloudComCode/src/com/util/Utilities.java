package com.util;

public final class Utilities
{
	public static boolean arrayCompare(Object[] a1, Object[] a2)
	{
		if (a1 == null && a2 == null) return true;
		if (a1 == null || a2 == null) return false;
		
		if (a1.length != a2.length) return false;
		
		for (int i = 0; i < a2.length; i++)
		{
			if (!a1[i].equals(a2[i])) return false;
		}
		
		return true;
	}
	
	public static boolean arrayCompare(byte[] a1, byte[] a2)
	{
		if (a1 == null && a2 == null) return true;
		if (a1 == null || a2 == null) return false;
		
		if (a1.length != a2.length) return false;
		
		for (int i = 0; i < a2.length; i++)
		{
			if (a1[i] != a2[i]) return false; 
		}
		return true;
	}
}

package client.main;

import client.networking.Client;
import client.networking.ServerUnavaibleException;

public class Main
{
	public static void main(String[] args) 
	{
		try
		{
			new Client();
		} catch (ServerUnavaibleException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

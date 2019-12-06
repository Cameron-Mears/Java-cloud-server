package client.files;

import java.util.Stack;

import client.viewer.graphics.Renderer;
import client.viewer.graphics.elements.HyperLink;

public class FileMapper
{
	private static String root = "F:\\servertestfolder\\";
	private static Stack<String> path;
	
	
	static
	{
		path = new Stack<String>();
	}
	
	public static Stack<String> getPath()
	{
		return path;
	}
	
	public static void exitDirect()
	{
		if (!path.isEmpty()) path.pop();
	}
	
	public static boolean setDirect(String direct)
	{
		String[] folders = direct.split("/");
		return false;
	}
	
	public static String getStackString()
	{
		String ret = new String();
		Stack<String> transfer = new Stack<String>();
		while (!path.isEmpty()) transfer.push(path.pop());
		
		while (!transfer.isEmpty())
		{
			String temp = transfer.pop();
			ret += temp + "/";
			path.push(temp);
		}
		return ret;
	}
	
	
	
	public static boolean enterDirect(String direct)
	{
		if (direct == null) return false;
		Renderer renderer = Renderer.getInstance();
		Stack<HyperLink> links = new Stack<HyperLink>();
		String[] path = direct.split("/");
		while (renderer.updateFilePath(null, true)); //dispose of old path
		
		for (String string : path)
		{
			
		}
		return false;
	}
}

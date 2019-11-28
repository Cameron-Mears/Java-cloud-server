package client.files;

import java.io.File;
import java.util.Stack;

public class FileMapper
{
	private static String root = "F:\\servertestfolder\\";
	private static Stack<String> path;
	
	public static Stack<String> getPath()
	{
		return path;
	}
	
	public static void exitDirect()
	{
		if (!path.isEmpty()) path.pop();
	}
	
	public static boolean enterDirect(String direct)
	{
		if (direct == null) return false;
		String[] folders = direct.split("/");
		if (folders.length > 1)
		{
			for (String folder : folders)
			{
				return enterDirect(folder);
			}
		}
		Stack<String> fromBase = new Stack<String>();
		
		while(!path.isEmpty()) fromBase.push(path.pop());
		
		String finalPath = new String();
		while (!fromBase.isEmpty())
		{
			String temp = fromBase.pop();
			finalPath += temp;
			path.push(temp);
			File test = new File(root + finalPath + direct);
			
			if (test.isDirectory())
			{
				path.push(direct);
				return true;
			}
		}
		
		return false;
	}
}

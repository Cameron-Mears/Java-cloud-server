package server.kernel;


import java.util.Scanner;

import com.security.hash.Fletcher16;
import com.security.hash.SHA256;

import server.user.base.UserBase;
import server.user.base.table.HashTable;

public final class Boot 
{

	public static void main(String[] args) 
	{
		HashTable<String, Integer> test = new HashTable<String, Integer>(1);
		for (int i = 0; i < 100; i ++)
		{
			String name = "hi" + Integer.toString(i);
			test.put(name, i);
		}
		Scanner scan = new Scanner(System.in);
		while (true)
		{
			String in = scan.next();
			if (in.equals("exit")) System.exit(0);
			if (in.equals("expand"))
			{
				long now = System.currentTimeMillis();
				test.setSize(scan.nextInt());
				System.out.println(System.currentTimeMillis()-now);
			}
			else
			{
				long now = System.currentTimeMillis();
				Integer i = test.get(in);
				System.out.println(System.currentTimeMillis()-now);
				if (i == null) System.out.println("No Value at Index");
				else System.out.println(i);
				
				System.out.println(new String(SHA256.doHash(in.getBytes())));
			}
			System.out.println(atoi(in));
		}
	}
	
	public static int atoi(String in)
	{
		int result = 0;
		for (int index = 0; index < in.length(); index++)
		{
			result += ((int)in.charAt(in.length() - index - 1) - 48) * (Math.pow(10, index));
		}
		return result;
	}

}

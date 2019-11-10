package server.kernel;


import java.util.Scanner;

import com.security.hash.Fletcher16;

import server.user.base.UserBase;
import server.user.base.table.HashTable;

public final class Boot 
{

	public static void main(String[] args) 
	{
		HashTable<String, Integer> test = new HashTable<String, Integer>(1);
		for (int i = 0; i < 1000000; i ++)
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
			}
		}
	}

}

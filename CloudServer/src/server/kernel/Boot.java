package server.kernel;

import java.util.Scanner;

import com.security.hash.Fletcher16;

import server.user.base.UserBase;

public final class Boot 
{

	public static void main(String[] args) 
	{
		String t = "Testdsasadsadsadesdfgdsfgsdfsdf";
		short check = Fletcher16.checkSum(t.getBytes());
		System.out.println(check);
		Scanner scan = new Scanner(System.in);
		new UserBase();
		scan.next();
		scan.close();
	}

}

package client.main;

import java.awt.Dimension;
import java.util.Scanner;

import client.viewer.display.Viewer;
import client.viewer.graphics.Renderer;

public class Main
{
	public static void main(String[] args) throws InterruptedException 
	{
		Viewer viewer = new Viewer(new Dimension(1200,500));
		Renderer renderer = new Renderer(viewer.getWinSize());
		viewer.setRenderer(renderer);
		Scanner scanner = new Scanner(System.in);
		while (true)
		{
			Thread.sleep(100);
			viewer.render();
			if (scanner.hasNext())
			{
				String in = scanner.nextLine();
				String[] command = in.split(" ");
				
				
				if (command[0].equals("push"))
				{
					if (command.length >= 2)renderer.updateFilePath(command[1], false);
				}
				else if (command[0].equals("pop"))
				{
					renderer.updateFilePath(null, true);
				}
			}
		}
	}
}

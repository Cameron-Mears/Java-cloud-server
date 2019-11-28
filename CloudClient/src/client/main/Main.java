package client.main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.Scanner;

import client.viewer.display.Viewer;
import client.viewer.graphics.Renderer;
import client.viewer.graphics.elements.HyperLink;
import client.viewer.input.InputHandler;

public class Main
{
	public static void main(String[] args) throws InterruptedException 
	{
		Viewer viewer = new Viewer(new Dimension(1200,500));
		Renderer renderer = new Renderer(viewer.getWinSize());
		viewer.setRenderer(renderer);
		InputHandler inputHandler = new InputHandler();
		viewer.getWindow().addMouseListener(inputHandler);
		viewer.getWindow().addMouseWheelListener(inputHandler);
		viewer.getWindow().addMouseMotionListener(inputHandler);
		
		Scanner scanner = new Scanner(System.in);
		while (true)
		{
			Thread.sleep(100);
			viewer.render();
			if (scanner.hasNext())
			{
				String in = scanner.nextLine();
				String[] command = in.split("/");
				System.out.println(command.length);
				
				
				if (command[0].equals("push"))
				{
					if (command.length >= 2)
					{
						Font font = new Font("Arial", Font.PLAIN, 18);
						HyperLink link = new HyperLink(0,0,command[1], null, font, new Rectangle(100,200));
						renderer.updateFilePath(link, false);
						inputHandler.addInputEventListener(link);
					}
				}
				else if (command[0].equals("pop"))
				{
					renderer.updateFilePath(null, true);
				}
			}
		}
	}
}

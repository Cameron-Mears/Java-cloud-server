package client.viewer.display;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFrame;

public final class Viewer
{
	private JFrame frame;
	private int width, height;
	
	public Viewer(int width, int height)
	{
		frame = new JFrame();
		
		this.width = width;
		this.height = height;
		
		frame.setSize(width, height);
		frame.setResizable(true);
		frame.setVisible(true);
		
		
	}
	
}

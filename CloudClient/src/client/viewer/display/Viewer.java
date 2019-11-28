package client.viewer.display;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import client.viewer.graphics.Renderer;

public final class Viewer implements ComponentListener
{
	private Dimension dimension;
	private Renderer renderer;
	private JFrame frame;
	private static Viewer instance;
	
	public Viewer(Dimension d)
	{
		
		this.dimension = d;
		frame = new JFrame();
		frame.setSize(dimension);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		frame.addComponentListener(this);
		
		instance = this;
	}
	
	public static Viewer getInstance()
	{
		return instance;
	}
	
	public Dimension getWinSize()
	{
		return dimension;
	}
	
	public JFrame getWindow()
	{
		return frame;
	}
	
	public void setRenderer(Renderer r)
	{
		renderer = r;
		renderer.setWinSize(dimension);
	}
	
	@Override
	public void componentHidden(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentMoved(ComponentEvent e)
	{
				
	}
	@Override
	public void componentResized(ComponentEvent e)
	{
		int width, height;
		width = frame.getWidth();
		height = frame.getHeight();
		Dimension newSize = new Dimension(width,height);
		if (renderer != null) renderer.setWinSize(newSize);
		this.dimension = newSize;
		render();
	}
	@Override
	public void componentShown(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	public void render()
	{
		BufferStrategy bs = frame.getBufferStrategy();
		if (bs == null)
		{
			frame.createBufferStrategy(3);
			bs = frame.getBufferStrategy();
		}
		
		Graphics2D graphics = (Graphics2D) bs.getDrawGraphics();
		if (renderer != null) renderer.render(graphics);
		graphics.dispose();
		bs.show();			
		
	}
}

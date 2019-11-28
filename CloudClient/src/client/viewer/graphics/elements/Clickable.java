package client.viewer.graphics.elements;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import client.viewer.display.Viewer;
import client.viewer.input.InputEventListener;

public abstract class Clickable implements InputEventListener
{
	protected Rectangle collisionBounds;
	protected boolean hovering;
	
	protected abstract void onClick();
	protected abstract void onHoverEnter();
	protected abstract void onHoverExit();
	
	public abstract void render(int x, int y, Graphics2D graphics);
	
	public Clickable(Rectangle bounds, int x, int y)
	{
		collisionBounds = new Rectangle(x, y, bounds.width, bounds.height);
	}
	
	@Override
	public void onMouseReleased(MouseEvent e)
	{
		if (collisionBounds.contains(e.getPoint())) onClick();
		System.out.println("Fdsfdsfdsf");
	}
	
	
	@Override
	public void onMouseMoved(MouseEvent e)
	{
		if (collisionBounds.contains(e.getPoint()))
		{
			if (!hovering)
			{
				hovering = true;
				Viewer.getInstance().render();
			}
			hovering = true;
		}
		else
		{
			if (hovering)
			{
				onHoverExit();
				hovering = false;
				Viewer.getInstance().render();
			}
		}
	}
	
	public void setBounds(Rectangle bounds)
	{
		this.collisionBounds = bounds;
	}
	
}

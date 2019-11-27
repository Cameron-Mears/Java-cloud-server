package client.viewer.graphics.elements;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import client.viewer.input.InputEventListener;

public abstract class Clickable implements InputEventListener
{
	protected Rectangle collisionBounds;
	
	protected abstract void onClick();
	
	public Clickable(Rectangle bounds)
	{
		collisionBounds = bounds;
	}
	
	
	@Override
	public void onMouseMoved(MouseEvent e)
	{
		if (collisionBounds.contains(e.getPoint()))
		{
			
		}
	}
	
}

package client.viewer.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.event.MouseInputListener;

public class InputHandler implements MouseMotionListener, MouseInputListener, MouseWheelListener, KeyListener
{

	public LinkedList<InputEventListener> listeners;
	
	public InputHandler()
	{
		listeners = new LinkedList<InputEventListener>();
	}
	
	
	public boolean addInputEventListener(InputEventListener listener)
	{
		return listeners.add(listener);
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e)
	{
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		for (InputEventListener inputEventListener : listeners)
		{
			inputEventListener.onMousePressed(e);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		for (InputEventListener inputEventListener : listeners)
		{
			inputEventListener.onMouseReleased(e);
		}		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		for (InputEventListener inputEventListener : listeners)
		{
			inputEventListener.onMouseMoved(e);
		}		
	}

	@Override
	public void keyPressed(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

}

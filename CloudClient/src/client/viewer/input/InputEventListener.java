package client.viewer.input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface InputEventListener
{
	public void onMouseReleased(MouseEvent e);
	public void onMousePressed(MouseEvent e);
	public void onMouseWheelUp(MouseEvent e);
	public void onMouseWheelDown(MouseEvent e);
	public void onMouseMoved(MouseEvent e);
	public void onKeyPressed(KeyEvent e);
	public void onKeyReleased(KeyEvent e);
	
}

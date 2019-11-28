package client.viewer.graphics.elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

import org.w3c.dom.css.Rect;

import client.viewer.graphics.Renderer;

public class HyperLink extends Clickable
{
	private Color color;
	private Font font;
	private String text;
	private String url;
	private boolean resized;
	private boolean boundsSet = false;

	public HyperLink(int x, int y, String text, String url, Font font, Rectangle rect)
	{
		super(rect, x, y);
		this.font = font;
		this.collisionBounds = rect;
		this.url = url;
		this.text = text;
	}
	
	public void setFontSize(int size)
	{
		font = new Font(font.getFamily(), font.getStyle(), size);
		resized = true;
	}
	
	public Font getFont()
	{
		return font;
	}
	
	public String getText()
	{
		return text;
	}
	
	@Override
	public void render(int x, int y, Graphics2D graphics)
	{
		if (!boundsSet)
		{
			Rectangle rect = new Rectangle(x, y-graphics.getFontMetrics().getHeight(), graphics.getFontMetrics().stringWidth(text), graphics.getFontMetrics().getHeight());
			this.setBounds(rect);
		}
		graphics.setFont(font);
		if (resized)
		{
			int height, width;
			width = graphics.getFontMetrics().stringWidth(text);
			height = graphics.getFontMetrics().getHeight();
			this.setBounds(new Rectangle(width, height));
		}
		
		if(hovering)
		{
		
			graphics.setColor(Color.BLUE);
			graphics.drawString(text, x, y);
		}
		else
		{
			graphics.setColor(Color.BLACK);
			graphics.drawString(text, x, y);
		}
			
	}
	
	@Override
	protected void onClick()
	{
		System.exit(0);

	}

	@Override
	protected void onHoverEnter()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onHoverExit()
	{
		hovering = false;
	}

	@Override
	public void onMousePressed(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onMouseWheelUp(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onMouseWheelDown(MouseEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onKeyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onKeyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub

	}

}

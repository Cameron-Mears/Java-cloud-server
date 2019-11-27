package client.viewer.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Stack;

public class Renderer
{
	private Stack<String> filePath;
	private Font font;
	private Dimension window;
	
	public Renderer(Dimension winSize)
	{
		window = winSize;
		filePath = new Stack<String>();
	}
	
	public void drawFilePath(int x, int y, Graphics2D graphics)
	{
		Stack<String> drawStack = new Stack<String>();
		
		 //move elements from filePath stack to draw stack so that drawing in right order (Home->Folder) becomes (Folder->Home)
		// now Home is top element and will drawn first
		while (!filePath.isEmpty()) drawStack.push(filePath.pop());
		
		graphics.setColor(Color.black);
		graphics.setFont(font);
		while (!drawStack.isEmpty())
		{
			String item = drawStack.pop();
			graphics.drawString(item, x, y);
			x += graphics.getFontMetrics().stringWidth(item);
			if (!drawStack.isEmpty())
			{
				graphics.drawString("/", x, y);
				x += graphics.getFontMetrics().stringWidth("/");
			}
			
			filePath.push(item);
		}
		
		
			
	}
	
	 //if leaving a direct String can be left null, and it will just pop the top stack item,
	//if adding a new item pop should be false and the path should be the folder name
	
	public void updateFilePath(String item, boolean pop)
	{
		if (pop && !filePath.isEmpty())
		{
			filePath.pop();
			return;
		}
		if (item != null) filePath.push(item);
	}
	
	public boolean setFont(String name, int style, int size) throws InvalidFontException
	{
		Font fontChoice = new Font(name, style, size);
		if (!fontChoice.getName().equals(name)) throw new InvalidFontException();
		else
		{
			this.font = fontChoice;
			return true;
		}
				
	}
	
	public void setWinSize(Dimension d)
	{
		this.window = d;
	}
	
	public void drawBackground(Graphics2D graphics)
	{
		int height = (int)(window.height * GraphicsMargins.TOP_BAR_HEIGHT_PERCENT);
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, window.width, window.height);
		graphics.setColor(Color.BLUE);
		graphics.fillRect(0, 0, window.width, height);
	}
	
	public void render(Graphics2D graphics)
	{
		try
		{
			setFont("Arial", Font.PLAIN, 18);
		} catch (InvalidFontException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		drawBackground(graphics);
		drawFilePath(10, (int)(window.height * GraphicsMargins.TOP_BAR_HEIGHT_PERCENT) + graphics.getFontMetrics().getHeight(), graphics);
	}
}

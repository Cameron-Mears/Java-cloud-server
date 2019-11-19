package client.viewer.graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.util.Stack;

import javax.swing.text.StyledEditorKit.FontFamilyAction;

public class Renderer
{
	private Stack<String> filePath;
	private Font font;
	
	public Renderer()
	{
		filePath = new Stack<String>();
	}
	
	public void drawFilePath(int x, int y, Graphics g)
	{
		Stack<String> drawStack = new Stack<String>();
		
		 //move elements from filePath stack to draw stack so that drawing in right order (Home->Folder) becomes (Folder->Home)
		// now Home is top element and will drawn first
		while (!filePath.isEmpty()) drawStack.push(filePath.pop());
		
		while (!drawStack.isEmpty())
		{
			String item = drawStack.pop();
			
			//do render
			
			filePath.push(item);
		}
		
		
			
	}
	
	 //if leaving a direct String can be left null, and it will just pop the top stack item,
	//if adding a new item pop should be false and the path should be the folder name
	
	public void updateFilePath(String item, boolean pop)
	{
		if (pop) filePath.pop();
		else
		{
			filePath.push(item);
		}
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
}

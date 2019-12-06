package client.viewer.graphics;

import java.awt.Font;
import java.awt.Rectangle;

import client.files.FileMapper;
import client.viewer.graphics.elements.HyperLink;

public class FilePathLink extends HyperLink
{

	public FilePathLink(int x, int y, String text, String url, Font font, Rectangle rect)
	{
		super(x, y, text, url, font, rect);
		url = FileMapper.getStackString();
		this.setUrl(url);
	}
	
	@Override
	public void onClick()
	{
		FileMapper.setDirect(this.getUrl());
	}

}

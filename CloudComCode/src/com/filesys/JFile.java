package com.filesys;

import java.io.Serializable;

public class JFile implements Serializable
{
	private static final long serialVersionUID = -3636047387232146347L;
	
	private String filePath;
	private int addedPadding;
	private byte[] iv;
	
	public JFile(String path, int padding, byte[] iv)
	{
		this.addedPadding = padding;
		this.filePath = path;
		this.iv = iv;
	}

	public String getFilePath()
	{
		return filePath;
	}

	public int getAddedPadding()
	{
		return addedPadding;
	}

	public byte[] getIv()
	{
		return iv;
	}
}

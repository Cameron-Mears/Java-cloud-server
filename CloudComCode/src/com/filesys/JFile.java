package com.filesys;

import java.io.Serializable;

public class JFile implements Serializable
{
	private static final long serialVersionUID = -3636047387232146347L;
	
	public boolean compressed;
	public boolean encryted;
	public int byteSize;
	private byte[] file;
}

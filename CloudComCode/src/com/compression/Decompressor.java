package com.compression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import com.filesys.JFile;

public class Decompressor 
{
	public JFile decompress(JFile compressed)
	{
		FileInputStream fos = null;
		GZIPInputStream decompress = null;
		try {
			fos = new FileInputStream(new File(""));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			decompress = new GZIPInputStream(fos);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		byte[] out;
		return null;
	}
}

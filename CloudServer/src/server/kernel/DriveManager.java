package server.kernel;

import java.io.IOException;

public final class DriveManager 
{
	private static final String rootPath = "F:\\CloudProjectRoot";
	public static VHD createVHD(long byteSize) throws IOException
	{
		ProcessBuilder builder = new ProcessBuilder("sdd");
		builder.start();
		return null;
	}
}

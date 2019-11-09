package server.kernel;

import java.io.IOException;

public final class DriveManager 
{
	public static VHD createVHD(long byteSize) throws IOException
	{
		ProcessBuilder builder = new ProcessBuilder("sdd");
		builder.start();
		return null;
	}
}

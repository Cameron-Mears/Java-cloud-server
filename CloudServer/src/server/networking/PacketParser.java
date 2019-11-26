package server.networking;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.networking.ObjectWriter;
import com.networking.Packet;
import com.security.hash.Authentication;
import com.security.hash.Salt;
import com.security.logon.LongOnRequest;
import com.user.User;
import com.util.Utilities;

import server.user.base.UserBase;

public final class PacketParser
{
	private static UserBase userBase;
	
	public static void parse(Packet packet, ClientConnection connection) throws IOException
	{
		switch (packet.getType())
		{
			case LOGON:
				byte[] buf = new byte[100000];
				ByteArrayInputStream bis = packet.getDataStream();
				int size = bis.read(buf);
				LongOnRequest log = (LongOnRequest) ObjectWriter.deserialize(Arrays.copyOf(buf, size));
				User user = userBase.fetch(log.username);
				Salt userSalt = user.getPassSalt();
				byte[] hash = Authentication.digest(log.password, userSalt, 10);
				if (Utilities.arrayCompare(hash, user.getHashedPass())) connection.setUser(user);
				else connection.setUser(null);
				break;
			
				
		}
	}
}

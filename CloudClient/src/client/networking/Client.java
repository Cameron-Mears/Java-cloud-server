package client.networking;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.security.PublicKey;
import java.util.Arrays;

import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;

import com.networking.ObjectWriter;
import com.security.encryption.Encryptor;
import com.security.encryption.KeyFactory;

public class Client
{
	private static final int SERVER_DEFUALT_PORT = 6969;
	private static  InetAddress SERVER_ADR;
	private Socket sock;
	private PublicKey publicKey = null;
	private SecretKey aesKey;
	private long timeout = 5000;
	private boolean serverTimeouted = false;
	private boolean keyExchanged = false;
	private CipherInputStream sockIn;
	private CipherOutputStream sockOut;
	private Thread thisThread;
	
	
	public Client() throws ServerUnavaibleException 
	{
		
		thisThread = Thread.currentThread();
		try
		{
			SERVER_ADR = InetAddress.getByName("mears.ca");
		
				
			sock = new Socket();
			SocketAddress sockadr = new InetSocketAddress(SERVER_ADR, SERVER_DEFUALT_PORT);
			
			try
			{
				sock.connect(sockadr, 5000);
			}
			catch (Exception e)
			{
				throw new ServerUnavaibleException();
			}
			
			
			new Thread(()->{
				
				long timeout = System.currentTimeMillis() + this.timeout;
				
				while (timeout > System.currentTimeMillis())
				{
					synchronized (sock)
					{
						try
						{
							if (sock.getInputStream().available() > 0)
							{
				
								byte[] buf = new byte[10000];
								int read = 0;
								read = sock.getInputStream().read(buf);
								byte[] key = Arrays.copyOf(buf, read);
								this.publicKey = (PublicKey) ObjectWriter.deserialize(key);
								timeout -= 5000; //will auto timeout the loop
								
							}
						} catch (IOException e) {e.printStackTrace();}
					}
					
				}
				
				if (publicKey == null)
				{					
					serverTimeouted = true;
				}			 
				
				
				
			}).start(); //waits to accept public key from server
			
			
			
			byte[] keyPairByte = ObjectWriter.serizalize(aesKey);
			byte[] encrypted = null;
			
			long timeoutTime = System.currentTimeMillis() + 5000;
			
			while (timeoutTime > System.currentTimeMillis() && publicKey == null)
			{
		
			}
			
			if (serverTimeouted)
			{
				throw new ServerUnavaibleException();
			}
			
			aesKey = KeyFactory.generateAES256();
			
			SealedObject obj = Encryptor.encryptRSA(publicKey, aesKey); //encrypt packet
			sock.getOutputStream().write(ObjectWriter.serizalize(obj));
			sock.getOutputStream().flush();
			byte[] b = aesKey.getEncoded();
			for (byte c : b) 
			{
				System.out.println(c);
			}
			System.out.print("KEYSENT");
			
			
			
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	public void update()
	{
		
	}
}

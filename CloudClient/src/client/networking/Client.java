package client.networking;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Arrays;

import javax.crypto.SealedObject;

import com.networking.ObjectWriter;
import com.security.encryption.Encryptor;
import com.security.encryption.KeyFactory;

public class Client
{
	private static final int SERVER_DEFUALT_PORT = 6969;
	private static  InetAddress SERVER_ADR;
	private Socket sock;
	private PublicKey publicKey = null;
	private KeyPair keyPair;
	private long timeout = 5000;
	private boolean serverTimeouted = false;
	private boolean keyExchanged = false;
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
				sock.connect(sockadr, (int)5000);
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
			
			
			//do everything else
			
			//viewer.init() etc..
			
			
			keyPair = KeyFactory.generateNewRSAPair();
			
			byte[] keyPairByte = ObjectWriter.serizalize(keyPair);
			byte[] encrypted = null;
			
			long timeoutTime = System.currentTimeMillis() + 5000;
			
			while (timeoutTime > System.currentTimeMillis() && publicKey == null)
			{
		
			}
			
			if (serverTimeouted)
			{
				throw new ServerUnavaibleException();
			}
			
			SealedObject obj = Encryptor.encrypt("RSA", publicKey, keyPair); //encrypt packet
			
			OutputStream sockStream = sock.getOutputStream();
			encrypted = ObjectWriter.serizalize(obj);
			sockStream.write(encrypted);
			sockStream.flush();
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public KeyPair getEncryptionPair()
	{
		return keyPair;
	}
	
	public void read()
	{
		
	}
	
	public void write(byte[] data)
	{
		
	}
}

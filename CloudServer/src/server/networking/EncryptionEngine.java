package server.networking;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.Iterator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.filesys.JFile;

public final class EncryptionEngine 
{
	private static final int MAX_BUFFER_SIZE = (int) Math.pow(2, 25); //167MB (multiple of 128 (block size))
	private static final int ENCRYPTION_BLOCK = 16; //16 bytes per block
	private static final double BLOCK_SIZE = 16.0;
	
	public static byte[] encryptFile(SecretKey aesKey, File file) throws FileNotFoundException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidParameterSpecException
	{		
		if (file.isDirectory())
		{
			File[] subFiles = file.listFiles();
			for (File subFile : subFiles)
			{
				encryptFile(aesKey, subFile);
			}
		}
		
		FileInputStream stream = new FileInputStream(file);
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		byte[] iv = cipher.getParameters().getParameterSpec(IvParameterSpec.class).getIV();
		byte[] fileBuffer = new byte[MAX_BUFFER_SIZE];
		
	
		FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile() + ".svref");
		
		
		boolean loopFlag = true;
		while (loopFlag)
		{
			int read = stream.read(fileBuffer, ENCRYPTION_BLOCK, MAX_BUFFER_SIZE - ENCRYPTION_BLOCK); //read core of buffer, start notes size end of buffer is added Padding
			
			int numChunks = (int)(Math.ceil(read/16.0D));
			System.out.println(numChunks);
			byte[] numChunksBytes = ByteBuffer.allocate(Integer.BYTES).putInt(numChunks).array();	
			
			memcpy(numChunksBytes, fileBuffer, Integer.BYTES); //copy number of chunks to start of buffer
			
			int paddingToAdd = ENCRYPTION_BLOCK - (read % ENCRYPTION_BLOCK);
			System.out.println(paddingToAdd);
			
			fileBuffer[ENCRYPTION_BLOCK + (ENCRYPTION_BLOCK * numChunks)-1] = (byte) paddingToAdd;
			
			ByteArrayInputStream bis = new ByteArrayInputStream(fileBuffer, 0, ENCRYPTION_BLOCK*2 + (ENCRYPTION_BLOCK * numChunks));
			CipherInputStream cis = new CipherInputStream(bis, cipher);
			
			for (int block = 0; block < numChunks + 2; block ++)
			{
				byte[] buf = new byte[ENCRYPTION_BLOCK];
				cis.read(buf);
				fos.write(buf);
			}
			cis.close();
			bis.close();
			
			
			if (stream.available() <= 0) loopFlag = false;
		}
		
		stream.close();
		fos.flush();
		fos.close();
		
		return iv;
	}
	
	
	private static void memcpy(byte[] source, byte[] dest, int numBytes)
	{
		for (int index = 0; index < numBytes; index ++)
		{
			dest[index] = source[index];
		}
	}
	
	public static File decryptFile(SecretKey aesKey, File file, byte[] iv) throws InvalidKeyException, FileNotFoundException, NoSuchAlgorithmException, NoSuchPaddingException, IOException, InvalidParameterSpecException, InvalidAlgorithmParameterException
	{	
		if (file.isDirectory())
		{
			File[] subFiles = file.listFiles();
			for (File subFile : subFiles)
			{
				decryptFile(aesKey, subFile, iv);
			}
		}
		
		FileInputStream stream = new FileInputStream(file);
		
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, aesKey, new IvParameterSpec(iv));
		
		byte[] fileBuffer = new byte[MAX_BUFFER_SIZE];
		//File encrpytedStore = new File(file.getAbsoluteFile() + ".serverEncrypt");
		FileOutputStream fos = new FileOutputStream("F:\\servertestfoler\\test.jpg");
		boolean flag = true;
		
		while (flag)
		{
			int read = stream.read(fileBuffer);
			
			ByteArrayInputStream bis = new ByteArrayInputStream(fileBuffer);
			CipherInputStream cis = new CipherInputStream(bis, cipher);
			byte[] block = new byte[ENCRYPTION_BLOCK];
			
			cis.read(block);
			
			int numChunks = ByteBuffer.wrap(block,0,4).getInt(); //get nunber of chunks in buffer
			
			byte[] buf = new byte[ENCRYPTION_BLOCK];
			for (int n = 0; n < numChunks-2; n ++)
			{
				cis.read(buf);
				fos.write(buf);
			}
			
			cis.read(buf); //last actual chunk
			byte[] paddingInfo = new byte[ENCRYPTION_BLOCK];
			try
			{
				cis.read(paddingInfo);
				fos.write(buf, 0, ENCRYPTION_BLOCK - (int)paddingInfo[0]);
			}
			catch (Exception e) {}
			
			if (stream.available() <= 0) flag = false;
		}
		
		fos.flush();
		fos.close();
		return null;
	}
}
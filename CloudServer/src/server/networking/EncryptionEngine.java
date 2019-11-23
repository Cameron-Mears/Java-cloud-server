package server.networking;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import com.filesys.JFile;

public final class EncryptionEngine 
{
	private static final int MAX_BUFFER_SIZE = (int) Math.pow(2, 28); //167MB (multiple of 128 (block size))
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
		int read = 0;
		
		String path = file.getAbsoluteFile() + ".serverEncrypt";
		int addedPadding = 0;
		
		//File encrpytedStore = new File(file.getAbsoluteFile() + ".serverEncrypt");
		FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile() + ".serverEncrypt");
		
		do
		{
			read = stream.read(fileBuffer);
			ByteArrayInputStream bis = new ByteArrayInputStream(fileBuffer);
			CipherInputStream cis = new CipherInputStream(bis, cipher);
			if (read < MAX_BUFFER_SIZE);
			{
				addedPadding = ENCRYPTION_BLOCK - (read % ENCRYPTION_BLOCK);
			}
			int blocks = (int) (Math.ceil(read/BLOCK_SIZE));
			byte[] encryptionBlock = new byte[ENCRYPTION_BLOCK];
			
			for (int block = 0; block < blocks; block++)
			{
				cis.read(encryptionBlock);
				fos.write(encryptionBlock);
			}
			if (read < MAX_BUFFER_SIZE) //adds blocks that shows additional padding
			{
				byte[] end = new byte[ENCRYPTION_BLOCK];
				end[12] = (byte) addedPadding;
				fos.write(end);
			}
			
		}
		while(read == MAX_BUFFER_SIZE);
		
		
		fos.flush();
		
		return iv;
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
		int read = 0;
		//File encrpytedStore = new File(file.getAbsoluteFile() + ".serverEncrypt");
		FileOutputStream fos = new FileOutputStream("C:\\Users\\Cameron\\Videos\\Counter-strike  Global Offensive\\Counter-strike  Global Offensive 2018.12.14 - 16.16.54.09.DV1R.mp4");
		do
		{
			int pos = 0;
			read = stream.read(fileBuffer);
			ByteArrayInputStream bis = new ByteArrayInputStream(fileBuffer);
			CipherInputStream cis = new CipherInputStream(bis, cipher);
			int blocks = (int) (Math.ceil(read/BLOCK_SIZE));
			byte[] encryptionBlock = new byte[ENCRYPTION_BLOCK];
			
			
			for (int block = 0; block < blocks - 2; block++) //last 2 blocks need to read differently
			{
				cis.read(encryptionBlock);
				fos.write(encryptionBlock);
				pos += 16;
			}
			
			if (read < MAX_BUFFER_SIZE)//only if end of file 
			{
				cis.read(encryptionBlock); //read last block normally				
				pos += 16;
				byte[] footer = Arrays.copyOfRange(fileBuffer, pos, pos + 15);
				int padding = (int)footer[12]; //get added padding from end of file
				System.out.println(padding);
				
				fos.write(encryptionBlock, 0, 16 - padding);
			}
			else //if not end file read the last 2 blocks in buffer
			{
				cis.read(encryptionBlock);
				fos.write(encryptionBlock);
			}
		}
		while(read == MAX_BUFFER_SIZE);
		
		
		fos.flush();
		fos.close();
		return null;
	}
}
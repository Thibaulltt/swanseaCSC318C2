import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;


public class binaryFileReader {
	
	public static void main(String[] args){
		
		int possibleCombinations = (int) Math.pow(2,16);
		byte[][] allKeys = new byte[possibleCombinations][]; 

		for (int i = 0; i < possibleCombinations; i++) {
			
			allKeys[i] = Arrays.copyOf(CryptoLib.smallIntToByteArray(i),8);
			  
		}
	
		
		byte[] message = randomMessagePicker("/home/wiktor/eclipse-workspace/Coursework2/src/puzzles.bin");
		
		
		
	
		
		

	}
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	public static byte[] randomMessagePicker(String filePath)	
	{
	
		
		Random random = new Random();
		int Low = 0;
		int High = 4096;
		int result = random.nextInt(High-Low) + Low;
		
		byte[] message = readBinaryFile(filePath)[result];

		
		
		return message;
		
	}
	
	
	public static byte[][] readBinaryFile (String filePath) {
		
		File file = new File(filePath); // e.g. "/home/wiktor/eclipse-workspace/Coursework2/src/puzzles.bin"		
		final int BUFFER_SIZE = 32; 	
		long fileSize = file.length();
		byte[][] data = null;
		
		FileInputStream fileInputStream;
		
		try {
			fileInputStream = new FileInputStream(file);
			 byte[] allBytes = new byte[(int) fileSize];
			 fileInputStream.read(allBytes);

			 int rest = allBytes.length % BUFFER_SIZE;  
			 
			 int chunks = allBytes.length / BUFFER_SIZE + (rest > 0 ? 1 : 0); 
			 
			  data = new byte[chunks][]; 
			    
			    for(int i = 0; i < (rest > 0 ? chunks - 1 : chunks); i++){
			        data[i] = Arrays.copyOfRange(allBytes, i * BUFFER_SIZE, i * BUFFER_SIZE + BUFFER_SIZE);
			    }
			    if(rest > 0){ 			        
			        data[chunks - 1] = Arrays.copyOfRange(allBytes, (chunks - 1) * BUFFER_SIZE, (chunks - 1) * BUFFER_SIZE + rest);
			    }
			    
			 // todo use the buffer way
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return data;
				
	}
		
}

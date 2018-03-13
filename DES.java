import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class DES {
	static Cipher cipher;
	
	public DES() throws Exception {
		cipher = Cipher.getInstance("DES");
		
	}

	public String encrypt(String plainText, SecretKey secretKey) throws Exception {
		
		//Convert plaintext into byte representation
		byte[] plainTextByte = plainText.getBytes();
		
		//Initialise the cipher to be in encrypt mode, using the given key.
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		
		//Perform the encryption
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		
		//Get a new Base64 (ASCII) encoder and use it to convert ciphertext back to a string
		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedText = encoder.encodeToString(encryptedByte);
		
		return encryptedText;
	}

	public String decrypt(String encryptedText, SecretKey secretKey)
			throws Exception {
		//Get a new Base64 (ASCII) decoder and use it to convert ciphertext from a string into bytes
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
		
		//Initialise the cipher to be in decryption mode, using the given key.
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		
		//Perform the decryption
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		
		//Convert byte representation of plaintext into a string
		String decryptedText = new String(decryptedByte);
		
		return decryptedText;
	}
	
	
	public SecretKey generateRandomKey() throws NoSuchAlgorithmException{
		//Use java's key generator to produce a random key.
		KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
		keyGenerator.init(128);														// TODO : change key length to DES key length
		SecretKey secretKey = keyGenerator.generateKey();
		
		//print the key
		String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
		System.out.println(encodedKey);

		return secretKey;
	}
	
	public SecretKey generateKeyFromPassword(String password) throws Exception{
	
		//Get byte representation of password.
		//Note here you should ideally also use salt!
		byte[] passwordInBytes = (password).getBytes("UTF-8");
		
		//Use sha to generate a message digest of the password
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		byte[] key = sha.digest(passwordInBytes);
		
		//AES keys are only 128bits (16 bytes) so take first 128 bits of digest.		
		key = Arrays.copyOf(key, 16); 												// TODO: change length to DES key length

		//Generate secret key using
		SecretKeySpec secretKey = new SecretKeySpec(key, "DES");
		
		//print the key
		String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
		System.out.println(encodedKey);
		
		return secretKey;
	}
	
}

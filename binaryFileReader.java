import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;

public class binaryFileReader {
	final static int BUFFER_SIZE = 32;

	public static void main(String[] args) {

		String filePath = "/home/wiktor/eclipse-workspace/Coursework2/src/puzzles.bin";

		System.out.println("Reading puzzle.bin file and picking random message to crack");
		byte[] randomMessage = randomMessagePicker(filePath);

		System.out.println();
		System.out.println("The random message picked is " + CryptoLib.getHexStringRepresentation(randomMessage));

		System.out.println();
		System.out.println("Decrypting the message using bruteforce");

		byte[] decryptedMessage = messageCracker(randomMessage);

		System.out.println();
		System.out.println("The decrypted message is " + CryptoLib.getHexStringRepresentation(decryptedMessage));

		System.out.println();
		System.out.println("Extracting puzzle number");

		System.out.println();
		System.out.println("The extracted puzzle number is "
				+ CryptoLib.getHexStringRepresentation(getPuzzleNumber(decryptedMessage)));

		System.out.println();
		System.out.println("Extracting shared key");

		System.out.println();
		System.out.println(
				"The extracted shared key is " + CryptoLib.getHexStringRepresentation(getSharedKey(decryptedMessage)));

	}

	public static byte[] messageCracker(byte[] message) {

		byte[] decryptedMessage = new byte[26];
		int possibleCombinations = (int) Math.pow(2, 16);
		byte[][] allKeys = new byte[possibleCombinations][];

		for (int i = 0; i < possibleCombinations; i++) {
			allKeys[i] = Arrays.copyOf(CryptoLib.smallIntToByteArray(i), 8);
		}

		for (int i = 0; i < allKeys.length; i++) {
			try {

				byte[] tempDecryptedMessage = decrypt(message, allKeys[i]);

				if (CryptoLib.getHexStringRepresentation(tempDecryptedMessage)
						.startsWith("00000000000000000000000000000000")) {
					decryptedMessage = Arrays.copyOf(tempDecryptedMessage, 26);
					break;
				}

			} catch (Exception e) {

				// TODO we might want to put some code
				// here to handle the BadPadding exceptions
				// or leave it empty to just ignore them (they are thrown when
				// we try out incorrect key to decrypt the message
			}

		}

		return decryptedMessage;

	}

	public static byte[] getSharedKey(byte[] decryptedMessage) {
		return Arrays.copyOfRange(decryptedMessage, 18, 26);

	}

	public static byte[] getPuzzleNumber(byte[] decryptedMessage) {
		return Arrays.copyOfRange(decryptedMessage, 16, 18);

	}

	public static byte[][] readBinaryFile(String filePath) {
		File file = new File(filePath);
		long fileSize = file.length();
		byte[] buffer = new byte[BUFFER_SIZE];
		byte[][] data = null;
		FileInputStream fileInputStream;

		try {
			fileInputStream = new FileInputStream(file);
			data = new byte[(int) (fileSize / BUFFER_SIZE)][BUFFER_SIZE];

			for (int i = 0; i < (fileSize / BUFFER_SIZE); i++) {
				fileInputStream.read(buffer);
				data[i] = Arrays.copyOf(buffer, BUFFER_SIZE);
			}
			fileInputStream.close();
		} catch (IOException e) {
			System.out.print("Caught exception : " + e.getMessage());
		}
		return data;
	}

	public static byte[] randomMessagePicker(String filePath) {
		long fileSize = new File(filePath).length();
		Random random = new Random();
		int randomNumber = random.nextInt((int) (fileSize / BUFFER_SIZE));
		byte[] message = readBinaryFile(filePath)[randomNumber];
		return message;
	}

	public static byte[] decrypt(byte[] encryptedMessage, byte[] keyData) throws Exception {

		Cipher cipher = Cipher.getInstance("DES");
		SecretKey secretKey = CryptoLib.createKey(keyData);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher.doFinal(encryptedMessage);
		return decryptedByte;
	}
}
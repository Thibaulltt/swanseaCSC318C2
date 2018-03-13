import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class binaryFileReader {
	final static int BUFFER_SIZE = 32;

	public static void main(String[] args) {
		String filePath = "/home/wiktor/eclipse-workspace/Coursework2/src/puzzles.bin";
		byte[][] data = readBinaryFile(filePath);
		byte[] message = randomMessagePicker(filePath);

		int possibleCombinations = (int) Math.pow(2, 16);
		byte[][] allKeys = new byte[possibleCombinations][];

		for (int i = 0; i < possibleCombinations; i++) {

			allKeys[i] = Arrays.copyOf(CryptoLib.smallIntToByteArray(i), 8);
			System.out.println(CryptoLib.getHexStringRepresentation(allKeys[i]));

		}

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
}

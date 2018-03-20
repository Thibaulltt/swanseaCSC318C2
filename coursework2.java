import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class coursework2 {
	public static void main(String[] args) throws IOException {

		// Puzzle generation
		int numberOfPuzzlesToGenerate = 4096;
		cryptoPuzzle[] puzzlesGeneratedByAlice = new cryptoPuzzle[numberOfPuzzlesToGenerate];
		puzzlesGeneratedByAlice = cryptoPuzzle.generatePuzzles(numberOfPuzzlesToGenerate);
		
		//Writing puzzles to file puzzles.bin
		cryptoPuzzle.writePuzzlesToBinary(puzzlesGeneratedByAlice, "puzzles.bin");
		
		// Reading puzzles from file puzzles.bin
		byte[][] data = binaryFileReader.readBinaryFile("puzzles.bin");
		
		// Bob randomly chooses a puzzle to crack
		byte[] BobsRandomMessage = binaryFileReader.randomMessagePicker("puzzles.bin");
		System.out.println("The message randomly chosen by Bob is: ");
		System.out.print(CryptoLib.getHexStringRepresentation(BobsRandomMessage));
		
		// Bob cracks a puzzle
		byte [] BobsDecryptedMessage = binaryFileReader.messageCracker(BobsRandomMessage);
		System.out.println();
		System.out.println("The random message chosen by Bob when decrypted: ");
		System.out.print(CryptoLib.getHexStringRepresentation(BobsDecryptedMessage));
		
		// Bob extracts puzzle number from cracked puzzle
		byte [] BobsPuzzleNumber = binaryFileReader.getPuzzleNumber(BobsDecryptedMessage);
		System.out.println();
		System.out.println("The puzzle number extracted by Bob is: ");
		System.out.print(CryptoLib.getHexStringRepresentation(BobsPuzzleNumber));
		
		//Bob extracts sharedKey from cracked puzzle
		byte [] BobsKey = binaryFileReader.getSharedKey(BobsDecryptedMessage);
		System.out.println();
		System.out.println("The shared key extracted by Bob is: ");
		System.out.print(CryptoLib.getHexStringRepresentation(BobsKey));
		
		//Alice looks up the puzzle number send from Bob
		byte[] AlicesKey = cryptoPuzzle.sharedKeyLookup(BobsPuzzleNumber, puzzlesGeneratedByAlice);
		System.out.println();
		System.out.println("The shared key looked up by Alice is: ");
		System.out.print(CryptoLib.getHexStringRepresentation(AlicesKey));
		
		try {
			DES des = new DES();
			//Alice encrypts a message with her key
			String encryptedTextMessage = des.encrypt("Hello Bob. I really like the Cryptography and IT Security module.", CryptoLib.createKey(AlicesKey));
			//Bob decrypts the message with his key
			String decryptedTextMessage = des.decrypt(encryptedTextMessage, CryptoLib.createKey(BobsKey));
			
			System.out.println();
			System.out.println("The message encrypted by Alice is: ");
			System.out.print(encryptedTextMessage);
			System.out.println();
			System.out.println("The message decrypted by Bob is: ");
			System.out.print(decryptedTextMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
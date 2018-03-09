import java.util.Set;
import java.util.HashSet;

public class coursework2 {
	public static void main(String[] args){

		int numberOfPuzzlesToGenerate = 30000;

		Set<Integer> numbersOfPuzzles = new HashSet<Integer>();

		long startTime = System.currentTimeMillis();

		// Generate the required amount of puzzles
		cryptoPuzzle[] puzzlesGenerated = new cryptoPuzzle[numberOfPuzzlesToGenerate];
		puzzlesGenerated = cryptoPuzzle.generatePuzzles(numberOfPuzzlesToGenerate);
		// stop timer

		long endTime = System.currentTimeMillis();

		for (int i = 0; i < puzzlesGenerated.length; i++) {
			numbersOfPuzzles.add(CryptoLib.byteArrayToSmallInt(puzzlesGenerated[i].getNumber()));
		}
		System.out.println("Just to check, the array is "+puzzlesGenerated.length+
		" elements long, and the set is "+numbersOfPuzzles.size()+" elements long.");
		System.out.println("Execution time of generatePuzzles() : "+(endTime-startTime)+" milliseconds.");
/*
		// And now we check them by printing their contents
		for (int i = 0; i < puzzlesGenerated.length; i++) {

			// Print puzzle number and 'content' text
			System.out.print("Puzzle n°");
			System.out.print(CryptoLib.byteArrayToSmallInt(puzzlesGenerated[i].getNumber()));
			System.out.print("	and byte : ");
			String firstByte = Integer.toBinaryString(puzzlesGenerated[i].getNumber()[0] & 0xFF).replace(' ','0');
			String secondByte = Integer.toBinaryString(puzzlesGenerated[i].getNumber()[1] & 0xFF).replace(' ','0');
			while (firstByte.length()!=8) {
				firstByte = '0'+firstByte;
			}
			while (secondByte.length()!=8) {
				secondByte = '0'+secondByte;
			}
			System.out.print(firstByte+' '+secondByte);

			// Display the puzzle content
			// WORKS AS OF 2018.03.07T18:49:34Z
			System.out.print(",	and content : ");
			
			// Get puzzle content
			byte [] contentOfPuzzle = new byte[puzzlesGenerated[i].getContentLength()];
			contentOfPuzzle = puzzlesGenerated[i].getContent();
			
			// Display it.
			for (int j = 0; j < contentOfPuzzle.length; j++) {
				System.out.print(Integer.toBinaryString(contentOfPuzzle[j] & 0xff).replace(' ','0'));
			}
			System.out.println();
		}
*/
	}
}
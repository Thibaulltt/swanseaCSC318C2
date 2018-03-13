import java.util.Set;
import java.util.HashSet;
import java.lang.Exception;

public class coursework2 {
	public static void main(String[] args){

		int numberOfPuzzlesToGenerate = 4096;

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

		// We now write them to a file.
		cryptoPuzzle.writeToFile(puzzlesGenerated, "puzzles.bin");
		
	}
}

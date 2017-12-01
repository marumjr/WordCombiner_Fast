package my.solo.finder;

/**
 * Class responsible for running the application
 * 
 * @author marumjr
 */
public class Runner {

	/**
	 * Main method.
	 * <p>
	 * It loads a word list into a dictionary and then starts combining words, trying to found the ones with an exactly
	 * certain number of characters and printing it when it also exists in the dictionary
	 * 
	 * @param args
	 *            Not used
	 */
	public static void main(String[] args) {
		int fixLength = 6;
		// Initializes a dictionary
		Dictionary dictionary = new Dictionary(fixLength);

		// Combines the words of said dictionary into other existing words
		WordCombiner wordCombiner = new WordCombiner(dictionary);
		wordCombiner.combineIntoNLetterWords(fixLength);
	}

}

package my.solo.finder;

import java.util.Set;

/**
 * Class responsible for combining words of a dictionary into existing words
 * 
 * @author marumjr
 */
public class WordCombiner {

	private Dictionary dictionary;

	/**
	 * Default constructor
	 * 
	 * @param dictionary
	 *            Dictionary containing the words to be combined
	 */
	public WordCombiner(Dictionary dictionary) {
		super();
		this.dictionary = dictionary;
	}

	/**
	 * Combines pairs of words into existing words with the exact amount of characters, printing them
	 * 
	 * @param fixLength
	 *            The fixed length a resulting word must have in order to be considered part of the solution
	 */
	public void combineIntoNLetterWords(int fixLength) {
		// Loads all words with the fixed amount of characters
		Set<String> wordsWithNLetters = this.dictionary.retrieveWordsByLength(fixLength);

		// As we are combining only pairs, we can pair 1-letter words with 5-letter words, as well as 2-letter words
		// with 4-letter words and so on
		for (int i = 1; i < fixLength; i++) {
			int j = fixLength - i;

			// Loads all words with I characters (1,2,3,4,5)
			Set<String> wordsWithILetters = this.dictionary.retrieveWordsByLength(i);
			// Loads all words with J characters, the complimentary number (5,4,3,2,1)
			Set<String> wordsWithJLetters = this.dictionary.retrieveWordsByLength(j);

			// Starts combining the groups of words
			for (String iWord : wordsWithILetters) {
				for (String jWord : wordsWithJLetters) {
					String combinedWord = iWord + jWord;

					// Asserts if the combined word exists within the group of words with the fixed amount of characters
					if (wordsWithNLetters.contains(combinedWord)) {
						// Formats and prints the result
						String result = iWord + " + " + jWord + " = " + combinedWord;
						System.out.println(result);
					}
				}
			}
		}
	}

}

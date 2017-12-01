package my.solo.finder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Class representing a dictionary, a list of possible words
 * 
 * @author marumjr
 */
public class Dictionary {

	private static final String ENCODING_ISO_8859_1 = "ISO-8859-1";

	private static final String DICTIONARY_LIST = "wordlist.txt";

	private Set<String> words;
	private Map<Integer, Set<String>> lengthToWordsMap;

	/**
	 * Default constructor
	 * <p>
	 * It loads the content of a wordlist as a collection of words to be readily accessed, filtering off all words above
	 * a certain number of characters
	 * 
	 * @param maxLength
	 *            The max number of characters a word must have in order to be loaded into this dictionary
	 */
	public Dictionary(int maxLength) {
		super();
		this.loadWordListFromFile(maxLength);
	}

	/**
	 * Retrieves a collection of all words with a certain length
	 * 
	 * @param length
	 *            The length of the words we want to access
	 * @return all words with the specified length
	 */
	public Set<String> retrieveWordsByLength(Integer length) {
		return this.lengthToWordsMap.get(length);
	}

	/**
	 * @return the collection of words in this dictionary
	 */
	public Set<String> getWords() {
		return this.words;
	}

	/**
	 * @return the {@link Map} that maps length to all words with said length
	 */
	public Map<Integer, Set<String>> getLengthToWordsMap() {
		return this.lengthToWordsMap;
	}

	/**
	 * Load the content of the wordlist.txt file as words into this dictionary, filtering out all words longer than a
	 * certain maximum length
	 * 
	 * @param maxLength
	 *            The maximum length a word must have in order to be loaded into this dictionary
	 */
	private void loadWordListFromFile(int maxLength) {
		this.words = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		this.lengthToWordsMap = new TreeMap<Integer, Set<String>>();

		try {
			// Load the dictionary
			File file = this.retrieveResourceFile(DICTIONARY_LIST);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(new FileInputStream(file), ENCODING_ISO_8859_1));

			String str;
			while ((str = in.readLine()) != null) {
				int length = str.length();

				// Filtering out all words longer than 'maxLength' letters
				if (length <= maxLength) {
					if (!this.lengthToWordsMap.containsKey(length)) {
						this.lengthToWordsMap.put(length, new TreeSet<String>());
					}

					// Together with the collection of words, which will be loaded from a file...
					this.words.add(str);
					// ... it also stores a map with words according to their lengths
					this.lengthToWordsMap.get(length).add(str);
				}
			}
			in.close();

			System.out.println("DICTIONARY COUNT: " + this.words.size());

		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Retrieve the resource file
	 * 
	 * @param filename
	 *            Name of the resource file to retrieve
	 * @return The resource file
	 * @throws URISyntaxException
	 */
	private File retrieveResourceFile(String filename) throws URISyntaxException {
		String pkgName = this.getClass().getPackage().getName().replace(".", "/") + "/";
		URI uri = this.getClass().getClassLoader().getResource(pkgName + filename).toURI();
		File file = new File(uri);

		return file;
	}

}

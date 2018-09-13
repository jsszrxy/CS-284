import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

//David Horowitz
//I pledge my honor that I have abided by the Stevens Honor System.

/**
 * Class for determining the words with the most anagrams based on a dictionary.
 */
public class Anagrams {
	//data fields
	final Integer[] primes = {2 , 3 , 5 , 7, 11 , 13 , 17 , 19 , 23 , 29 , 31 , 37 , 41 , 43 , 47 , 53 , 59 , 61 , 67 , 71 , 73 , 79 , 83 , 89 , 97 , 101};
	Map<Character, Integer> letterTable;
	Map<Long, ArrayList<String>> anagramTable;
	
	/**
	 * Constructor that creates a default Anagram instance with a letter table and empty anagram table.
	 */
	public Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long, ArrayList<String>>();
	}
	
	/**
	 * Processes the text file of words
	 * @param s - name of file
	 * @throws IOException if file is not found or not readable
	 */
	private void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine ;
		while ((strLine=br.readLine()) != null) {
		this.addWord(strLine);
		}
		br.close ();
	}
	/**
	 * Constructs a letter table that gives each letter a prime number value in a hash table.
	 */
	private void buildLetterTable() {
		Character alphabet[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		letterTable = new HashMap<Character, Integer>();
		for(int i = 0; i<26; i++) {
			letterTable.put(alphabet[i], primes[i]);
		}
	}
	
	/**
	 * Computes the hash code for a given string.
	 * @param s - string to be used in computation
	 * @return Long hash code
	 */
	private Long myHashCode(String s) {
		if(s == null) {
			throw new NullPointerException("String cannot be null");
		}
		Long mult = 1L;
		for (Character ch : s.toCharArray()) {
			mult *= (long)letterTable.get(ch);
		}
		return mult;
	}
	
	/**
	 * Adds the given word to the hash table.
	 * @param s - String that is added to the table.
	 */
	private void addWord(String s) {
		if(s == null) {
			throw new NullPointerException("String cannot be null");
		}
		Long hash = myHashCode(s);
		if(anagramTable.get(hash) == null) {
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(s);
			anagramTable.put(hash, temp);
		} else {
			anagramTable.get(hash).add(s);
		}
	}
	
	/**
	 * Finds the words with the most anagrams in the hash table.
	 * @return a list of words that have the most anagrams in the hash table.
	 */
	private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
		int maxSize = 0;
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxs = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
		for(Entry<Long,ArrayList<String>> ent : anagramTable.entrySet()) {
			if(ent.getValue().size() > maxSize) {
				maxSize = ent.getValue().size();
				maxs.clear();
				maxs.add(ent);
			} else if(ent.getValue().size() == maxSize) {
				maxs.add(ent);
			}
		}
		return maxs;
	}
	/**
	 * Used to Junit Test.
	 */
	static String maxEntriesTest = "";
	/**
	 * Tests the Anagram class with the given dictionary file.
	 */
	public static void main (String [] args) {
		Anagrams a = new Anagrams ();
		final long startTime = System . nanoTime ();
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList <Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries ();
		maxEntriesTest = maxEntries.toString();
		final long estimatedTime = System.nanoTime() - startTime ;
		final double seconds = ((double)estimatedTime / 1000000000);
		System.out.println ("Time: " + seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
		}

}

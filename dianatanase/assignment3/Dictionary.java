import java.util.HashSet;
import java.util.List;

// Create a Dictionary class with addWord, isWord and isPrefix methods.
// Methods are case-insensitive.
public class Dictionary {

	// Store the words and prefixes in Sets to avoid duplicates.
	HashSet<String> words = new HashSet<String>();
	HashSet<String> prefixes = new HashSet<String>();
	
	public void addWord(String word){
		String lowerWord = word.toLowerCase();
		words.add(lowerWord);
		// Every time a word is added to the dictionary.
		// Add its prefixes to the prefixes set as well.
		for(int i = 0; i < word.length(); i++){
			prefixes.add(lowerWord.substring(0,i+1));
		}
	}
	
	public void addListOfWords(List<String> words){
		for(String word: words)
			addWord(word);
	}
	
	public boolean isWord(String word){
		return words.contains(word.toLowerCase());
	}
	
	public boolean isPrefix(String prefix){
		return prefixes.contains(prefix.toLowerCase());
	}
	
}

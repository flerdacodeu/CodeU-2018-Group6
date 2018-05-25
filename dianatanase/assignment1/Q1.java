import java.util.HashMap;
import java.util.Scanner;

// Note: for words anagrams, I could have sorted each word and compare them
// But this method would have more time complexity from sorting.
// The HashMap method I used though takes more memory.

public class Q1 {
	
	// To tackle the insensitive case, transform words in all lowerCase characters and use the case sensitive method.
	private static boolean areWordsAnagramsNotSensitive(String word1, String word2){
		return areWordsAnagramsSensitive(word1.toLowerCase(), word2.toLowerCase());
	}
	
	private static boolean areWordsAnagramsSensitive(String word1, String word2){
		if(word1.length() != word2.length()){
			return false;
		}
		//Map the frequencies of each character in word1, save in map_word1.
		HashMap<Character, Integer> map_word1 = new HashMap<Character, Integer>();
		int size_word1 = word1.length();
		int size_word2 = word2.length();
		
		for(int i = 0; i < size_word1; i++){
			char letter = word1.charAt(i);
			//If the letter exists in map, update it.
			if(map_word1.containsKey(letter)){
				int freq = map_word1.get(letter);
				freq++;
				map_word1.replace(letter, freq);
			}
			else{ //If the letter does not exist in map, put it.
				map_word1.put(letter, 1);
			}
		}
		
		// Check every letter in word2 has the same frequency in word1 by using map_word1.
		for(int i = 0; i < size_word2; i++){
			char letter = word2.charAt(i);
			if(map_word1.containsKey(letter)){
				int freq = map_word1.get(letter);
				// A letter in word2 appears more times than in word1
				if(freq <= 0){
					return false;
				}
				freq--;
				map_word1.replace(letter, freq);
			}
			else{ // Letter in word2 does not appear in word1
				return false;
			}
		}
		
		return true;
		
	}
	
	private static boolean areSentencesAnagrams(String sent1, String sent2){
		// Split sentences in words with regards to separators - ? ! : ; , . 
		String delimiters = "[-?!:;,. ]+";
		String[] words_sent1 = sent1.split(delimiters);
		String[] words_sent2 = sent2.split(delimiters);
		
		// If one sentence has more words than the other, then not anagrams.
		if(words_sent1.length != words_sent2.length)
			return false;
		
		// Map every word in sent1 with its frequency.
		HashMap<String, Integer> map_sent1 = new HashMap<String, Integer>();
		for(String word: words_sent1){
			if(map_sent1.containsKey(word)){
				int freq = map_sent1.get(word);
				freq++;
				map_sent1.replace(word, freq);
			}
			else{
				map_sent1.put(word, 1);
			}
		}
		
		for(String word2: words_sent2){
			// Check every word in sent2 and see if it has an anagram in sent1
			boolean found = false;
			for(String word1: map_sent1.keySet()){
				if(areWordsAnagramsNotSensitive(word1, word2)){
					int freq = map_sent1.get(word1);
					// if (freq <=0) then word1 is not valid as it has been used as anagram before, need to keep searching
					if(freq > 0){ 
						freq--;
						map_sent1.replace(word1, freq);
						found = true;
						break; // we found a good anagram in sent1 of word2 
					}
				}	
			}
			// No anagram found in sent1 for word2
			if(!found)
				return false;
		}
		
		return true;
	}
	
	// main method for testing
	public static void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input first sentence: ");
		String sent1 = scanner.nextLine();
		System.out.println("Input second sentence: ");
		String sent2 = scanner.nextLine();
		System.out.println("Result: ");
		System.out.println(areSentencesAnagrams(sent1, sent2));
		scanner.close();
	}
}

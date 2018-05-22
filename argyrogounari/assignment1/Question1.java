import java.util.Scanner;
import java.util.*;

public class Question1 {

	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);  
		System.out.println("Enter your strings: ");
		String sentence1 = reader.nextLine();
		String sentence2 = reader.nextLine();
		
		reader.close(); 
		
		if (anagram(sentence1,sentence2)) {
			System.out.printf("'%s' and '%s' are anagrams",sentence1, sentence2);
		} else {
			System.out.printf("'%s' and '%s' are NOT anagrams",sentence1, sentence2);
		}

	}
	
	public static boolean anagram(String sentence1, String sentence2){
		
		ArrayList<String> sentence1Words = new ArrayList<String>(Arrays.asList(sentence1.split(" ")));
		ArrayList<String> sentence2Words = new ArrayList<String>(Arrays.asList(sentence2.split(" ")));
		
		int anagram = 0;
        for (int i = 0; i < sentence1Words.size(); i++){
        	for (int j = 0; j < sentence2Words.size(); j++){
        		if (anagramWord(sentence1Words.get(i), sentence2Words.get(j))) {
        			sentence2Words.remove(j);
        			anagram ++;
        		}
        	}
        }
        
        if (anagram == sentence1Words.size()){
        	return true;
        }
        
        return false;
	    
	}
	
	public static boolean anagramWord(String word1, String word2){
		
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		
		if (word1.equals(word2)) {
			return false;
		}
		
		if (word1.length() == word2.length()) {
			
			ArrayList<Character> word1Letters = new ArrayList<Character>();
	        for (int i = 0; i < word1.length(); i++){
	        	word1Letters.add(word1.charAt(i));
	        }
	        ArrayList<Character> word2Letters = new ArrayList<Character>();
	        for (int i = 0; i < word2.length(); i++){
	        	word2Letters.add(word2.charAt(i));
	        }
	        
	        int same = 0;
	        for (int i = 0; i < word1.length(); i++){
	        	for (int j = 0; j < word2Letters.size(); j++){
	        		if (word1Letters.get(i) == word2Letters.get(j)) {
	        			word2Letters.remove(j);
	        			same ++;
	        		}
	        	}
	        }
	        
	        if (same == word1.length()){
	        	return true;
	        }

		} 
		
		return false;
	}

}

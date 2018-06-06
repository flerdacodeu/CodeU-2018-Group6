import java.util.Scanner;
import java.util.*;
/**
* Question 1: Given two strings, determine if one is an anagram of the other. 
* Two words are anagrams of each other if they are made of the same
* letters in a different order.
*
* @author  Argyro Gounari
* @version 1.1
* @since   15-05-2018 
*/
public class Question1 {

	public static void main(String[] args) {
		
		Scanner reader = new Scanner(System.in);  
		System.out.println("Enter your strings: ");
		String sentence1 = reader.nextLine();
		String sentence2 = reader.nextLine();
		
		reader.close(); 
		
		if (isAnagram(sentence1,sentence2)) {
			System.out.printf("'%s' and '%s' are anagrams",sentence1, sentence2);
		} else {
			System.out.printf("'%s' and '%s' are NOT anagrams",sentence1, sentence2);
		}

	}
	
	public static boolean isAnagram(String sentence1, String sentence2){
		
		ArrayList<String> sentence1Words = new ArrayList<String>(Arrays.asList(sentence1.split("[\\p{Punct}\\s]+")));
		ArrayList<String> sentence2Words = new ArrayList<String>(Arrays.asList(sentence2.split("[\\p{Punct}\\s]+")));
		System.out.println(sentence1Words);
		System.out.println(sentence2Words);
		int anagram = 0;
		int sentense1Size = sentence1Words.size();
		
		for (int i = 0; i < sentence1Words.size(); i++){
        	for (int j = 0; j < sentence2Words.size(); j++){
        		if (anagramWord(sentence1Words.get(i).trim(), sentence2Words.get(j).trim()) && !(sentence1Words.size()==0)) {
        			sentence2Words.remove(j);
        			sentence2Words.remove(i);
        			anagram ++;
        		}
        	}
        }
		
        //foo bar
        //oof ofo
		//rab oof baz
        if (anagram == sentense1Size){
        	return true;
        }
        
        return false;
	    
	}
	
	public static boolean anagramWord(String word1, String word2){
		
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		
		if (word1.equals(word2)) {
			return true;
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
	        			continue;
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

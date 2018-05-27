package assignment1;

import java.util.*;

/**
 * Class Q1 has one public method 'areAnagrams', one private method 'outcomeOfAreAnagramsCheck'
 * and a main method used for testing.
 * 
 * Method boolean areAnagrams(String, String) takes two strings and checks whether
 * they are anagrams of one another. The check is case-insensitive and in case of 
 * inputs that are sentences, requires each word of the second sentence to be an
 * anagram of one of the words in the first sentence (and vice-versa).
 * Method String outcomeOfAreAnagramsCheck(String, String) is used for testing purposes.
 * 
 * The solution used for determining if one string is an anagram of the other is as follows:
 * 1) clean the inputs of all non-alphanumerical characters
 * 2) split each input by the space character to get an array of words
 * 3) sort each word in both arrays alphabetically and the sort the whole array
 * 4) check for a mismatch in the arrays, if none are found, the inputs are anagrams!
 * 
 * Another possible solution would be to keep track of the number of characters
 * in each word using a HashMap<Character, Integer>, but since for the follow-up
 * we have to make sure that each word of the sentence is the anagram of one of
 * the words in the resulting string, rather than have the whole sentences be ana-
 * grams of each other, I would have to keep a separate HashMap for each word of
 * the 'original' sentence, I felt like the above solution is cleaner and easier
 * to understand.
 */
public class Q1 {

    // returns true if the inputs are anagrams
    public static boolean areAnagrams(String firstSentence, String secondSentence) {
        String[] firstSentenceWords, secondSentenceWords;

        // get rid of non-alphanumerical characters
        firstSentence = firstSentence.replaceAll("[^A-Za-z0-9 ]", "");
        secondSentence = secondSentence.replaceAll("[^A-Za-z0-9 ]", "");

        // split input by space to get an array of strings
        firstSentenceWords = firstSentence.toLowerCase().split(" ");
        secondSentenceWords = secondSentence.toLowerCase().split(" ");

        // check if the two arrays have the same num of words -> can't be anagrams if they don't!
        if (firstSentenceWords.length != secondSentenceWords.length) {
            return false;
        }
        // sort each string(word) of the two arrays alphabetically
        for (int i = 0; i < firstSentenceWords.length; i++) {
            char[] charArray = firstSentenceWords[i].toCharArray();
            Arrays.sort(charArray);
            firstSentenceWords[i] = new String(charArray);
            charArray = secondSentenceWords[i].toCharArray();
            Arrays.sort(charArray);
            secondSentenceWords[i] = new String(charArray);
        }

        // sort the whole array of words alphabetically
        Arrays.sort(firstSentenceWords);
        Arrays.sort(secondSentenceWords);

        // go through the both arrays to see if there is a mismatch
        for (int i = 0; i < firstSentenceWords.length; i++) {
            if (firstSentenceWords[i].compareTo(secondSentenceWords[i]) != 0) {
                return false;
            }
        }

        return true;

    }
    
    private static String outcomeOfAreAnagramsCheck(String firstSentence, String secondSentence) {
        return "Inputs: '" + firstSentence + "' and '" + secondSentence + "' are "
                    + (areAnagrams(firstSentence, secondSentence) ? "anagrams! :)" : "not anagrams! :(");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if (args.length == 2) {
            System.out.println(outcomeOfAreAnagramsCheck(args[0], args[1]));
        } else {
            System.out.println("To choose by yourself the strings to be tested, please provide two strings as program inputs.");
            
            System.out.println("\n--------------------------------------------Testing--------------------------------------------\n");

            System.out.print(" --> Valid input; two words that are anagrams: ");
            System.out.println(outcomeOfAreAnagramsCheck("listen", "silent"));
            
            System.out.print(" --> Valid input; two words that are anagrams: ");
            System.out.println(outcomeOfAreAnagramsCheck("silent", "listen"));
            
            System.out.print(" --> Valid input; two sentences that are anagrams: ");
            System.out.println(outcomeOfAreAnagramsCheck("Good Morning, Googlers!", "ornmngi ooggersl doog"));
            
            System.out.print(" --> Valid input; two sentences where each word isn't an anagram of the other: ");
            System.out.println(outcomeOfAreAnagramsCheck("Tom Marvolo Riddle", "I am Lord Voldemort"));
            
            System.out.print(" --> One valid input, one empty input: ");
            System.out.println(outcomeOfAreAnagramsCheck("Ana", ""));
            
            System.out.print(" --> Empty input: ");
            System.out.println(outcomeOfAreAnagramsCheck("", ""));
            
            System.out.println("\n-----------------------------------------------------------------------------------------------------------------");
        }

    }

}

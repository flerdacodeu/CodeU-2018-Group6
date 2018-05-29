package Assignment1;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

enum Case{
    SENSITIVE,
    INSENSITIVE;
        }

/**
 Given two strings, determine if one is an anagram of the other. Two words are anagrams of
 each other if they are made of the same letters in a different order.
 For example:
 "listen" and "silent" are anagrams
 "triangle" and "integral" are anagrams
 "apple" and "pabble" are NOT anagrams
 Follow-ups:
 Make the algorithm able to handle both case sensitive and case insensitive anagrams.
 Make the algorithm able to handle anagrams of sentences, where each word in the resulting
 sentence is an anagram of one of the words in the original sentence.
 */
public class Question1 {

    /**
     * @param firstString
     * @param secondString
     * @param isCaseSensitive if true, characters comparisons are case sensitive
     * @return true if the two String arguments are anagrams of each other
     */
    public static boolean areWordsAnagrams(String firstString, String secondString, Case isCaseSensitive) {

        if (firstString == null || secondString == null) {
            return false;
        }
        if (firstString.length() != secondString.length()) {
            return false;
        }

        if (isCaseSensitive.equals(Case.INSENSITIVE)) {
            firstString = firstString.toLowerCase();
            secondString = secondString.toLowerCase();
        }

        if (firstString.equals(secondString)) {
            return true;
        }

        // use a hashMap for the frequencies of characters in the first word, compare
        // to the characters in the second word
        Map<Character, Integer> map_firstWord = new HashMap<>();
        for (int i = 0; i < firstString.length(); i++) {
            char c = firstString.charAt(i);
            map_firstWord.put(c, map_firstWord.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < secondString.length(); i++) {
            char c = secondString.charAt(i);
            int freq = map_firstWord.getOrDefault(c, 0);
            if (freq <= 0) {
                return false;
            }
            map_firstWord.put(c, freq - 1);
        }

        return true;
    }

    /**
     * @param firstSentence
     * @param secondSentence
     * @param isCaseSensitive
     * @return true if every word in the first sentence has an anagram in the second sentence
     */
    public static boolean areSentencesAnagrams(String firstSentence, String secondSentence, Case isCaseSensitive) {
        if (firstSentence == null || secondSentence == null) {
            return false;
        }
        if (isCaseSensitive.equals(Case.INSENSITIVE)) {
            firstSentence = firstSentence.toLowerCase();
            secondSentence = secondSentence.toLowerCase();
        }

        String[] firstSentWords = firstSentence.split("\\W+");
        String[] secondSentWords = secondSentence.split("\\W+");

        if (firstSentWords.length != secondSentWords.length) {
            return false;
        }

        Map<String, Integer> secondSentWordsMap = new HashMap<>();
        for (String word : secondSentWords) {
            secondSentWordsMap.put(word, secondSentWordsMap.getOrDefault(word, 0) + 1);
        }

        // check every word in the first sentence has an anagram in the second sentence
        // use the hashmap of word frequencies of the second sentence for repeated words
        for (String word : firstSentWords) {
            boolean foundMatch = false;
            for (String matchWord : secondSentWordsMap.keySet()) {
                int freq = secondSentWordsMap.get(matchWord);
                if (freq == 0) {
                    continue;
                }
                if (areWordsAnagrams(word, matchWord, isCaseSensitive)) {
                    secondSentWordsMap.put(matchWord, freq - 1);
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) {
                return false;
            }
        }
        return true;

    }

    /**
     * Helper method to test areWordsAnagrams both ways
     */
    public static void commutativeTestWords(String firstString, String secondString, Case isCaseSensitive, boolean expected){
        if(expected){
            assertTrue(areWordsAnagrams(firstString, secondString, isCaseSensitive));
            assertTrue(areWordsAnagrams(secondString, firstString, isCaseSensitive));
        }
        else {
            assertFalse(areWordsAnagrams(firstString, secondString, isCaseSensitive));
            assertFalse(areWordsAnagrams(secondString, firstString, isCaseSensitive));
        }
    }

    /**
     * Helper method to test areSentencesAnagrams both ways
     */
    public static void commutativeTestSentences(String firstString, String secondString, Case isCaseSensitive, boolean expected){
        if(expected){
            assertTrue(areSentencesAnagrams(firstString, secondString, isCaseSensitive));
            assertTrue(areSentencesAnagrams(secondString, firstString, isCaseSensitive));
        }
        else {
            assertFalse(areSentencesAnagrams(firstString, secondString, isCaseSensitive));
            assertFalse(areSentencesAnagrams(secondString, firstString, isCaseSensitive));
        }
    }

    public static void main(String[] args) {
        commutativeTestWords("abcd", "dbca", Case.INSENSITIVE, true); // simple true case
        commutativeTestWords("aabc", "abbc", Case.INSENSITIVE, false); // simple false case
        commutativeTestWords("aav", "aa", Case.INSENSITIVE, false); // extra character in one string
        commutativeTestWords("aa", "aaa", Case.INSENSITIVE, false); //identical characters but one extra
        commutativeTestWords("", " ", Case.INSENSITIVE, false); //empty string
        commutativeTestWords("Abcd", "abcd", Case.SENSITIVE, false); //case sensitive
        commutativeTestWords("Abcd", "abcd", Case.INSENSITIVE, true); //case insensitive

        //sentences
        commutativeTestSentences("bla alb bla", "bla bla lba", Case.INSENSITIVE, true); //simple anagram sentences
        commutativeTestSentences("hi hello hi", "ih Hi elloh", Case.SENSITIVE, false); //case insensitive sentence
        commutativeTestSentences("hi,hello hi", "ih, hi ! elloh", Case.INSENSITIVE, true); //simple anagram sentences with punctuation
        commutativeTestSentences("a b c", "ab c", Case.INSENSITIVE, false); //test spaces matter


    }
}

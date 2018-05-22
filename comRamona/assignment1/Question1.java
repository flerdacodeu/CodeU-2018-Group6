import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Given two strings, determine if one is an anagram of the other. Two words are anagrams of
 * each other if they are made of the same letters in a different order.
 * Extensions:
 * -handle both case sensitive and insensitive
 * -handle anagrams of sentences
 */
public class Question1 {

    /**
     * @param firstString
     * @param secondString
     * @param isCaseSensitive if true, characters comparisons are case sensitive
     * @return true if the two String arguments are anagrams of each other
     */
    public static boolean areWordsAnagrams(String firstString, String secondString, boolean isCaseSensitive) {

        if (firstString == null || secondString == null) {
            return false;
        }
        if (firstString.length() != secondString.length()) {
            return false;
        }

        if (!isCaseSensitive) {
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
    public static boolean areSentencesAnagrams(String firstSentence, String secondSentence, boolean isCaseSensitive) {
        if (firstSentence == null || secondSentence == null) {
            return false;
        }
        if (!isCaseSensitive) {
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
                if (areWordsAnagrams(word, matchWord, true)) {
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

    public static void main(String[] args) {
        boolean testPassed = true;
        //simple tests
        assertTrue(areWordsAnagrams("abcd", "dbca", false));
        assertFalse(areWordsAnagrams("aabc", "abbc", false));
        assertFalse(areWordsAnagrams("aa", "aav", false));
        assertFalse(areWordsAnagrams("aav", "aa", false));
        assertFalse(areWordsAnagrams("aa", "aaa", false));
        assertFalse(areWordsAnagrams("aaa", "aa", false));
        assertFalse(areWordsAnagrams("", " ", false));
        //case sensitive tests
        assertFalse(areWordsAnagrams("Abcd", "abcd", true));
        assertTrue(areWordsAnagrams("Abcd", "abcd", false));

        //sentences
        assertTrue(areSentencesAnagrams("hi,hello hi", "ih, hi ! elloh", true));
        assertFalse(areSentencesAnagrams("hi hello hi", "ih Hi elloh", true));
        assertTrue(areSentencesAnagrams("bla alb bla", "bla bla bla", false));
        assertFalse(areSentencesAnagrams("a b c", "ab c", false));


    }
}

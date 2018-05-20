/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.util.*;

/**
 *
 * @author Ana
 */
public class Q1 {

    // returns true if the inputs are anagrams
    private static boolean q1(String o, String r) {
        String[] original, result;

        // get rid of non-alphanumerical characters
        o = o.replaceAll("[^A-Za-z0-9 ]", "");
        r = r.replaceAll("[^A-Za-z0-9 ]", "");

        // split input by space to get an array of strings
        original = o.toLowerCase().split(" ");
        result = r.toLowerCase().split(" ");

        // check if the two arrays are the same lenght -> can't be anagrams if they're not!
        if (original.length != result.length) {
            return false;
        }
        // sort each string of the two arrays alphabetically
        for (int i = 0; i < original.length; i++) {
            char[] charArray = original[i].toCharArray();
            Arrays.sort(charArray);
            original[i] = new String(charArray);
            charArray = result[i].toCharArray();
            Arrays.sort(charArray);
            result[i] = new String(charArray);
        }

        // sort the whole array of strings alphabetically
        Arrays.sort(original);
        Arrays.sort(result);

        // go through the both arrays to see if there is a mismatch
        for (int i = 0; i < original.length; i++) {
            if (original[i].compareTo(result[i]) != 0) {
                return false;
            }
        }

        return true;

    }

    /*
        Another possible solution would be to keep track of the number of characters
        in each word using a HashMap<Character, Integer>, but since for the follow-up
        we have to make sure that each word of the sentence is the anagram of one of
        the words in the resulting string, rather than have the whole sentences be ana-
        grams of each other, I would have to keep a separate HashMap for each word of
        the 'original' sentence, I felt like the above solution is cleaner and easier
        to understand.
     */

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if (args.length == 2) {
            String message = "Inputs: '" + args[0] + "' and '" + args[1] + "' are "
                    + (q1(args[0], args[1]) ? "anagrams! :)" : "not anagrams! :(");
            System.out.println(message);
        } else {
            System.out.println("Please provide two strings as program inputs.");
        }

    }

}

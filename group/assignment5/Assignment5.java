package assignment5;

import java.util.LinkedList;
import java.util.List;

public class Assignment5 {

    private Graph graphOfLetters = new Graph();

    public LinkedList<LinkedList<Character>> findAllAphabetsOfLanguage(List<String> dictionaryList) {
        LinkedList<LinkedList<Character>> allPossibleAlphabets = new LinkedList<>();

        if (dictionaryList.isEmpty()) {
            return allPossibleAlphabets;
        }

        if (dictionaryList.size() == 1) {
            formTopologicalGraphFromOneWordDictionary(dictionaryList.get(0));
        } else {
            formTopologicalGraph(dictionaryList);
        }

        allPossibleAlphabets = graphOfLetters.allTopologicalSortOrders();

        return allPossibleAlphabets;
    }

    public void formTopologicalGraphFromOneWordDictionary(String word) {
        for (int i = 0; i < word.length(); i++) {
            addOrFetchVertex(word.charAt(i));
        }
    }

    public void formTopologicalGraph(List<String> dictionaryList) {
        for (int i = 0; i < dictionaryList.size() - 1; i++) {
            String firstWord = dictionaryList.get(i);
            String secondWord = dictionaryList.get(i + 1);

            int index = 0;
            int firstWordLength = firstWord.length();
            int secondWordLength = secondWord.length();

            boolean checkForGraphEdges = true;
            while (index < firstWordLength && index < secondWordLength) {
                Character firstWordsCharacter = firstWord.charAt(index);
                Character secondWordsCharacter = secondWord.charAt(index);

                // insert firstWord's character if it isn't in the map and make a space for it in the graph; or just fetch it from map if it exists
                Vertex firstWordsCharVertex = addOrFetchVertex(firstWordsCharacter);
                //insert secondWrod's character if it isn't in the map (also works if the characters are the same, because it has just been inserted); or just fetch it from map if it exists
                Vertex secondWordsCharVertex = addOrFetchVertex(secondWordsCharacter);

                if (!firstWordsCharacter.equals(secondWordsCharacter) && checkForGraphEdges) {
                    // different characters, both inserted in the map if needed
                    // if they are the first two different characters discovered, 
                    // insert edge in graph from firstWordsCharacter to secondWordsCharacter
                    checkForGraphEdges = false;
                    graphOfLetters.addEdge(firstWordsCharVertex, secondWordsCharVertex);
                }
                index++;
            }

            int secondWordIndex = index;

            // process until the end of the first word if it is longer
            while (index < firstWordLength) {
                Character firstWordsCharacter = firstWord.charAt(index);
                addOrFetchVertex(firstWordsCharacter);
                index++;
            }

            // need to process the last word in dictionary, if it is longer
            if (isSecondWordLastInDictionary(dictionaryList.size(), i + 1)) {
                while (secondWordIndex < secondWordLength) {
                    Character secondWordsCharacter = secondWord.charAt(secondWordIndex);
                    addOrFetchVertex(secondWordsCharacter);
                    secondWordIndex++;
                }
            }

        }
    }

    private Vertex addOrFetchVertex(Character letter) {
        Vertex vertex;
        if (!graphOfLetters.containsVertex(letter)) {
            vertex = graphOfLetters.addVertex(letter);
        } else {
            vertex = graphOfLetters.getVertex(letter);
        }
        return vertex;
    }

    private boolean isSecondWordLastInDictionary(int dictionarySize, int secondWordPosition) {
        return secondWordPosition == (dictionarySize - 1);
    }

    public static void main(String[] args) {
        LinkedList<String> dictionary = new LinkedList<>();
        /* 
        dictionary.add("ART");
        dictionary.add("RAT");
        dictionary.add("CAT");
        dictionary.add("CAR");
         */

 /* 
        dictionary.add("54");
        dictionary.add("50");
        dictionary.add("24");
        dictionary.add("21");
        dictionary.add("35");
        dictionary.add("30");
        dictionary.add("15");
         */
 
        //dictionary.add("ACE");
        //dictionary.add("ALU");
        dictionary.add("BEAR");
        dictionary.add("BETTER");    
        dictionary.add("CALM");
        dictionary.add("CLEAR");
        dictionary.add("CLUB");
        dictionary.add("CLUE");
        dictionary.add("CLUSTER");
        dictionary.add("CUTE");
        dictionary.add("READY");
        dictionary.add("REAR");

        Assignment5 alphabetCreator = new Assignment5();
        //alphabetCreator.formTopologicalGraph(dictionary);
        long millisStart = System.currentTimeMillis();
        LinkedList<LinkedList<Character>> allPossibleAlphabets = alphabetCreator.findAllAphabetsOfLanguage(dictionary);
        long millisEnd = System.currentTimeMillis();

        System.out.println("Number of possible alphabets: " + allPossibleAlphabets.size() + "; found in: " + (millisEnd - millisStart) + " milliseconds.");
        if (allPossibleAlphabets.size() < 100) {
            for (int i = 0; i < allPossibleAlphabets.size(); i++) {
                LinkedList<Character> currentPossibleAlphabet = allPossibleAlphabets.get(i);
                for (int j = 0; j < currentPossibleAlphabet.size(); j++) {
                    System.out.print(currentPossibleAlphabet.get(j) + " ");
                }
                System.out.println("");
            }
        }

    }

}

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

// Helper class to represent the characters in the words and their relation.
// vertex_first is the parent of vertex_second if the char of vertex_first comes before the char of vertex_second in the alphabet.
// i.e if Abc < Bcd then vertex with char B is the child of vertex with char A.
// A vertex can be in 3 states for the topological visit:
// white = not yet visited; grey = visited, but not yet finished; black = finished.
class Vertex {

    final Character value;
    Set<Vertex> children = new HashSet<Vertex>();
    String state = "white";
    int depth = 0;

    Vertex(Character value) {
        this.value = value;
    }

    public void addChild(Vertex child) {
        children.add(child);
    }

    public void setState(String state) {
        this.state = state;
    }

    public Character getValue() {
        return value;
    }

    public Set<Vertex> getChildren() {
        return children;
    }

    public String getState() {
        return state;
    }
    
    public int getDepth() {
        return depth;
    }
    
    public void increaseDepth() {
        depth++;
    }
    
    public void decreaseDepth() {
        depth--;
    }

}

// Solution will create the list of vertices and do topological sort to find a valid order.
// During topological traversal, chars will be added a the beginning of a linked list alphabet.
// Keep a list vertices of all the vertices created from the words in dictionary.
// Keep a map of every char to its vertex to check if a vertex was already created for that char.
public class UnknownAlphabet {

    LinkedList<Character> alphabet = new LinkedList<Character>();
    LinkedList<Character> currentAlphabet = new LinkedList<Character>();
    LinkedList<LinkedList<Character>> allPossibleAlphabets = new LinkedList<>();
    LinkedList<Vertex> vertices = new LinkedList<Vertex>();
    HashMap<Character, Vertex> mapCharToVertex = new HashMap<Character, Vertex>();

    // Assume dictionary input is a List.
    public LinkedList<Character> getAlphabet(List<String> dict) throws Exception {

        if (dict.isEmpty()) {
            return new LinkedList<Character>();
        }

        // Return all characters (no duplicates) if dictionary has only one word.
        if (dict.size() == 1) {
            alphabetForOneWord(dict.get(0));
        } else {
            createVertices(dict);
            topologicalSort();
        }

        return alphabet;
    }
    
    public LinkedList<LinkedList<Character>> getAllAlphabets(List<String> dict) throws Exception {
        if (dict.isEmpty()) {
            return new LinkedList<>();
        }

        if (dict.size() == 1) {
            alphabetForOneWord(dict.get(0));
        } else {
            createVertices(dict);
            allTopologicalSort();
        }

        return allPossibleAlphabets;
    }

    private void alphabetForOneWord(String word) {

        for (int i = 0; i < word.length(); i++) {
            Character ch = word.charAt(i);
            if (!alphabet.contains(ch)) {
                alphabet.add(ch);
            }
        }
    }

    // Traverse verticies which have not been visited yet.
    private void topologicalSort() throws Exception {

        for (Vertex vertex : vertices) {
            if (vertex.getState().equals("white")) {
                sortFromVertex(vertex);
            }
        }
    }

    private void allTopologicalSort() throws Exception {
        boolean allFound = false;

        for (Vertex vertex : vertices) {
            if (vertex.getState().equals("white") && vertex.getDepth() == 0) {
                vertex.setState("black");
                // "delete" the branch to every child by decreasing the depth
                Iterator<Vertex> iter = vertex.getChildren().iterator();
                while (iter.hasNext()) {
                    Vertex child = iter.next();
                    child.decreaseDepth();
                }

                currentAlphabet.add(vertex.getValue());
                allTopologicalSort();

                // reset the vertix info allowing it to be chosen again in another combination
                vertex.setState("white");
                iter = vertex.getChildren().iterator();
                while (iter.hasNext()) {
                    Vertex child = iter.next();
                    child.increaseDepth();
                }
                currentAlphabet.remove(vertex.getValue());

                allFound = true;
            }
        }

        if (!allFound) {
            // make different lists for each possible alphabet
            LinkedList<Character> newAlphabet = new LinkedList<>();
            for (Character letter : currentAlphabet) {
                newAlphabet.add(letter);
            }
            allPossibleAlphabets.add(newAlphabet);
        }

    }

    // For each unvisisted vertex, visit its children if they haven't been visited (white).
    // If during the visit of a child we discover it was started but not finished (grey),
    // then we have a cycle, so no order is possible.
    private void sortFromVertex(Vertex currentVertex) throws Exception {

        currentVertex.setState("grey"); // starting visit, but not finished
        for (Vertex child : currentVertex.getChildren()) {
            if (child.getState().equals("white")) {
                sortFromVertex(child);
            } else if (child.getState().equals("grey")) {
                throw new Exception("There is a cycle, order impossible.");
            }
        }
        currentVertex.setState("black"); // finished visit.
        alphabet.addFirst(currentVertex.getValue());
    }

    // Look at every 2 consecutive words to get the relation between the word_first 2 different characters.
    // For example, in [ABCD, ABDE, EFGH, FMNO]: C < D (from word_first 2 words), A < E (from 2nd and 3rd), E < F (from 3rd and 4th)
    // Because A < E and E < F then from transitivity => A < E without needing to check word 1 or 2 with word 4.
    public void createVertices(List<String> dict) {

        boolean isSecondWordLast = false;
        int dictSize = dict.size();

        for (int i = 0; i < dictSize - 1; i++) {
            String firstWord = dict.get(i);
            String secondWord = dict.get(i + 1);

            if (i == dictSize - 1) {
                isSecondWordLast = true;
            }

            createVerticesHelper(firstWord, secondWord, isSecondWordLast);
        }
    }

    private void createVerticesHelper(String firstWord, String secondWord, boolean isSecondWordLast) {

        int firstWordLength = firstWord.length();
        int secondWordLength = secondWord.length();
        int minLength = Math.min(firstWordLength, secondWordLength);

        int index = 0;

        // Iterate through word_first and word_second word until they have a different character.
        // For each new character, create its vertex and add it to the list using createVertex.
        while (index < minLength) {
            char firstChar = firstWord.charAt(index);
            char secondChar = secondWord.charAt(index);

            if (firstChar == secondChar) {
                createVertex(firstChar);
            } else {
                Vertex parentVertex = createVertex(firstChar);
                Vertex childVertex = createVertex(secondChar);
                parentVertex.addChild(childVertex);
                childVertex.increaseDepth();
                break;
            }

            index++;
        }

        int indexForSecondWord = index;

        // Create vertices for characters until the end of word_first  word.
        while (index < firstWordLength) {
            createVertex(firstWord.charAt(index));
            index++;
        }

        if (isSecondWordLast) {
            while (indexForSecondWord < secondWordLength) {
                createVertex(secondWord.charAt(indexForSecondWord));
                indexForSecondWord++;
            }
        }

    }

    // Auxiliary method, keep a map of already created vertices for given chars.
    // Only create new vertex and update map and list if char not seen before (not in map).
    private Vertex createVertex(char ch) {

        Vertex vertex;

        if (!mapCharToVertex.containsKey(ch)) {
            vertex = new Vertex(ch);
            mapCharToVertex.put(ch, vertex);
            vertices.add(vertex);
        } else {
            vertex = mapCharToVertex.get(ch);
        }

        return vertex;
    }

    // Getter method for testing.
    public LinkedList<Vertex> getListOfVertices() {
        return vertices;
    }

}
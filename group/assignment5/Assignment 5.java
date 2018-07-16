
import java.util.ArrayList;
import java.util.List;

public class Node {

    private char value;
    private List<Node> children;
    //keeping track of how many nodes this one depends on
    private int numOfParents; 

    public Node(char v) {
        value = v;
        children = new ArrayList<Node>();
    }

    public char getValue() {
        return value;
    }

    public void addChild(Node n) {
        children.add(n);
    }

    public void removeChild(Node n) {
        children.remove(n);
    }
    public void removeChildren() {
        children = new ArrayList<Node>();
    }

    public List<Node> getChildren() {
        return children;
    }

    public void incNumOfParents() {
        numOfParents++;
    }

    public void decNumOfParents() {
        numOfParents--;
    }

    public int getNumOfParents() {
        return numOfParents;
    }
}

public class A5Q1 {

    private static List<Character> buildOneWordSolution(String word) {
        //one word does not tell us anything about the order od chars
        //so we simply return all letters
        //using hashset so that there are no repetition of chars
        HashSet<Character> result = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            result.add(word.charAt(i));
        }
        LinkedList<Character> listResult = new LinkedList<>(result);
        return listResult;
    }

    private static void compareWords(String prevWord, String curWord, Node root, HashMap<Character, Node> visitedChars) {

        boolean differenceFound = false;

        int len = (prevWord.length() < curWord.length()) ? prevWord.length() : curWord.length();

        int i = 0;
        while (!differenceFound && (i < len)) {
            char prev = prevWord.charAt(i);
            char cur = curWord.charAt(i);
            //if the chars are not the same, we should add dependency to the graph
            if (prev != cur) {
                differenceFound = true;
                Node prevNode = null;
                Node curNode = null;
                //find parent node
                //if it is the first appearance of the char, make new node for it
                if (!visitedChars.containsKey(prev)) {
                    prevNode = new Node(prev);
                    root.addChild(prevNode);
                    prevNode.incNumOfParents();
                    visitedChars.put(prev, prevNode);
                } else {
                    prevNode = visitedChars.get(prev);
                }
                //find child node
                //if it is the first appearance of the char, make new node for it
                if (!visitedChars.containsKey(cur)) {
                    curNode = new Node(cur);
                    visitedChars.put(cur, curNode);
                } else {
                    curNode = visitedChars.get(cur);
                }
                //add dependency between nodes
                curNode.incNumOfParents();
                prevNode.addChild(curNode);
            } else {
                //if they are the same, make one Node instance so that the letter is in the alphabet
                //if one does not exsist already
                if (!visitedChars.containsKey(prev)) {
                    Node newNode = new Node(prev);
                    root.addChild(newNode);
                    newNode.incNumOfParents();
                    visitedChars.put(prev, newNode);
                }
            }
            i++;
        }
        //add remaining charachters of both string, so that the alphabet is complete
        for (int i1 = i; i1 < prevWord.length(); i1++) {
            char newChar = prevWord.charAt(i1);
            if (!visitedChars.containsKey(newChar)) {
                Node newNode = new Node(newChar);
                root.addChild(newNode);
                newNode.incNumOfParents();
                visitedChars.put(newChar, newNode);
            }
        }
        for (int i1 = i; i1 < curWord.length(); i1++) {
            char newChar = curWord.charAt(i1);
            if (!visitedChars.containsKey(newChar)) {
                Node newNode = new Node(newChar);
                root.addChild(newNode);
                newNode.incNumOfParents();
                visitedChars.put(newChar, newNode);
            }
        }
    }

    private static Node makeGraph(List<String> dictionary) {

        //base node
        Node root = new Node(' ');

        //using hashMap so that we have only one node for one char
        HashMap<Character, Node> visitedChars = new HashMap<Character, Node>();

        String prevWord;
        String curWord;

        
        prevWord = dictionary.get(0);
        int i = 1;
        while (i < dictionary.size()) {
            curWord = dictionary.get(i);
            compareWords(prevWord, curWord, root, visitedChars);
            prevWord = curWord;
            i++;
        }

        return root;
    }

    private static Node findNextNode(HashSet<Node> leftNodes) {

        //looking for a node without any parents
        Iterator<Node> iterator = leftNodes.iterator();
        while (iterator.hasNext()) {
            Node next = iterator.next();
            if (next.getNumOfParents() == 0) {
                return next;
            }
        }

        return null;
    }

    private static List<Character> topSort(Node root) {
        List<Character> charOrder = new LinkedList<>();
        HashSet<Node> leftNodes = new HashSet<Node>();
        List<Node> rootsChildren = root.getChildren();
        for (Node n : rootsChildren) {
            leftNodes.add(n);
            n.decNumOfParents();
        }
        root.removeChildren();
        Node nextNode = findNextNode(leftNodes);
        while (nextNode != null) {
            //delete found node from the waiting list
            leftNodes.remove(nextNode);
            //delete dependencies from the found node to his children
            List<Node> nextNodesChildren = nextNode.getChildren();
            for (Node n : nextNodesChildren) {
                leftNodes.add(n);
                n.decNumOfParents();
            }
            nextNode.removeChildren();
            charOrder.add(nextNode.getValue());
            nextNode = findNextNode(leftNodes);
        }
        if (leftNodes.size() > 0) {
            System.out.println("Cycle!");
            return null; //there is a cycle, could throw an exception
        }
        return charOrder;
    }

    public static List<Character> getAlphabet(List<String> dictionary) {
        if (dictionary == null) {
            return null;
        }
        List<Character> charOrder = null;
        if (dictionary.size() <= 1) {
            charOrder = buildOneWordSolution(dictionary.get(0));
            return charOrder;
        }

        Node root = makeGraph(dictionary);
        charOrder = topSort(root);
        return charOrder;
    }

    public static void testOneWordDictionary() {
        List<String> dictionary = new LinkedList<>();
        dictionary.add("apple");
        List<Character> result = getAlphabet(dictionary);
        System.out.println(result.toString());
        assertTrue(result.size() == 4); // {a p l e}
    }

    public static void testNullDictionary() {
        List<Character> result = getAlphabet(null);
        assertTrue(result == null);
    }

    public static void testCycle() {
        List<String> dictionary = new LinkedList<>();
        dictionary.add("as");
        dictionary.add("aa"); // s before a
        dictionary.add("sa"); // a before s
        List<Character> result = getAlphabet(dictionary);
        assertTrue(result == null);
    }

    public static void testRegualar() {
        List<String> dictionary = new LinkedList<>();
        dictionary.add("art");
        dictionary.add("rat");
        dictionary.add("cat");
        dictionary.add("car");
        List<Character> result = getAlphabet(dictionary);
        System.out.println(result.toString());
        assertTrue(result.indexOf('a') < result.indexOf('r')); 
        assertTrue(result.indexOf('a') < result.indexOf('c'));
        assertTrue(result.indexOf('r') < result.indexOf('c'));
        assertTrue(result.indexOf('t') < result.indexOf('r'));
    }

    public static void main(String[] args) {
        testNullDictionary();
        testOneWordDictionary();
        testCycle();
        testRegualar();
    }
}

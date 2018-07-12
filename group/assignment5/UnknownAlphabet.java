import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

// Helper class to represent the characters in the words and their relation.
// vertex1 is the parent of vertex2 if the char of vertex1 comes before the char of vertex2 in the alphabet.
// i.e if Abc < Bcd then vertex with char B is the child of vertex with char A.
// A vertex can be in 3 states for the topological visit:
// white = not yet visited; grey = visited, but not yet finished; black = finished.
class Vertex {

	final char value;
	Set<Vertex> children = new HashSet<Vertex>();
	String state = "white";

	Vertex(char value) {
		this.value = value;
	}

	public void addChild(Vertex child) {
		children.add(child);
	}

	public void setState(String state) {
		this.state = state;
	}

	public char getValue() {
		return value;
	}

	public Set<Vertex> getChildren() {
		return children;
	}

	public String getState() {
		return state;
	}

}

// Solution will create the list of vertices and do topological sort to find a valid order.
// During topological traversal, chars will be saved in reversed order in a stack reversed_alphabet.
// Used stack to have the correct order using the last-in-first-out property.
// Keep a list vertices of all the vertices created from the words in dictionary.
// Keep a map of every char to its vertex to check if a vertex was already created for that char.
public class UnknownAlphabet {

	Stack<Character> reversed_alphabet = new Stack<Character>();
	LinkedList<Vertex> vertices = new LinkedList<Vertex>();
	HashMap<Character, Vertex> char_vertex_map = new HashMap<Character, Vertex>();
	
	// Assume dictionary input is a List.
	public LinkedHashSet<Character> getAlphabet(List<String> dict) throws Exception {

		LinkedHashSet<Character> ordered_alphabet = new LinkedHashSet<Character>();

		if (dict.isEmpty())
			return ordered_alphabet;

		// Return all characters (no duplicates) if dictionary has only one word.
		if (dict.size() == 1) {
			String word = dict.get(0);
			for (int i = 0; i < word.length(); i++) {
				ordered_alphabet.add(word.charAt(i));
			}
			return ordered_alphabet;
		}

		createVertices(dict);
		topologicalSort();

		// Return the characters in the correct order by reversing the stack.
		while (!reversed_alphabet.isEmpty()) {
			ordered_alphabet.add(reversed_alphabet.pop());
		}

		return ordered_alphabet;
	}

	// Traverse verticies which have not been visited yet.
	private void topologicalSort() throws Exception {

		for (Vertex vertex : vertices) {
			if (vertex.getState().equals("white")) {
				sortFromVertex(vertex);
			}
		}
	}

	// For each unvisisted vertex, visit its children if they haven't been visited (white).
	// If during the visit of a child we discover it was started but not finished (grey),
	// then we have a cycle, so no order is possible.
	private void sortFromVertex(Vertex cur_vertex) throws Exception {

		cur_vertex.setState("grey"); // starting visit, but not finished
		for (Vertex child : cur_vertex.getChildren()) {
			if (child.getState().equals("white")) {
				sortFromVertex(child);
			} else if (child.getState().equals("grey")) {
				throw new Exception("There is a cycle, order impossible.");
			}
		}
		cur_vertex.setState("black"); // finished visit.
		reversed_alphabet.add(cur_vertex.getValue());
	}

	// Look at every 2 consecutive words to get the relation between the first 2 different characters.
	// For example, in [ABCD, ABDE, EFGH, FMNO]: C < D (from first 2 words), A < E (from 2nd and 3rd), E < F (from 3rd and 4th)
	// Because A < E and E < F then from transitivity => A < E without needing to check word 1 or 2 with word 4.
	public LinkedList<Vertex> createVertices(List<String> dict) {

		for (int i = 0; i < dict.size() - 1; i++) {
			String first = dict.get(i);
			String second = dict.get(i + 1);
			createVerticesHelper(first, second);
		}
		
		return vertices;
	}

	private void createVerticesHelper(String first, String second) {

		int length1 = first.length();
		int length2 = second.length();

		int i = 0;
		int j = 0;

		// Iterate through first and second word until they have a different character.
		// For each new character, create its vertex and add it to the list using createVertex.
		while (i < length1 && j < length2) {
			char char1 = first.charAt(i);
			char char2 = second.charAt(j);

			if (char1 == char2) {
				createVertex(char1);
			}

			else {
				Vertex vertex1 = createVertex(char1);
				Vertex vertex2 = createVertex(char2);
				vertex1.addChild(vertex2);
				break;
			}

			i++;
			j++;
		}

		// Create vertices for characters until the end of first and second words.
		while (i < length1) {
			createVertex(first.charAt(i));
			i++;
		}

		while (j < length2) {
			createVertex(second.charAt(j));
			j++;
		}
	}
	
	// Auxiliary method, keep a map of already created vertices for given chars.
	// Only create new vertex and update map and list if char not seen before (not in map).
	private Vertex createVertex(char ch) {
		
		Vertex vertex;

		if (!char_vertex_map.containsKey(ch)) {
			vertex = new Vertex(ch);
			char_vertex_map.put(ch, vertex);
			vertices.add(vertex);
		} else {
			vertex = char_vertex_map.get(ch);
		}

		return vertex;
	}

}

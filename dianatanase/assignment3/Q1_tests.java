import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;


public class Q1_tests {

	// Set up the grid.
	public char[][] gridSetUp(){
		char[][] grid = new char[][] {
				  {'a','a','r'},
				  {'t','c','d'}
				};
		
		return grid;
	}
		
	// Set up a dictionary with a few words.
	public Dictionary simpleDictionarySetUp(){
		Dictionary dict = new Dictionary();
		dict.addListOfWords(Arrays.asList("CAR", "CARD", "CART", "CAT", "c"));
		
		return dict;
	}
	
	// Set up a dictionary with more words.
	public Dictionary complexDictionarySetUp(){
		Dictionary dict = new Dictionary();
		dict.addListOfWords(Arrays.asList("CAR", "CARD", "CAT"));
		dict.addListOfWords(Arrays.asList(
				"dar", "aarct", "ata", "aar", "arc", "rdat", "rctaa", "racat", "draact"));
		// Add words in dictionary that cannot be formed in the grid
		dict.addListOfWords(Arrays.asList("CART", "ATRD", "art", "rtac"));
		
		return dict;
	}
	
	// Test the children are added correctly for each vertex in the Graph.
	@Test
	public void testGraphCreateVertices(){
		
		char[][] grid = gridSetUp();
		Graph graph = new Graph(2,3);
		Vertex[][] vertices = graph.createVertices(grid);
		
		assertEquals(new HashSet<Vertex>(vertices[0][0].getChildren()), new HashSet<Vertex>(
				Arrays.asList(vertices[1][0],vertices[1][1], vertices[0][1])));
		assertEquals(new HashSet<Vertex>(vertices[0][2].getChildren()), new HashSet<Vertex>(
				Arrays.asList(vertices[1][2],vertices[1][1], vertices[0][1])));
		assertEquals(new HashSet<Vertex>(vertices[1][0].getChildren()), new HashSet<Vertex>(
				Arrays.asList(vertices[0][0],vertices[0][1], vertices[1][1])));
		assertEquals(new HashSet<Vertex>(vertices[1][2].getChildren()), new HashSet<Vertex>(
				Arrays.asList(vertices[0][2],vertices[0][1], vertices[1][1])));
		assertEquals(new HashSet<Vertex>(vertices[0][1].getChildren()), new HashSet<Vertex>(
				Arrays.asList(vertices[1][1], vertices[1][0], vertices[1][2], vertices[0][0], vertices[0][2])));
		assertEquals(new HashSet<Vertex>(vertices[1][1].getChildren()), new HashSet<Vertex>(
				Arrays.asList(vertices[0][1], vertices[0][0], vertices[0][2], vertices[1][0], vertices[1][2])));		
	}

	@Test
	public void testCorrectFoundWordsSimpleDictionay() throws Exception {
		
		char[][] grid = gridSetUp();
		Dictionary dict = simpleDictionarySetUp();
		
		HashSet<String> words = Q1.findWords(grid,dict,2,3);
		assertEquals(words, new HashSet<String>(Arrays.asList("car","cat","card","c")));
	}
	
	@Test
	public void testCorrectFoundWordsComplexDictionary() throws Exception {
		
		char[][] grid = gridSetUp();
		Dictionary dict = complexDictionarySetUp();
		
		HashSet<String> words = Q1.findWords(grid,dict,2,3);
		assertEquals(words, new HashSet<String>(Arrays.asList(
				"car","cat","card", "dar", "aar", "aarct",
				"ata", "arc", "rctaa", "racat", "rdat", "draact")));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidLineColumn() throws Exception {
		
		char[][] grid = gridSetUp();
		Dictionary dict = simpleDictionarySetUp();
		
		Q1.findWords(grid,dict,-2,3);
	}	
	
}

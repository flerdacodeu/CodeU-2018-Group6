import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

import org.junit.Test;


public class UnknownAlphabet_test {
	
	@Test
	public void testCreateVertices() throws Exception{
		UnknownAlphabet alphabet = new UnknownAlphabet();
		LinkedList<Vertex> vertices = alphabet.createVertices(Arrays.asList("ART", "RAT", "CAT", "CAR"));
		
		assertEquals(vertices.size(), 4);
		
		assertEquals(vertices.get(0).getValue(), 'A');
		assertEquals(vertices.get(0).getChildren().size(), 1);
		
		assertEquals(vertices.get(1).getValue(), 'R');
		assertEquals(vertices.get(1).getChildren().size(), 1);
		
		assertEquals(vertices.get(2).getValue(), 'T');
		assertEquals(vertices.get(2).getChildren().size(), 1);
		
		assertEquals(vertices.get(3).getValue(), 'C');
		assertEquals(vertices.get(3).getChildren().size(), 0);		
	}
	
	@Test(expected = Exception.class)
	public void testCycleSmallExample() throws Exception{
		UnknownAlphabet alphabet = new UnknownAlphabet();
		alphabet.getAlphabet(Arrays.asList("abc","acd","abd"));
	}
	
	@Test(expected = Exception.class)
	public void testCycleBiggerExample() throws Exception{
		UnknownAlphabet alphabet = new UnknownAlphabet();
		// ... calm < ... < cute < cat ...
		alphabet.getAlphabet(Arrays.asList("bear","better","calm","clear",
				"club","cluster", "cute", "cat", "ready","rear"));
	}
	
	
	@Test
	public void testNoWord() throws Exception {
		UnknownAlphabet alphabet = new UnknownAlphabet();
		assertEquals(alphabet.getAlphabet(new ArrayList<String>()).size(),0);
	}
	
	@Test
	public void testOneWord() throws Exception {
		UnknownAlphabet alphabet = new UnknownAlphabet();
		LinkedHashSet<Character> correctResult = new LinkedHashSet<Character>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F','G'));
		assertEquals(alphabet.getAlphabet(Arrays.asList("ABCDEFG")), correctResult);
		assertEquals(alphabet.getAlphabet(Arrays.asList("ABACDBEFEGGG")), correctResult);
	}

	@Test
	public void testSmallExample() throws Exception {
		UnknownAlphabet alphabet = new UnknownAlphabet();
		LinkedHashSet<Character> result = alphabet.getAlphabet(Arrays.asList("ART", "RAT", "CAT", "CAR"));
		
		System.out.println(result.toString()); // [T, A, R, C]
		
		List<Object> arrayResult = Arrays.asList(result.toArray());
		assertTrue(arrayResult.indexOf('A') < arrayResult.indexOf('R'));
		assertTrue(arrayResult.indexOf('R') < arrayResult.indexOf('C'));
		assertTrue(arrayResult.indexOf('T') < arrayResult.indexOf('R'));
	}
	
	@Test
	public void testBiggerExample() throws Exception {
		UnknownAlphabet alphabet = new UnknownAlphabet();
		LinkedHashSet<Character> result = alphabet.getAlphabet(Arrays.asList("bear","better","calm","clear",
				"club","cluster", "cute", "ready","rear"));
		
		System.out.println(result.toString()); // [y, d, m, a, l, t, e, u, b, s, c, r]
		
		List<Object> arrayResult = Arrays.asList(result.toArray());
		assertTrue(arrayResult.indexOf('a') < arrayResult.indexOf('t')); // beAr < beTter
		assertTrue(arrayResult.indexOf('b') < arrayResult.indexOf('c')); // Better < Calm
		assertTrue(arrayResult.indexOf('a') < arrayResult.indexOf('l')); // cAlm < cLear
		assertTrue(arrayResult.indexOf('e') < arrayResult.indexOf('u')); // clEar < clUb
		assertTrue(arrayResult.indexOf('b') < arrayResult.indexOf('s')); // cluB < cluSter
		assertTrue(arrayResult.indexOf('l') < arrayResult.indexOf('u')); // cLuster < cUter
		assertTrue(arrayResult.indexOf('a') < arrayResult.indexOf('u')); // cAlm < ... < cUte
		assertTrue(arrayResult.indexOf('c') < arrayResult.indexOf('r')); // Cute < Ready
		assertTrue(arrayResult.indexOf('b') < arrayResult.indexOf('r')); // Better < .. < Ready
		assertTrue(arrayResult.indexOf('d') < arrayResult.indexOf('r')); // reaDy < reaR
	}
	
}
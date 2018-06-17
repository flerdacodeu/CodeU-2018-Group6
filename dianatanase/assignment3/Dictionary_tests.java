import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;


public class Dictionary_tests {
	
	// Create dictionary and add words to it.
	public Dictionary setUp(){
		Dictionary dict = new Dictionary();
		dict.addListOfWords(Arrays.asList("CAR", "CARD", "CART", "CAT"));
		
		return dict;
	}

	@Test
	public void testIsWord() {
		Dictionary dict = setUp();
		
		// Test isWord method, case insensitive.
		assertTrue(dict.isWord("CAR"));
		assertTrue(dict.isWord("CARD"));
		assertTrue(dict.isWord("cArT"));
		assertTrue(dict.isWord("cat"));
		
		// Test isWord with Strings not in dictionary.
		assertFalse(dict.isWord("cta"));
		assertFalse(dict.isWord("word"));
		assertFalse(dict.isWord("OTHER"));
	}
	
	public void testIsPrefix(){
		Dictionary dict = setUp();
			
		// Test isPrefix method, case insensitive.
		assertTrue(dict.isPrefix("C"));
		assertTrue(dict.isPrefix("ca"));
		assertTrue(dict.isPrefix("Car"));
		assertTrue(dict.isPrefix("cARd"));
		assertTrue(dict.isPrefix("CART"));
		assertTrue(dict.isPrefix("cat"));
		
		// Test isPrefix with Strings that are not prefixes.
		assertFalse(dict.isPrefix("ct"));
		assertFalse(dict.isPrefix("CRT"));
		assertFalse(dict.isPrefix("ard"));
	}

}

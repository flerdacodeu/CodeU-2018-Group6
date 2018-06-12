package assignment3;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DictionaryTest {
    private Dictionary simpleDictionary;

    @Before
    public void setUp() {
        simpleDictionary = new Dictionary();
        simpleDictionary.addWord("CAR");
        simpleDictionary.addWord("CARD");
        simpleDictionary.addWord("CART");
        simpleDictionary.addWord("CAT");
    }

    @Test
    public void testValidWords() {
        Assert.assertTrue(simpleDictionary.isWord("CARD"));
        Assert.assertTrue(simpleDictionary.isWord("CART"));
        Assert.assertTrue(simpleDictionary.isWord("CAT"));
        Assert.assertTrue(simpleDictionary.isWord("CAR")); //prefix of another word and also a word
    }

    @Test
    public void testInvalidWords() {
        Assert.assertFalse(simpleDictionary.isWord("CARP")); //not in dictionary
        Assert.assertFalse(simpleDictionary.isWord("CA")); //prefix but not word
    }

    @Test
    public void testValidPrefix() {
        Assert.assertTrue(simpleDictionary.isPrefix("C"));
        Assert.assertTrue(simpleDictionary.isPrefix("CA"));
        Assert.assertTrue(simpleDictionary.isPrefix("CAR")); //coplete word and also a prefix
        Assert.assertTrue(simpleDictionary.isPrefix("CARD")); //complete word
        Assert.assertTrue(simpleDictionary.isPrefix("CART"));
        Assert.assertTrue(simpleDictionary.isPrefix("CAT"));
    }

    @Test
    public void testInvalidPrefix() {
        Assert.assertFalse(simpleDictionary.isPrefix("X")); //first letter not in dictionary
        Assert.assertFalse(simpleDictionary.isPrefix("CAP")); //not a prefix
    }

}

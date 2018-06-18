/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import assignment3.Dictionary;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ana
 */
public class DictionaryTest {
    private Dictionary testDictionary;
    
    @Before
    public void setUp() {
        testDictionary = new Dictionary();
        testDictionary.addWord("CAR");
        testDictionary.addWord("CARD");
        testDictionary.addWord("CART");
        testDictionary.addWord("CAT");
    }
    
    @Test
    public void testWordsInDictionary() throws Exception {
        Assert.assertEquals(true, testDictionary.isWord("CAR"));
        Assert.assertEquals(true, testDictionary.isWord("CARD"));
        Assert.assertEquals(true, testDictionary.isWord("CART"));
        Assert.assertEquals(true, testDictionary.isWord("CAT"));
    }
    
    @Test
    public void testPrefixesInDictionary() throws Exception {
        Assert.assertEquals(true, testDictionary.isPrefix("C"));
        Assert.assertEquals(true, testDictionary.isPrefix("CA"));
        Assert.assertEquals(true, testDictionary.isPrefix("CAR"));
        Assert.assertEquals(true, testDictionary.isPrefix("CARD"));
        Assert.assertEquals(true, testDictionary.isPrefix("CART"));
        Assert.assertEquals(true, testDictionary.isPrefix("CAT"));
    }
    
    @Test
    public void testWordsNotInDictionary() throws Exception {
        Assert.assertEquals(false, testDictionary.isWord("Harry"));
        Assert.assertEquals(false, testDictionary.isWord("Hermione"));
        Assert.assertEquals(false, testDictionary.isWord("Hogwarts"));
        Assert.assertEquals(false, testDictionary.isWord("Hogsmeade"));
        Assert.assertEquals(false, testDictionary.isWord("Hufflepuff"));
    }
    
    @Test
    public void testPrefixesNotInDictionary() throws Exception {
        Assert.assertEquals(false, testDictionary.isPrefix("H"));
        Assert.assertEquals(false, testDictionary.isPrefix("HA"));
        Assert.assertEquals(false, testDictionary.isPrefix("HE"));
        Assert.assertEquals(false, testDictionary.isPrefix("HOG"));
        Assert.assertEquals(false, testDictionary.isPrefix("HUFF"));
    }
}

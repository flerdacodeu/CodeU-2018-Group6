import assignment5.Assignment5;
import java.util.Arrays;
import java.util.LinkedList;
import org.junit.Assert;
import org.junit.Test;

public class Assignment5Test {
    private final Assignment5 alphabetCreator = new Assignment5();
    
    @Test
    public void noWordsInDictionaryTest() {
        Assert.assertEquals(0, alphabetCreator.findAllAphabetsOfLanguage(new LinkedList<>()).size());
    }
    
    @Test
    public void oneWordInDictionaryTest() {
        Assert.assertEquals(24, alphabetCreator.findAllAphabetsOfLanguage(Arrays.asList("BETTER")).size());
    }

    @Test
    public void onePossibleAlphabetTest() {
        LinkedList<LinkedList<Character>> allPossibleAlphabets = alphabetCreator.findAllAphabetsOfLanguage(Arrays.asList("ART", "RAT", "CAT", "CAR", "CTA"));
        Assert.assertEquals(1, allPossibleAlphabets.size());
        LinkedList<Character> onlyAlphabet = allPossibleAlphabets.get(0);
        Assert.assertTrue(onlyAlphabet.indexOf('A') < onlyAlphabet.indexOf('R'));
        Assert.assertTrue(onlyAlphabet.indexOf('A') < onlyAlphabet.indexOf('T'));
        Assert.assertTrue(onlyAlphabet.indexOf('R') < onlyAlphabet.indexOf('C'));
        Assert.assertTrue(onlyAlphabet.indexOf('T') < onlyAlphabet.indexOf('R'));
        
    }
    
    @Test
    public void twoPossibleAlphabetsTest() {
        LinkedList<LinkedList<Character>> allPossibleAlphabets = alphabetCreator.findAllAphabetsOfLanguage(Arrays.asList("ART", "RAT", "CAT", "CAR"));
        Assert.assertEquals(2, allPossibleAlphabets.size());
        for (int i = 0; i < allPossibleAlphabets.size(); i++) {
            LinkedList<Character> currentAlphabet = allPossibleAlphabets.get(i);
            Assert.assertTrue(currentAlphabet.indexOf('A') < currentAlphabet.indexOf('R'));
            Assert.assertTrue(currentAlphabet.indexOf('R') < currentAlphabet.indexOf('C'));
            Assert.assertTrue(currentAlphabet.indexOf('T') < currentAlphabet.indexOf('R'));
        }
    }
    
    @Test
    public void manyPossibleAlphabetsTest() {
        LinkedList<LinkedList<Character>> allPossibleAlphabets = alphabetCreator.findAllAphabetsOfLanguage(Arrays.asList("54", "50", "24", "21", "35", "30", "15"));
        Assert.assertEquals(13, allPossibleAlphabets.size());
        for (int i = 0; i < allPossibleAlphabets.size(); i++) {
            LinkedList<Character> currentAlphabet = allPossibleAlphabets.get(i);
            Assert.assertTrue(currentAlphabet.indexOf('4') < currentAlphabet.indexOf('0'));
            Assert.assertTrue(currentAlphabet.indexOf('5') < currentAlphabet.indexOf('2'));
            Assert.assertTrue(currentAlphabet.indexOf('4') < currentAlphabet.indexOf('1'));
            Assert.assertTrue(currentAlphabet.indexOf('2') < currentAlphabet.indexOf('3'));
            Assert.assertTrue(currentAlphabet.indexOf('5') < currentAlphabet.indexOf('0'));
            Assert.assertTrue(currentAlphabet.indexOf('3') < currentAlphabet.indexOf('1'));
        }
    }
    
    @Test // long test, takes between 11s to 14s for me, but finds 1.782.660 possible alphabets 
    public void aWholeLotOfPossibleAlphabetsTest() {
        long millisStart = System.currentTimeMillis();
        LinkedList<LinkedList<Character>> allPossibleAlphabets = alphabetCreator.findAllAphabetsOfLanguage(Arrays.asList("BEAR", "BETTER", "CALM", "CLEAR", "CLUB", "CLUE", "CLUSTER", "CUTE", "READY", "REAR"));
        long millisEnd = System.currentTimeMillis();
        Assert.assertTrue(allPossibleAlphabets.size() > 1000000);
        Assert.assertTrue((millisEnd - millisStart) / 1000  < 15);
    }
}

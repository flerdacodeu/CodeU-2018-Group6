
import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class UnknownAlphabetTest {

    @Test
    public void testCreateVertices() throws Exception {
        UnknownAlphabet alphabet = new UnknownAlphabet();
        alphabet.createVertices(Arrays.asList("ART", "RAT", "CAT", "CAR"));
        LinkedList<Vertex> vertices = alphabet.getListOfVertices();

        assertEquals(vertices.size(), 4);

        // need to add explicit boxing, otherwise an ambiguous call
        assertEquals(vertices.get(0).getValue(), (Character) 'A');
        assertEquals(vertices.get(0).getChildren().size(), 1);

        assertEquals(vertices.get(1).getValue(), (Character) 'R');
        assertEquals(vertices.get(1).getChildren().size(), 1);

        assertEquals(vertices.get(2).getValue(), (Character) 'T');
        assertEquals(vertices.get(2).getChildren().size(), 1);

        assertEquals(vertices.get(3).getValue(), (Character) 'C');
        assertEquals(vertices.get(3).getChildren().size(), 0);
    }

    @Test(expected = Exception.class)
    public void testCycleSmallExample() throws Exception {
        UnknownAlphabet alphabet = new UnknownAlphabet();
        alphabet.getAlphabet(Arrays.asList("abc", "acd", "abd"));
    }

    @Test(expected = Exception.class)
    public void testCycleBiggerExample() throws Exception {
        UnknownAlphabet alphabet = new UnknownAlphabet();
        // ... calm < ... < cute < cat ...
        alphabet.getAlphabet(Arrays.asList("bear", "better", "calm", "clear",
                "club", "cluster", "cute", "cat", "ready", "rear"));
    }

    @Test
    public void testNoWord() throws Exception {
        UnknownAlphabet alphabet = new UnknownAlphabet();
        assertEquals(alphabet.getAlphabet(new ArrayList<String>()).size(), 0);
    }

    @Test
    public void testOneWord() throws Exception {
        UnknownAlphabet alphabet = new UnknownAlphabet();
        LinkedList<Character> correctResult = new LinkedList<Character>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G'));
        assertEquals(alphabet.getAlphabet(Arrays.asList("ABCDEFG")), correctResult);
        assertEquals(alphabet.getAlphabet(Arrays.asList("ABACDBEFEGGG")), correctResult);
    }

    @Test
    public void testSmallExample() throws Exception {
        UnknownAlphabet alphabet = new UnknownAlphabet();
        LinkedList<Character> result = alphabet.getAlphabet(Arrays.asList("ART", "RAT", "CAT", "CAR"));

        System.out.println(result.toString()); // [T, A, R, C]

        assertTrue(result.indexOf('A') < result.indexOf('R'));
        assertTrue(result.indexOf('R') < result.indexOf('C'));
        assertTrue(result.indexOf('T') < result.indexOf('R'));
    }

    @Test
    public void testBiggerExample() throws Exception {
        UnknownAlphabet alphabet = new UnknownAlphabet();
        LinkedList<Character> result = alphabet.getAlphabet(Arrays.asList("bear", "better", "calm", "clear",
                "club", "cluster", "cute", "ready", "rear"));

        System.out.println(result.toString()); // [y, d, m, a, t, l, e, u, b, s, c, r]

        assertTrue(result.indexOf('a') < result.indexOf('t')); // beAr < beTter
        assertTrue(result.indexOf('b') < result.indexOf('c')); // Better < Calm
        assertTrue(result.indexOf('a') < result.indexOf('l')); // cAlm < cLear
        assertTrue(result.indexOf('e') < result.indexOf('u')); // clEar < clUb
        assertTrue(result.indexOf('b') < result.indexOf('s')); // cluB < cluSter
        assertTrue(result.indexOf('l') < result.indexOf('u')); // cLuster < cUter
        assertTrue(result.indexOf('a') < result.indexOf('u')); // cAlm < ... < cUte
        assertTrue(result.indexOf('c') < result.indexOf('r')); // Cute < Ready
        assertTrue(result.indexOf('b') < result.indexOf('r')); // Better < .. < Ready
        assertTrue(result.indexOf('d') < result.indexOf('r')); // reaDy < reaR
    }

    @Test
    public void onePossibleAlphabetTest() throws Exception {
        UnknownAlphabet alphabet = new UnknownAlphabet();
        LinkedList<LinkedList<Character>> allPossibleAlphabets = alphabet.getAllAlphabets(Arrays.asList("ART", "RAT", "CAT", "CAR", "CTA"));
        assertEquals(1, allPossibleAlphabets.size());
        LinkedList<Character> onlyAlphabet = allPossibleAlphabets.get(0);
        assertTrue(onlyAlphabet.indexOf('A') < onlyAlphabet.indexOf('R'));
        assertTrue(onlyAlphabet.indexOf('A') < onlyAlphabet.indexOf('T'));
        assertTrue(onlyAlphabet.indexOf('R') < onlyAlphabet.indexOf('C'));
        assertTrue(onlyAlphabet.indexOf('T') < onlyAlphabet.indexOf('R'));

    }

    @Test
    public void twoPossibleAlphabetsTest() throws Exception {
        UnknownAlphabet alphabet = new UnknownAlphabet();
        LinkedList<LinkedList<Character>> allPossibleAlphabets = alphabet.getAllAlphabets(Arrays.asList("ART", "RAT", "CAT", "CAR"));
        assertEquals(2, allPossibleAlphabets.size());
        for (int i = 0; i < allPossibleAlphabets.size(); i++) {
            LinkedList<Character> currentAlphabet = allPossibleAlphabets.get(i);
            assertTrue(currentAlphabet.indexOf('A') < currentAlphabet.indexOf('R'));
            assertTrue(currentAlphabet.indexOf('R') < currentAlphabet.indexOf('C'));
            assertTrue(currentAlphabet.indexOf('T') < currentAlphabet.indexOf('R'));
        }
    }

    @Test
    public void manyPossibleAlphabetsTest() throws Exception {
        UnknownAlphabet alphabet = new UnknownAlphabet();
        LinkedList<LinkedList<Character>> allPossibleAlphabets = alphabet.getAllAlphabets(Arrays.asList("54", "50", "24", "21", "35", "30", "15"));
        assertEquals(13, allPossibleAlphabets.size());
        for (int i = 0; i < allPossibleAlphabets.size(); i++) {
            LinkedList<Character> currentAlphabet = allPossibleAlphabets.get(i);
            assertTrue(currentAlphabet.indexOf('4') < currentAlphabet.indexOf('0'));
            assertTrue(currentAlphabet.indexOf('5') < currentAlphabet.indexOf('2'));
            assertTrue(currentAlphabet.indexOf('4') < currentAlphabet.indexOf('1'));
            assertTrue(currentAlphabet.indexOf('2') < currentAlphabet.indexOf('3'));
            assertTrue(currentAlphabet.indexOf('5') < currentAlphabet.indexOf('0'));
            assertTrue(currentAlphabet.indexOf('3') < currentAlphabet.indexOf('1'));
        }
    }

    @Test // long test, takes between 11s to 14s for me, but finds 1.782.660 possible alphabets 
    public void aWholeLotOfPossibleAlphabetsTest() throws Exception {
        UnknownAlphabet alphabet = new UnknownAlphabet();
        long millisStart = System.currentTimeMillis();
        LinkedList<LinkedList<Character>> allPossibleAlphabets = alphabet.getAllAlphabets(Arrays.asList("BEAR", "BETTER", "CALM", "CLEAR", "CLUB", "CLUE", "CLUSTER", "CUTE", "READY", "REAR"));
        long millisEnd = System.currentTimeMillis();
        assertTrue(allPossibleAlphabets.size() > 1000000);
        assertTrue((millisEnd - millisStart) / 1000 < 15);
    }

}
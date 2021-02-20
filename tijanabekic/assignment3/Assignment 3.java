//simple dictionary for test purpose, not the follow up solution
public class Dictionary {

    private static LinkedList<String> words;
    private static LinkedList<String> prefixes;

    public Dictionary() {
        prefixes = new LinkedList<>();
        prefixes.add("CARD");
        prefixes.add("CART");
        prefixes.add("CAR");
        prefixes.add("CAT");
        prefixes.add("CA");
        prefixes.add("C");

        words = new LinkedList<>();
        words.add("CARD");
        words.add("CART");
        words.add("CAR");
        words.add("CAT");
    }

    public static boolean isPrefix(String word) {
        for (String s : prefixes) {
            if (word.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isWord(String word) {

        for (String s : words) {
            if (word.equals(s)) {
                return true;
            }
        }
        return false;
    }
    
    public void addWord(String word) {
        words.add(word);
        
    }

}


public class A3Q1 {
    public static Dictionary dictionary;
    public static void checkWord(char[][] grid, int i, int j, String start, int m, int n, HashMap<String, Boolean> map, HashSet<String> set) {
        
        //check if out of bounds
        if (i < 0 || j < 0 || i >= m || j >= n) {
            return;
        }
        //make the string for checking
        start =  start + grid[i][j];
        
        //add to map if the word is not there
        if (!map.containsKey(start)) {
            map.put(start, dictionary.isPrefix(start));
        }
        //if the string is not a prefix, it can never form a word
        if (map.get(start) == false) {
            return;
        }
        //add to result if it is a word
        if (dictionary.isWord(start)) {
            //by using a set we can eliminate duplicats
            if (!set.contains(start)){
                set.add(start);
            }
        }
        //check if it is possible to make a word in any direction
       
        checkWord(grid, i - 1, j - 1, start, m, n, map, set);
        checkWord(grid, i - 1, j, start, m, n, map, set);
        checkWord(grid, i - 1, j + 1, start, m, n, map, set);
        checkWord(grid, i, j - 1, start, m, n, map, set);
        checkWord(grid, i, j + 1, start, m, n, map, set);
        checkWord(grid, i + 1, j - 1, start, m, n, map, set);
        checkWord(grid, i + 1, j, start, m, n, map, set);
        checkWord(grid, i + 1, j + 1, start, m, n, map, set);

        return;
    }

    public static List<String> findWords(char[][] grid) {
        
        if (grid == null) {
            return null;
        }
        int m = grid.length;
        int n = grid[0].length;
        //store all the results in a list
        LinkedList<String> result = new LinkedList<String>();

        //make a map so there is no need to double check strings
        HashMap<String, Boolean> map = new HashMap<String, Boolean>();
        HashSet<String> set = new HashSet<String>();
        //start at any point
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String start = "";
                checkWord(grid, i, j, start, m, n, map, set);
            }
        }
        
        Iterator iter = set.iterator();
        while (iter.hasNext()) {
            result.add((String) iter.next());
        }
        return result;
    }

    public static void testRegular() {
        int m = 2;
        int n = 3;
        char[][] grid = new char[m][n];
        grid[0][0] = 'A'; grid[0][1] = 'A'; grid[0][2] = 'R';
        grid[1][0] = 'T'; grid[1][1] = 'C'; grid[1][2] = 'D';
        List<String> result = findWords(grid);
        if (result.size() == 3) {
            System.out.println("Passed regular");
        } else {
            System.out.println("Failed regular");
        }
        return;
    }

    public static void testNoWords() {
        int m = 2;
        int n = 3;
        char[][] grid = new char[m][n];
        grid[0][0] = 'A'; grid[0][1] = 'A'; grid[0][2] = 'A';
        grid[1][0] = 'A'; grid[1][1] = 'A'; grid[1][2] = 'A';
        List<String> result = findWords(grid);
        if (result.size() == 0) {
            System.out.println("Passed no words");
        } else {
            System.out.println("Failed no words");
        }
        return;
    }

    public static void testNullGrid() {
        int m = 3;
        int n = 2;
        char[][] grid = null;
        List<String> result = findWords(grid);
        if (result == null) {
            System.out.println("Passed null grid");
        } else {
            System.out.println("Failed null grid");
        }
        return;
    }

    public static void main(String[] args) {
        dictionary = new Dictionary();
        testRegular();       
        testNoWords();
        testNullGrid();
    }
}
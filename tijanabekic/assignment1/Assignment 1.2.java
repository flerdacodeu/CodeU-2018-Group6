/*Q1. Given two strings, determine if one is an anagram of the other. Two words are anagrams of
each other if they are made of the same letters in a different order

Follow-ups:
1. Make the algorithm able to handle both case sensitive and case insensitive anagrams.
2. Make the algorithm able to handle anagrams of sentences, where each word in the resulting
sentence is an anagram of one of the words in the original sentence
*/
class WordInfo {
	private int count = 0;
	private int length = 0;
	public WordInfo(int cnt, int len) {
		count = cnt;
		length = len;
	}
	public void setCount(int cnt) {
		count = cnt;
	}
	public int getCount() {
		return count;
	}
	public int getLength() {
		return length;
	}
}

public class A1Q1 {

    static boolean isAnagram(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false; // alternative: throw new IllegalArgumentException("s1/s2 was not set.");
        }
        if (s1.length() != s2.length()) {
            return false; // different char count
        }
        // count number of appearances of each char in s1
        int[] charCount = new int[256]; // ASCII
        for (int i = 0; i < s1.length(); i++) {
            charCount[s1.charAt(i)]++;
        }

        // check if the number of appearances of each char in s2 matches
        for (int i = 0; i < s2.length(); i++) {
            if (charCount[s2.charAt(i)] <= 0) {
                return false; // char s2.charAt(i) appears more times in s2
            }
            charCount[s2.charAt(i)]--;
        }
        return true;
    }

    static boolean isAnagramCaseInsensitive(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false; // alternative: throw new IllegalArgumentException("s1/s2 was not set.");
        }
        if (s1.length() != s2.length()) {
            return false; // different char count
        }
        s1.toLowerCase();
        s2.toLowerCase();
        // count number of appearances of each char in s1
        int[] charCount = new int[256]; // ASCII
        for (int i = 0; i < s1.length(); i++) {
            charCount[s1.charAt(i)]++;
        }

        // check if the number of appearances of each char in s2 matches
        for (int i = 0; i < s2.length(); i++) {
            if (charCount[s2.charAt(i)] <= 0) {
                return false; // char s2.charAt(i) appears more times in s2
            }
            charCount[s2.charAt(i)]--;
        }
        return true;
    }

    static boolean isAnagramSentence(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false; // alternative: throw new IllegalArgumentException("s1/s2 was not set.");
        }

        // leave only letters
        String s1new = s1.replaceAll("[^A-Za-z ]", " ");
        String s2new = s2.replaceAll("[^A-Za-z ]", " ");
        // case insensitive
        s1new.toLowerCase();
        s1new.toLowerCase();

        // find all words in the first sentence
        HashMap<String, WordInfo> map = new HashMap<String, WordInfo>();
        String[] words1 = s1new.split(" ");

        for (String currWord : words1) {
            if (map.containsKey(currWord)) {
                WordInfo currWordInfo = map.get(currWord);
                currWordInfo.setCount(currWordInfo.getCount() + 1);
                map.put(currWord, currWordInfo);
            } else {
                WordInfo currWordInfo = new WordInfo(1, currWord.length());
                map.put(currWord, currWordInfo);
            }
        }

        // go through all words in the second sentence 
        String[] words2 = s2new.split(" ");

        for (String currWord : words2) {
            boolean found = false;
            for (String checkWord : map.keySet()) {
                WordInfo checkWordInfo = map.get(checkWord);
                if (checkWordInfo.getLength() == currWord.length()) {
                    if (isAnagram(currWord, checkWord)) {
                        if (checkWordInfo.getCount() == 1) {
                            map.remove(checkWord); // last apperance of the found word
                        } else {
                            checkWordInfo.setCount(checkWordInfo.getCount() - 1);
                            map.put(checkWord, checkWordInfo);
                        }
                        found = true;
                        break;
                    }
                }
            }
            if (found == false) {
                return false; // anagram of current word is not found
            }
        }
        return true;
    }

    public static void main(String[] arg) {
        String s1, s2;
        s1 = "sianna si";
        s2 = "nnaais is";
        boolean testing = false;
        testing = A1Q1.isAnagramSentence(s1, s2);
        System.out.println("Sentence 1: " + s1);
        System.out.println("Sentence 2: " + s2);
        System.out.println(testing);

        s1 = "are, you.. ok+";
        s2 = "ouy are ko";
        testing = A1Q1.isAnagramSentence(s1, s2);
        System.out.println("Sentence 1: " + s1);
        System.out.println("Sentence 2: " + s2);
        System.out.println(testing);

        s1 = null;
        testing = A1Q1.isAnagramSentence(s1, s2);
        System.out.println("Sentence 1: " + s1);
        System.out.println("Sentence 2: " + s2);
        System.out.println(testing);
    }
}




//Q2. Implement an algorithm to find the kth to last element of a singly linked list.

public class A1Q2 {
    static Node findKth(Node head, int k) {
	if (k < 0)
		return null; // exception, alternative : throw new Exception();
	if (head == null) 
		return null; // there are less then k nodes, alternative : throw new Exception();
	Node helper = head;
	Node result = head;

	// move helper k nodes
	for (int i = 0; i < k; i++) {
		if (helper == null)
			return null; // there are less then k nodes, alternative : throw new Exception();
		helper = helper.next;
	}

	// helper is k nodes ahead, so when it hits the end, the result is at (n - k) node
	while (helper != null){
		helper = helper.next;
		result = result.next;
	}

	return result;
    }
    public static void main(String[]args){
        Node head = new Node(1, null);
        head.next = new Node(2, null);
        head.next.next = new Node(3, null);
        head.next.next.next = new Node(4, null);
        head.next.next.next.next = new Node(5, null);
        
        Node ret = null;
        ret = A1Q2.findKth(head, 0);
        if (ret != null){
            System.out.println(ret.data);
        } else {
            System.out.println("null");
        }
        ret = A1Q2.findKth(head, 5);
        if (ret != null){
            System.out.println(ret.data);
        } else {
            System.out.println("null");
        }
        ret = A1Q2.findKth(head, 6);
        if (ret != null){
            System.out.println(ret.data);
        } else {
            System.out.println("null");
        }
        ret = A1Q2.findKth(head, 1);
        if (ret != null){
            System.out.println(ret.data);
        } else {
            System.out.println("null");
        }
    }
}
Q1. Given two strings, determine if one is an anagram of the other. Two words are anagrams of
each other if they are made of the same letters in a different order

boolean isAnagram(String s1, String s2) {
	if (s1 == null || s1 == null)
		return false; // alternative: throw new Exception();
	if (s1.length() != s2.length())
		return false; // different char count

	// count number of appearances of each char in s1
	int [] charCount = new int[256]; // ASCII
	for (int i = 0; i < s1.length(); i++) {
		charCount[s1.charAt(i)]++;
	}

	// check if the number of appearances of each char in s2 matches
	for (int i = 0; i < s2.length(); i++) {
		if (charCount[s2.charAt(i)] <= 0)
			return false; // char s2.charAt(i) appears more times in s2
		charCount[s2.charAt(i)]--;
	}
	return true;
}

Follow-ups:
1. Make the algorithm able to handle both case sensitive and case insensitive anagrams.

// case insensitive
boolean isAnagramCaseInsensitive(String s1, String s2) {
	if (s1 == null || s1 == null)
		return false; // alternative: throw new Exception();
	if (s1.length() != s2.length())
		return false; // different char count

	s1.toLowerCase();
	s2.toLowerCase();
	// count number of appearances of each char in s1
	int [] charCount = new int[256]; // ASCII
	for (int i = 0; i < s1.length(); i++) {
		charCount[s1.charAt(i)]++;
	}

	// check if the number of appearances of each char in s2 matches
	for (int i = 0; i < s2.length(); i++) {
		if (charCount[s2.charAt(i)] <= 0)
			return false; // char s2.charAt(i) appears more times in s2
		charCount[s2.charAt(i)]--;
	}
	return true;
}
2. Make the algorithm able to handle anagrams of sentences, where each word in the resulting
sentence is an anagram of one of the words in the original sentence

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

boolean isAnagramSentence(String s1, String s2) {
	if (s1 == null || s1 == null)
		return false; // alternative: throw new Exception();
	if (s1.length() != s2.length())
		return false; // different char count

	// case insensitive
	s1.toLowerCase();
	s2.toLowerCase();


	HashMap<String, WordInfo> map = new HashMap<String, WordInfo>();
	StringBuilder currWord = new StringBuilder();

	// find all words in the first sentence
	for (int i = 0; i < s1.length(); i++) {
		char currChar = s1.charAt(i);
		if (currChar == ' ' || currChar == ',' || currChar == '.' || currChar == '!' || currChar == '?' || currChar =='	') {
			if (currWord.length() == 0)
				continue; // case when we have punctiation followed by space
			if (map.contains(currWord)){
				WordInfo currWordInfo = map.get(currWord);
				currWordInfo.setCount(currWordInfo.getCount() + 1)
				map.put(currWord, currWordInfo);
			} else {
				WordInfo currWordInfo = new WordInfo(1, currWord.length());
				map.put(currWord, 1);
			}
			currWord = new StringBuilder();
		}
		currWord.append(currChar);
	}

	// go through all words in the second sentence
	for (int i = 0; i < s2.length(); i++) {
		char currChar = s2.charAt(i);
		if (currChar == ' ' || currChar == ',' || currChar == '.' || currChar == '!' || currChar == '?' || currChar =='	') {
			if (currWord.length() == 0)
				continue; // case when we have punctiation followed by space
			
			boolean found = false;
			// check all words from the first sentence
			for (String checkWord : map.keySet()){
				WordInfo checkWordInfo = map.get(checkWord);
				if (checkWordInfo.getLength() == currWord.getLength()){
					if (isAnagram(currWord, checkWord)) {
						if (checkWordInfo.getCount() == 1){
							map.remove(checkWord); // last apperance of the found word
						} else {
							checkWordInfo.setCount(checkWordInfo.getCount() - 1)
							map.put(checkWord, checkWordInfo);
						}
						found = true;
						break;
					}
				}			
			}
			if (found == false)
				return false; // anagram of current word is not found
			currWord = new StringBuilder();
		}
		currWord.append(currChar);
	}		
	return true;
}

Q2. Implement an algorithm to find the kth to last element of a singly linked list.

Node findKth(Node head, int k) {
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
}
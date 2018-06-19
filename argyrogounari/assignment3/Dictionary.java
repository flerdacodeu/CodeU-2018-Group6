/**
* We were given a dictionary class with these two methods:
* isWord(string): Returns whether the given string is a valid word.
* isPrefix(string): Returns whether the given string is a prefix of at least one word in the
* dictionary.
*
* @author  Argyro Gounari
* @version 1.0
* @since   12-06-2018 
*/
public class Dictionary {

	String dictionary[] = {"CAR", "CARD", "CART", "CAT"};
	String prefix[] = {"C", "CA", "CAR", "CARD", "CART", "CAT"};
	
	public Boolean isWord(String string) {
		for (int i = 0; i < dictionary.length; i++) {
			if (string == dictionary[i]) {
				return true;
			}
		}
		return false;
	}
	
	public Boolean isPrefix(String string) {
		for (int i = 0; i < prefix.length; i++) {
			if (string == prefix[i]) {
				return true;
			}
		}
		return false;
	}
	
}


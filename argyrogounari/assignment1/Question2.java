/**
* Question 2: Implement an algorithm to find the kth to last 
* element of a singly linked list.
*
* @author  Argyro Gounari
* @version 1.1
* @since   15-05-2018 
*/
public class Question2 {

	public static void main(String[] args) {
		
		SinglyLinkedList<Integer> newList = new SinglyLinkedList<>();
		
		newList.addHead(3);
		newList.addHead(2);
		newList.addHead(1);
		newList.addHead(0);
		newList.display(newList.getHead());
		
		newList.findK(-1); // Error: Wrong number message
		newList.findK(0);  // Error: Wrong number message
		newList.findK(4);  // Error: Wrong number message
		newList.findK(5);  // Error: Wrong number message
		
	}

}

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
		newList.addHead(4);
		newList.addHead(3);
		newList.addHead(2);
		newList.addHead(1);
		newList.addHead(0);
		newList.findK(4);
		
	}

}

/**
* Class representing a Node.
*
* @author  Argyro Gounari
* @version 1.1
* @since   15-05-2018 
*/
public class Node <T>{

	private T data;
	private Node<T> next;
	
	public Node(T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}
	
	public T getData(){
		return this.data;
	}
	
	public Node<T> getNext(){
		return this.next;
	}
	
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
}

/**
* Class representing a Singly Linked List.
*
* @author  Argyro Gounari
* @version 1.1
* @since   15-05-2018 
*/
public class SinglyLinkedList<T> {

	private Node<T> head;
	private Node<T> tail;
	private Node<T> reversedHead = this.reverse(head);
	
	public SinglyLinkedList() {};
	
	/**
	* Finds the kth to last 
	* element of a singly linked list.
	* 
	* @param k
	*/
	public void findK(int k) {
		
		if (k<0 || k>=this.size()) {
			System.out.println(k + " is a wrong number.");
		} else {
			
			Node<T> answer = reversedHead;
			for(int i=0; i<k; i++) {
				answer = answer.getNext();
			}
			if (answer==null) {
				System.out.println(k + " is a wrong number.");
			} else {
				System.out.println("The "+ k + " to last element of a singly linked list is "+ answer.getData());
			}
			
		}
		
	}
	
	public boolean isEmpty() {
		return this.head == null;
	}
	
	public void removeHead() {
		if (isEmpty()) {
			this.head = null;
			this.tail = null;
		} else {
			this.head = this.head.getNext();
		}
	}
	
	public Node<T> reverse(Node<T> head) {
		
		if (head == null) {
			return head;
		}
		
		Node<T> current = head;
		Node<T> previous = null;
		Node<T> next = null;
		
		while(current!=null) {
			next = current.next;
			current.next = previous;
			previous = current;
			current = next;
		}
		return previous;
	}
	
	public void addHead(T data) {
		if (isEmpty()) {
			this.head = new Node<T>( data, null);
			this.tail = this.head;
		} else {
			this.head = new Node<T>( data, this.head);
		}
		
	}
	
	public void addTail(T data) {
		this.tail = new Node<T>( data, null);
		if (isEmpty()) {
			this.head = this.tail;
		} else {
			Node<T> newNode = new Node<T>( data, null);
			this.tail = new Node<T>( this.tail.getData(), newNode);
			this.tail = newNode;
		}
	}
	
	public Node<T> getHead() {
		if(isEmpty()) {
			return null;
		}
		return this.head;
	}
	
	public Node<T> getReversedHead() {
		if(isEmpty()) {
			return null;
		}
		return this.reversedHead;
	}
	
	public T getTail() {
		if(isEmpty()){
			return null;
		}
		return this.tail.getData();
	}
	
	public void display(Node<T> head) {
		if(head == null){
			return;
		}
		Node<T> current = head;
		while(current!=null) {
			System.out.print(current.getData() + " --> ");
			current = current.next;
		}
		System.out.println(current);
	}
	
	private int size() {
		int counter = 0;
		Node<T> current = head;
		while(true) {
			if (current == null) {
				return counter;
			}
			counter++;
			current = current.getNext();
		}
	}
	
}

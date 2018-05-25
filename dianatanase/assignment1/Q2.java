import java.util.Scanner;

// Note: I could have used a LinkedList<T> with its size() and get() method
// but I have created my SimpleLinkedList and Node objects for practice.

// Since the type of objects in the list is not known
// Create a Node object with data of Generic type T to represent the nodes in the list.
// A Node has data (value) and is linked to a next Node (can be null).
class Node<T>{
	
	T data = null;
	Node<T> next = null;
	
	Node(T data, Node<T> next){
		this.data = data;
		this.next = next;
	}
	
	// Update the next node
	public void nextNode(Node<T> next){
		this.next = next;
	}
	
	public T getData(){
		return data;
	}
	
	public Node<T> getNext(){
		return next;
	}
}

// Create a SimpleLinkedList object to hold Nodes
class SimpleLinkedList<T>{
	Node<T> root = null;
	Node<T> last = null;
	int size = 0;
	
	SimpleLinkedList(){}
	
	SimpleLinkedList(T rootData){
		Node<T> rootNode = new Node<T>(rootData, null);
		root = rootNode;
		last = root;
		size = 1;
	}
	
	public int getSize(){
		return size;
	}
	
	// Add values at the end of the list
	public void addElement(T nodeData){
		Node<T> newNode = new Node<T>(nodeData, null);
		last.nextNode(newNode);
		last = newNode;
		size++;
	}
	
	public boolean isEmpty(){
		return (size <= 0);
	}
	
	public T getElement(int index){
		
		if (index >= size || index < 0){
		   throw new IndexOutOfBoundsException();
		}
		 
		Node<T> curNode = root;
		int i = 0;
		// Traverse the list from root to the element we want
		while(i < index){
			curNode = curNode.getNext();
			i++;
		}			
		return curNode.getData();
	}
}

public class Q2<T>{
	
	// Compute the size of the list
	// Calculate how many elements to traverse from left to the kth elem required.
	// Give as argument the list (with Node objects) and the query k.
	public static <T> T getKthFromLastElem(int k, SimpleLinkedList<T> list){
		// Check if list is empty or if k is negative.
		if(list.isEmpty() || k < 0){
			System.out.println("Invalid query");
			return null;
		}
		else{
			int size = list.getSize();
			if(k >= size){
				System.out.println("Invalid query");
				return null;
			}
			// Calculate the index the element we want is at
			// With respect to the root (from left to right)
			int index = size - k - 1;
			T result = list.getElement(index);
			return result;
		}
	}
	
	// A main method for testing.
	// k is the query, then input the elements separated by space and add them to the list.
	public static <T> void main(String args[]){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input query k:");
		int k = Integer.parseInt(scanner.nextLine());
		
		// Parse the values 
		String line = scanner.nextLine();
		String[] values = line.split(" ");
		
		String nodeData = values[0];
		SimpleLinkedList<String> list = new SimpleLinkedList<String>(nodeData);
		// Add the values to the list
		for(int i = 1; i < values.length; i++){
			nodeData = values[i];
			list.addElement(nodeData);
		}

		String result = getKthFromLastElem(k, list);
		
		if(result == null){
			System.out.println("Result: null");
		}
		else{
			System.out.println("Result: " + result);
		}
		
		scanner.close();
	}
}
   

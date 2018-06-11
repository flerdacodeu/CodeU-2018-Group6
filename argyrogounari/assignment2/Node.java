/**
* Class representing a Node.
*
* @author  Argyro Gounari
* @version 1.1
* @since   29-05-2018 
*/
public class Node<T> {

	private int key;
	private T value;
	private Node<T> left;
	private Node<T> right;
	
	public Node(int key, T value) {
		this.key = key;
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
	public Node(int key, T value, Node<T> left, Node<T> right) {
		this.key = key;
		this.value = value;
		this.left = left;
		this.right = right;
	}
	
	public void setKey(int key) {
		this.key = key;
	}
	
	public int getKey() {
		return this.key;
	}
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public void setLeft(Node<T> left) {
		this.left = left;
	}
	
	public void setRight(Node<T> right) {
		this.right = right;
	}
	
	public T getValue() {
		return this.value;
	}
	
	public Node<T> getLeft() {
		return left;
	}
	
	public Node<T> getRight() {
		return this.right;
	}
	
}

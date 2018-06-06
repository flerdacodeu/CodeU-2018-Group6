/**
* Class representing a Node.
*
* @author  Argyro Gounari
* @version 1.0
* @since   29-05-2018 
*/
public class Node<T> {

	private T value;
	private Node<T> left;
	private Node<T> right;
	
	public Node(T value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
	public Node(T value, Node<T> left, Node<T> right) {
		this.value = value;
		this.left = left;
		this.right = right;
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

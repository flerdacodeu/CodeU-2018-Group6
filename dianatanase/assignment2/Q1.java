import java.util.ArrayList;

// Represent the Tree with Nodes that store data of generic type T.
// Each node as a key, I assumed keys are positive integers and unique for each node.
// Each node has a leaf and right child (could be null = leaf).
// Store the parent of each node as well.
class Node<T>{
	
	T data = null;
	Node<T> left = null;
	Node<T> right = null;
	Node<T> parent = null;
	int key = -1;
	
	Node(Node<T> parent, int key, T data, Node<T> left, Node<T> right){
		this.data = data;
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.key = key;
	}
	
	// Update left node, right node and the data.
	public void leftNode(Node<T> left){
		this.left = left;
	}
	
	public void rightNode(Node<T> right){
		this.right = right;
	}
	
	public void updateData(T data){
		this.data = data;
	}
	
	public T getData(){
		return data;
	}
	
	// Update parent in case we want to move a node. 
	public void updateParent(Node<T> parent){
		this.parent = parent;
	}
	
	public Node<T> getLeft(){
		return left;
	}
	
	public Node<T> getRight(){
		return right;
	}
	
	public Node<T> getParent(){
		return parent;
	}
	
	public int getKey(){
		return key;
	}
}

// Create a Tree structure, each tree is represented by its root Node.
class Tree<T>{
	Node<T> root = null;
	
	Tree(){}
	
	Tree(Node<T> root){
		this.root = root;
	}
	
	public Node<T> getRoot(){
		return root;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
}


public class Q1 {

	// Get the ancestors of a node, return an ArrayList of integers storing the keys of the ancestors.
	public static <T> ArrayList<Integer> getAncestorsOfNode(Node<T> node){
		
		// Return empty ArrayList if node query is null.
		if(node == null){
			System.out.println("Invalid query. Node is null.");
			return new ArrayList<Integer>();
		}
		
		ArrayList<Integer> ancestors = new ArrayList<Integer>();
		Node<T> tmp_child = node;
		Node<T> tmp_parent = node.getParent();
		
		// Traverse from the node query up through its parents and the ancestors until the root was added.
		// Root has null parent.
		while(tmp_parent != null){
			ancestors.add(tmp_parent.getKey());
			tmp_child = tmp_parent;
			tmp_parent = tmp_child.getParent();
		}
		
		return ancestors;
	}
	
	// Search for a node in a tree and return it, given its key.
	public static <T> Node<T> findNode(int key, Node<T> root){
		
		if(root == null){
			return null;
		}
		
		if(key < 0){ // If we assume only positive integers are used for keys.
			System.out.println("Invalid query. Key is negative, should be positive.");
			return null; 
		}
		
		// Check if the root is the wanted node.
		int tmp_key = root.getKey();
		if(tmp_key == key){
			return root;
		}
		
		// If not, search for the node in the left child of the current root.
		Node<T> foundNode = findNode(key, root.getLeft());
		if(foundNode != null){
			return foundNode;
		}
		// If node not found in left child, search in the right child of the current root.
		return findNode(key, root.getRight()); 
	}
	
	// Given the tree (with its root), search for the node with the given key.
	// Return and ArrayList of Integers, storing the keys of the ancestors.
	public static <T> ArrayList<Integer> getAncestorsInTree(int key, Tree<T> tree){
		
		if(tree == null || tree.isEmpty()){
			System.out.println("Invalid query. Tree is null.");
			return new ArrayList<Integer>();
		}
		
		if(key < 0){ // If we assume only positive integers are used for keys.
			System.out.println("Invalid query. Key is negative, should be positive.");
			return new ArrayList<Integer>(); 
		}
		
		Node<T> root = tree.getRoot();
		// Search for the node in the tree.
		Node<T> node = findNode(key, root);
		if(node == null){
			System.out.println("Invalid query. Node with key " + key + " does not exist in tree.");
			return new ArrayList<Integer>();
		}
		// Return the ancestors of the node found.
		return getAncestorsOfNode(node);
	}
}

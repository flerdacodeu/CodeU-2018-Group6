// Represent the Tree with Nodes that store data of generic type T.
// Each node as a key, I assumed keys are integers.
// Each node has a leaf and right child (could be null = leaf).
// Store the parent of each node as well.
class Node<T>{
	
	T data = null;
	Node<T> left = null;
	Node<T> right = null;
	Node<T> parent = null;
	final int key;
	
	Node(int key, T data){
		this.data = data;
		this.key = key;
	}

	// Update left node, right node and the data.
	public void leftNode(Node<T> left){
		if(this.left != null){ // if left child exists, delete the edge parent -> current left
			this.left.parent = null;
		}
		this.left = left;
		if(left != null)
			left.parent = this;
	}
	
	public void rightNode(Node<T> right){
		if(this.right != null){  // if right child exists, delete the edge parent -> current right
			this.right.parent = null;
		}
		this.right = right;
		if(right != null)
			right.parent = this;
	}
	
	public void updateData(T data){
		this.data = data;
	}
	
	public T getData(){
		return data;
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
	
	// Search for a node (given its key) in the children of this node and return it.
	public Node<T> findKeyInChildren(int key){
		
		// Check if this node is the wanted node.
		if(this.key == key){
			return this;
		}
		
		// If not, search for the node in the left child of the current node.
		if(left != null){
			Node<T> foundNode = left.findKeyInChildren(key);
			if(foundNode != null){
				return foundNode;
			}
		}
		
		// If node not found in left child, search in the right child of the current node.
		if(right != null){
			return right.findKeyInChildren(key); 
		}
		
		return null;
	}
}

// Create a Tree structure, each tree is represented by its root Node.
public class Tree<T>{
	
	Node<T> root = null;
	
	Tree(){}
	
	void setRoot(Node<T> root){
		this.root = root;
	}
	
	public Node<T> getRoot(){
		return root;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	// Add a node in the tree, given the key of the child, the key of the parent and the data of the child.
	// Don't update the children if they already exist.
	void addChildToKey(int key_child, int key_parent, T data) throws Exception{
		
		Node<T> parent_node = root.findKeyInChildren(key_parent);
		if(parent_node != null){
			Node<T> child_node = new Node<T>(key_child, data);
			// If parent does not have left child, add the new node to the left.
			if(parent_node.getLeft() == null)
				parent_node.leftNode(child_node);
			else if(parent_node.getRight() == null){  
				parent_node.rightNode(child_node);
			}
			else{ // Throw exception if parent already has left and right child (don't change them)
				throw new Exception("Parent node with key " + key_parent + " already has 2 children");
			}
		}
		else{ // Throw exception if parent does not exist in tree.
			throw new Exception("Parent node with key " + key_parent + " does not exist in tree");
		}
	}
	
	// Update Left or Right child of a key (change it if it already exists).
	void updateLeftChildOfKey(int key_child, int key_parent, T data) throws Exception{
		Node<T> parent_node = root.findKeyInChildren(key_parent);
		if(parent_node != null){
			Node<T> child_node = new Node<T>(key_child, data);			
			parent_node.leftNode(child_node);
		}
		else{ // Throw exception if parent does not exist in tree.
			throw new Exception("Parent node with key " + key_parent + " does not exist in tree");
		
		}
	}

	void updateRightChildOfKey(int key_child, int key_parent, T data) throws Exception{
		Node<T> parent_node = root.findKeyInChildren(key_parent);
		if(parent_node != null){
			Node<T> child_node = new Node<T>(key_child, data);			
			parent_node.rightNode(child_node);
		}
		else{ // Throw exception if parent does not exist in tree.
			throw new Exception("Parent node with key " + key_parent + " does not exist in tree");	
		}
	}
}

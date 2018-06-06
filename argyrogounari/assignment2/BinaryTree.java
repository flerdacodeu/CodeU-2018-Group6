import java.util.ArrayList;

/**
* Class representing an arbitrary Binary Tree.
*
* @author  Argyro Gounari
* @version 1.0
* @since   29-05-2018 
*/
public class BinaryTree<T> {

	private Node<T> root;

	public BinaryTree(Node<T> root){
		this.root = root;
	}
	
	public Node<T> getRoot(){
		return root;
	}
	
	/**
	* For this method my idea was to iterate through the tree,
	* adding elements to the list as I go and at the end when the key is 
	* found print previous/ancestor keys.
	* It is not working at the moment but I will have time to give it more thought
	* and research in the coming week.
	*/
	public ArrayList<T> find(T key, Node<T> current) {
		ArrayList<T> ancestors = new ArrayList<T>();
		if (current.getValue() == key) {
			return ancestors;
		}
		ancestors.add(current.getValue());
		if (!(current.getLeft() == null)) {
			find(key,current.getLeft());
		}
		if (!(current.getRight() == null)) {
			find(key,current.getRight());
		}
		return ancestors;
		
	}
	
	public String findKey(T key) {
		if (root==null) {
			return "The tree is empty.";
		}
		ArrayList<T> ancestors = find(key, root);
		String output = "The ancestors of the key in the given binary tree are: " + "\n";
		for (int i=0; i<ancestors.size(); i++) {
			output = output + ancestors.get(i) + "\n";
		}
		return output;
	}

	
	
}

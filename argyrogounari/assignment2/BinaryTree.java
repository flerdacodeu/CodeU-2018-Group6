
/**
* Class representing an arbitrary Binary Tree.
*
* @author  Argyro Gounari
* @version 1.1
* @since   29-05-2018 
*/
public class BinaryTree<T> {

	private Node<T> root;
	
	/**
	* Creates a binary tree. 
	*
	* @param key
	* @param value
	*/
	public void addNode(int key, T value) {
		
		Node<T> newNode = new Node<T>(key, value);
		
		if (root == null) {

			root = newNode;

		} else {
			
			Node<T> focusNode = root;
			Node<T> parent;

			while (true) {
				
				parent = focusNode;

				if (parent.getLeft() == null) {
					
					parent.setLeft(newNode); 
					return; 
					
				} else if(parent.getRight() == null){
					
					parent.setRight(newNode);
					return; 

				}
				
				focusNode = parent.getRight(); //stores only on the right
				
			}
		}

	}

	/**
	* Q1 - Print Ancestors
	* Prints all the ancestors of the key 
	* in the given binary tree.
	*
	* @param node
	* @param key
	*/
	public boolean printAncestors(Node<T> node, int key) {
       
       if (node == null)return false;
       if (node.getKey() == key) return true;

       if (printAncestors(node.getLeft(), key)
               || printAncestors(node.getRight(), key)) 
       {
           System.out.print(node.getValue() + " ");
           return true;
       }
       return false;
   }
	
	/**
	* Q2 - Common Ancestor
	* Find the lowest common ancestor 
	* of two nodes in a binary tree.
	*
	* @param root
	* @param n1
	* @param n2
	*/
	public Node<T> commonAncestor( Node<T> root, Node<T> n1, Node<T> n2) {
		if (root==null) return null;
		if (root==n1 || root==n2) return root;
		Node<T> left = commonAncestor(root.getLeft(),n1,n2);
		Node<T> right = commonAncestor(root.getRight(),n1,n2);
		if (left!=null && right!=null) return root;
		if (left==null && right==null) return null;
		if (left!=null) return left;
		return right;
	}
	
	public Node<T> getRoot(){
		return root;
	}
	
}

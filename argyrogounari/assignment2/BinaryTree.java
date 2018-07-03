
/**
* Class representing an arbitrary Binary Tree.
*
* @author  Argyro Gounari
* @version 1.1
* @since   29-05-2018 
*/
public class BinaryTree<T> {
	
	private Node<T> root;
	private int size;
	
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
			size++;

		} else {
			
			Node<T> focusNode = root;
			Node<T> parent;

			while (true) {

				parent = focusNode;

				if (key < focusNode.getKey()) {

					focusNode = focusNode.getLeft();

					if (focusNode == null) {
						
						parent.setLeft(newNode);
						size++;
						return; 
						
					}

				} else { 

					focusNode = focusNode.getRight();
					
					if (focusNode == null) {

						parent.setRight(newNode); 
						size++;
						return; 

					}

				}

			}
		}

	}

	/**
	* Q1 - Print Ancestors
	* Prints all the ancestors of the key 
	* in the binary tree.
	*
	* @param node
	* @param key
	*/
	public boolean printAncestors(int key) {
		return printAncestors(root, key);
   	}
	
	/**
	* Prints all the ancestors of the key 
	* in the given binary tree.
	*
	* @param node
	* @param key
	*/
	public boolean printAncestors(Node<T> node, int key) {
       
	       if (node == null) return false;
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
	* Prints all the ancestors keys 
	* of the key in the given binary tree.
	* - Same code as printAncestors
	* but used for testing.
	*
	* @param node
	* @param key
	*/
	public boolean printAncestorsKeys(Node<T> node, int key) {
       
	       if (node == null) return false;
	       if (node.getKey() == key) return true;

	       if (printAncestors(node.getLeft(), key)
		       || printAncestors(node.getRight(), key)) 
	       {
		   System.out.print(node.getKey() + " ");						
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
	public T commonAncestor(int key1, int key2) {
		return commonAncestor(root, key1, key2).getValue();
	}
	
	/**
	* Helps find the lowest common ancestor 
	* of two nodes in a binary tree.
	*/
	private Node<T> commonAncestor( Node<T> root, int key1, int key2) {
		if (key1>size || key2>size) return null;
		if (root==null) return null;
		if (root.getKey()==key1 || root.getKey()==key2) return root;
		Node<T> left = commonAncestor(root.getLeft(),key1,key2);
		Node<T> right = commonAncestor(root.getRight(),key1,key2);
		if (left!=null && right!=null) return root;
		if (left==null && right==null) return null;
		if (left!=null) return left;
		return right;
	}
	
	public int size(Node<T> focusNode) {
		
		int size = 0;
		
		if (focusNode != null) {

			System.out.println(focusNode);

			size(focusNode.getLeft());
			size(focusNode.getRight());

		}
		
		return size;

	}

}

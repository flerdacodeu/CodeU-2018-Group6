/**
* Q1 - Print Ancestors
* Given a binary tree and a key, write a function that prints 
* all the ancestors of the key in the given binary tree.
* 
* Q2 - Common Ancestor
* Design an algorithm and write ​code​ to find the lowest common ancestor 
* of two nodes in a binary tree. Avoid storing additional 
* nodes in a data structure.
* 
* @author  Argyro Gounari
* @version 1.1
* @since   29-05-2018 
*/
public class TheMain {
	public static void main(String[] args) {

		BinaryTree<Integer> myTree = new BinaryTree<Integer>();
		myTree.addNode(1,1);
		myTree.addNode(2,2);
		myTree.addNode(3,3);
		myTree.addNode(4,4);
		myTree.addNode(5,5);
		/* 
		        1
		      /   \
		     2     3
			      /  \
			     4    5
		*/
		
		// Q1 - Print Ancestors
		System.out.print("The ancestors of 5 are: ");
		myTree.printAncestors(myTree.getRoot(),5);
		System.out.println();
		
		// Q2 - Common Ancestor
		System.out.print("The lowest common ancestor is: ");
		System.out.println(myTree.commonAncestor(myTree.getRoot(),myTree.getRoot().getRight().getLeft(), myTree.getRoot().getRight().getRight()).getKey());
		
	}
}

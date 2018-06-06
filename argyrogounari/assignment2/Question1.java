/**
* Q1 - Print Ancestors
* Given a binary tree and a key, write a function that prints 
* all the ancestors of the key in the given binary tree.
* 
* Note: ​the tree is NOT a binary search tree (where the keys are ordered),
* but an arbitrary binary tree. You should implement your 
* own data structure to store the binary tree.
* 
* @author  Argyro Gounari
* @version 1.0
* @since   29-05-2018 
*/
public class Question1 {
	public static void main(String[] args) {

		BinaryTree<Integer> myTree = new BinaryTree<Integer>(new Node<Integer>(7));
		myTree.getRoot().setLeft(new Node<Integer>(3));
		myTree.getRoot().setRight(new Node<Integer>(4));
		myTree.getRoot().getLeft().setLeft(new Node<Integer>(2));
		myTree.getRoot().getLeft().setRight(new Node<Integer>(5));
		myTree.getRoot().getRight().setRight(new Node<Integer>(8));
		myTree.getRoot().getLeft().getLeft().setLeft(new Node<Integer>(1));
		myTree.getRoot().getLeft().getLeft().setRight(new Node<Integer>(6));
		System.out.print(myTree.findKey(6));
		
	}
}

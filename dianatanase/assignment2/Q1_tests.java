import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;


public class Q1_tests {

	// Test result is an empty array list for an empty or null tree.
	@Test
	public void testNull(){
		
		Tree<Integer> tree = new Tree<Integer>();
		assertEquals(0, Q1.getAncestorsInTree(5, tree).size());
		
		assertEquals(0, Q1.getAncestorsInTree(5, null).size());
	}
	
	// Test the result with keys equal with data and in increasing order.
	@Test
	public void testKeysInOrder() {
		
		// Initialize nodes of the tree.
		Node<Integer> node1 = new Node<Integer>(null, 1, 1, null, null);
		Node<Integer> node2 = new Node<Integer>(node1, 2, 2, null, null);
		Node<Integer> node3 = new Node<Integer>(node1, 3, 3, null, null);
		Node<Integer> node4 = new Node<Integer>(node2, 4, 4, null, null);
		Node<Integer> node5 = new Node<Integer>(node2, 5, 5, null, null);
		Node<Integer> node6 = new Node<Integer>(node3, 6, 6, null, null);
		Node<Integer> node7 = new Node<Integer>(node3, 7, 7, null, null);
		Node<Integer> node8 = new Node<Integer>(node4, 8, 8, null, null);
		Node<Integer> node9 = new Node<Integer>(node4, 9, 9, null, null);
		
		// Link nodes to their parents.
		node1.leftNode(node2);
		node1.rightNode(node3);
		
		node2.leftNode(node4);
		node2.rightNode(node5);
		
		node3.leftNode(node6);
		node3.rightNode(node7);
		
		node4.leftNode(node8);
		node4.rightNode(node9);
		
		Tree<Integer> tree = new Tree<Integer>(node1);
		
		// Declare expected results.
		ArrayList<Integer> ancestors1 = new ArrayList<Integer>();
		ArrayList<Integer> ancestors2and3 = new ArrayList<Integer>(Arrays.asList(1));
		ArrayList<Integer> ancestors4and5 = new ArrayList<Integer>(Arrays.asList(2,1));
		ArrayList<Integer> ancestors6and7 = new ArrayList<Integer>(Arrays.asList(3,1));
		ArrayList<Integer> ancestors8and9 = new ArrayList<Integer>(Arrays.asList(4,2,1));
		
		// Test correct ancestors for each node.
		assertEquals(ancestors1, Q1.getAncestorsInTree(1, tree));
		assertEquals(ancestors2and3, Q1.getAncestorsInTree(2, tree));
		assertEquals(ancestors2and3, Q1.getAncestorsInTree(3, tree));
		assertEquals(ancestors4and5, Q1.getAncestorsInTree(4, tree));
		assertEquals(ancestors4and5, Q1.getAncestorsInTree(5, tree));
		assertEquals(ancestors6and7, Q1.getAncestorsInTree(6, tree));
		assertEquals(ancestors6and7, Q1.getAncestorsInTree(7, tree));
		assertEquals(ancestors8and9, Q1.getAncestorsInTree(8, tree));
		assertEquals(ancestors8and9, Q1.getAncestorsInTree(9, tree));
		
		// Test with keys that are not accepted (negative) or do not exist in the tree.
		assertEquals(ancestors1, Q1.getAncestorsInTree(-10, tree));
		assertEquals(ancestors1, Q1.getAncestorsInTree(20, tree));
	}
	
	// Test with keys not in order and data of char type.
	@Test
	public void testKeysNotInOrder() {
		
		// Initialize nodes.
		Node<Character> node1 = new Node<Character>(null, 7, 'x', null, null);
		Node<Character> node2 = new Node<Character>(node1, 3, 'a', null, null);
		Node<Character> node3 = new Node<Character>(node1, 4, 'v', null, null);
		Node<Character> node4 = new Node<Character>(node2, 2, 'w', null, null);
		Node<Character> node5 = new Node<Character>(node2, 5, 't', null, null);
		Node<Character> node6 = new Node<Character>(node3, 8, 'u', null, null);
		Node<Character> node7 = new Node<Character>(node4, 1, 's', null, null);
		Node<Character> node8 = new Node<Character>(node4, 6, 'p', null, null);

		// Link nodes to their parents.s
		node1.leftNode(node2);
		node1.rightNode(node3);
		
		node2.leftNode(node4);
		node2.rightNode(node5);
		
		node3.rightNode(node6);
		
		node4.leftNode(node7);
		node4.rightNode(node8);
		
		Tree<Character> tree = new Tree<Character>(node1);
		
		// Declare expected results.
		ArrayList<Integer> ancestors1 = new ArrayList<Integer>();
		ArrayList<Integer> ancestors2and3 = new ArrayList<Integer>(Arrays.asList(7));
		ArrayList<Integer> ancestors4and5 = new ArrayList<Integer>(Arrays.asList(3,7));
		ArrayList<Integer> ancestors6 = new ArrayList<Integer>(Arrays.asList(4,7));
		ArrayList<Integer> ancestors7and8 = new ArrayList<Integer>(Arrays.asList(2,3,7));
		
		// Test correct ancestors for each node.
		assertEquals(ancestors1, Q1.getAncestorsInTree(7, tree));
		assertEquals(ancestors2and3, Q1.getAncestorsInTree(3, tree));
		assertEquals(ancestors2and3, Q1.getAncestorsInTree(4, tree));
		assertEquals(ancestors4and5, Q1.getAncestorsInTree(2, tree));
		assertEquals(ancestors4and5, Q1.getAncestorsInTree(5, tree));
		assertEquals(ancestors6, Q1.getAncestorsInTree(8, tree));
		assertEquals(ancestors7and8, Q1.getAncestorsInTree(1, tree));
		assertEquals(ancestors7and8, Q1.getAncestorsInTree(6, tree));
		
		// Test with keys that are not accepted (negative) or do not exist in the tree.
		assertEquals(ancestors1, Q1.getAncestorsInTree(-10, tree));
		assertEquals(ancestors1, Q1.getAncestorsInTree(33, tree));
	}

}

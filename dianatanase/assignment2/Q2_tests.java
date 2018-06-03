import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;


public class Q2_tests {
	
	// Test result is an empty array list for an empty or null tree.
	@Test
	public void testNull(){
		
		Tree<Integer> tree = new Tree<Integer>();
		assertEquals(-1, Q2.getLCA(2,3, tree));
		
		assertEquals(-1, Q2.getLCA(2,3, null));
	}
		
	// Test the results for a tree with keys not in order and char data.
	@Test
	public void testLCA() {
		
		// Initialize nodes.
		Node<Character> node1 = new Node<Character>(null, 7, 'x', null, null);
		Node<Character> node2 = new Node<Character>(node1, 3, 'a', null, null);
		Node<Character> node3 = new Node<Character>(node1, 4, 'v', null, null);
		Node<Character> node4 = new Node<Character>(node2, 2, 'w', null, null);
		Node<Character> node5 = new Node<Character>(node2, 5, 't', null, null);
		Node<Character> node6 = new Node<Character>(node3, 8, 'u', null, null);
		Node<Character> node7 = new Node<Character>(node4, 1, 's', null, null);
		Node<Character> node8 = new Node<Character>(node4, 6, 'p', null, null);

		// Link nodes to their parents.
		node1.leftNode(node2);
		node1.rightNode(node3);
		
		node2.leftNode(node4);
		node2.rightNode(node5);
		
		node3.rightNode(node6);
		
		node4.leftNode(node7);
		node4.rightNode(node8);
		
		Tree<Character> tree = new Tree<Character>(node1);
		
		// Test correct LCA for some nodes.
		assertEquals(7, Q2.getLCA(7,7, tree)); 
		assertEquals(7, Q2.getLCA(7,4, tree)); 
		assertEquals(7, Q2.getLCA(6,8, tree));  
		assertEquals(7, Q2.getLCA(5,8, tree));
		assertEquals(7, Q2.getLCA(3,4, tree));
		assertEquals(2, Q2.getLCA(2,2, tree));
		assertEquals(2, Q2.getLCA(1,6, tree));
		assertEquals(3, Q2.getLCA(1,5, tree));
		assertEquals(2, Q2.getLCA(1,2, tree));
		assertEquals(3, Q2.getLCA(3,5, tree));
		assertEquals(3, Q2.getLCA(2,5, tree));
		
		// Test with key that are not accepted (do not exist in the tree).
		assertEquals(-1, Q2.getLCA(7,15, tree));
		assertEquals(-1, Q2.getLCA(31,7, tree));
		assertEquals(-1, Q2.getLCA(17,2, tree));
		assertEquals(-1, Q2.getLCA(5,20, tree));

	}

}

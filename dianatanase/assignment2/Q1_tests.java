import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class Q1_tests {

	// Set up of tree used for testing.
	public Tree<Integer> setUp() throws Exception{
		
		Node<Integer> root = new Node<Integer>(7,15);
		Tree<Integer> tree = new Tree<Integer>();
		tree.setRoot(root);
		
		tree.addChildToKey(3,7,10);
		tree.addChildToKey(4,7,3);
		tree.addChildToKey(2,3,23);
		tree.addChildToKey(5,3,14);
		tree.addChildToKey(8,4,19);
		tree.addChildToKey(1,2,39);
		tree.addChildToKey(6,2,20);
		
		return tree;
	}
	
	// Test adding a node to tree fails if parent is not in tree. 
	@Test
	public void testFailingAddChild(){
		
		Node<Integer> root = new Node<Integer>(7,15);
		Tree<Integer> tree = new Tree<Integer>();
		tree.setRoot(root);
		
		try {
			tree.addChildToKey(3,5,-1);
			 fail("Adding child should fail if key of parent is not in tree");
		} catch (Exception e) {
		}
	}
	
	// Test method fails for an empty or null tree.
	@Test
	public void testNull(){
		
		Tree<Integer> tree = new Tree<Integer>();
		try {
			 Q1.getAncestorsInTree(5, tree);
			 fail("Method should fail with empty tree.");
		} catch (Exception e) {
		}
	
		try {
			 Q1.getAncestorsInTree(5, null);
			 fail("Method should fail with empty tree.");
		} catch (Exception e) {
		}		
	}
	
	// Test with keys not in order and data of char type.
	@Test
	public void testKeysInTree() throws Exception {
		
		Tree<Integer> tree = setUp();
				
		// Declare expected results and test correct ancestors for each node.
		ArrayList<Integer> ancestors7 = new ArrayList<Integer>();
		assertEquals(ancestors7, Q1.getAncestorsInTree(7, tree));
		
		ArrayList<Integer> ancestors3and4 = new ArrayList<Integer>(Arrays.asList(7));
		assertEquals(ancestors3and4, Q1.getAncestorsInTree(3, tree));
		assertEquals(ancestors3and4, Q1.getAncestorsInTree(4, tree));
		
		ArrayList<Integer> ancestors2and5 = new ArrayList<Integer>(Arrays.asList(3,7));
		assertEquals(ancestors2and5, Q1.getAncestorsInTree(2, tree));	
		assertEquals(ancestors2and5, Q1.getAncestorsInTree(5, tree));
		
		ArrayList<Integer> ancestors8 = new ArrayList<Integer>(Arrays.asList(4,7));
		assertEquals(ancestors8, Q1.getAncestorsInTree(8, tree));
		
		ArrayList<Integer> ancestors1and6 = new ArrayList<Integer>(Arrays.asList(2,3,7));
		assertEquals(ancestors1and6, Q1.getAncestorsInTree(1, tree));
		assertEquals(ancestors1and6, Q1.getAncestorsInTree(6, tree));
	}
	
	// Test with keys that are not accepted (negative) or do not exist in the tree.
	@Test
	public void testKeysNotAccepted() throws Exception{
		
		Tree<Integer> tree = setUp();
			
		ArrayList<Integer> emptyList = new ArrayList<Integer>();
		assertEquals(emptyList, Q1.getAncestorsInTree(-11, tree));
		assertEquals(emptyList, Q1.getAncestorsInTree(33, tree));
	}

}

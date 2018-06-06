import static org.junit.Assert.*;

import org.junit.Test;

public class Q2_tests {
	
	// Set up of tree used for testing.
	public Tree<Character> setUp() throws Exception{
	
		Node<Character> root = new Node<Character>(7, 'x');
		Tree<Character> tree = new Tree<Character>();
		tree.setRoot(root);
		
		tree.addChildToKey(3,7,'a');
		tree.addChildToKey(4,7,'v');
		tree.addChildToKey(2,3,'w');
		tree.addChildToKey(5,3,'t');
		tree.addChildToKey(8,4,'u');
		tree.addChildToKey(1,2,'s');
		tree.addChildToKey(6,2,'p');
		
		return tree;
	}
	
	// Test method fails for an empty or null tree.
	@Test
	public void testEmptyOrNullTree(){
		
		Tree<Integer> tree = new Tree<Integer>();
		
		try {
			 Q2.getLCA(2,3, tree);
			 fail("Method should fail with empty tree.");
		} catch (Exception e) {
		}
		
		try {
			 Q2.getLCA(2,3, tree);
			 fail("Method should fail with null tree.");
		} catch (Exception e) {
		}
	}
		
	// Test the results for a tree with keys not in order and char data.
	@Test
	public void testSuccessfulLCA() throws Exception {

		Tree<Character> tree = setUp();
		
		// Test correct LCA for some nodes.
		assertEquals(7, Q2.getLCA(7,7, tree)); 
		assertEquals(7, Q2.getLCA(7,4, tree)); 
		assertEquals(7, Q2.getLCA(6,8, tree));  
		assertEquals(7, Q2.getLCA(5,8, tree));
		assertEquals(7, Q2.getLCA(3,4, tree));
		assertEquals(2, Q2.getLCA(2,2, tree));
		assertEquals(2, Q2.getLCA(1,6, tree));
		assertEquals(2, Q2.getLCA(1,2, tree));
		assertEquals(3, Q2.getLCA(1,5, tree));
		assertEquals(3, Q2.getLCA(3,5, tree));
		assertEquals(3, Q2.getLCA(2,5, tree));
	}
	
	// Test with key that are not accepted (do not exist in the tree).		
	@Test
	public void testFailingLCA() throws Exception {
		
		Tree<Character> tree = setUp();
		
		try {
			 Q2.getLCA(31,7, tree);
			 fail("Method should fail when key1 does not exist in tree.");
		} catch (Exception e) {
		}
		
		try {
			 Q2.getLCA(17,2, tree);
			 fail("Method should fail when key1 does not exist in tree.");
		} catch (Exception e) {
		}
		
		try {
			 Q2.getLCA(7,15, tree);
			 fail("Method should fail when key2 does not exist in tree.");
		} catch (Exception e) {
		}
		
		try {
			 Q2.getLCA(5,20, tree);
			 fail("Method should fail when key2 does not exist in tree.");
		} catch (Exception e) {
		}
	}	
}

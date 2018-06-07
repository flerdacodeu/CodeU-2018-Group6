import java.util.ArrayList;

public class Q1 {

	// Get the ancestors of a node, return an ArrayList of integers storing the keys of the ancestors.
	private static <T> ArrayList<Integer> getAncestorsOfNode(Node<T> node) {
		
		ArrayList<Integer> ancestors = new ArrayList<Integer>();
		Node<T> tmp_parent = node.getParent();
		
		// Traverse from the node query up through its parents and the ancestors until the root was added.
		// Root has null parent.
		while(tmp_parent != null){
			ancestors.add(tmp_parent.getKey());
			tmp_parent = tmp_parent.getParent();
		}
		
		return ancestors;
	}
	
	// Given the tree (with its root), search for the node with the given key.
	// Return and ArrayList of Integers, storing the keys of the ancestors.
	public static <T> ArrayList<Integer> getAncestorsInTree(int key, Tree<T> tree) throws Exception{
		
		if(tree == null || tree.isEmpty()){
			throw new Exception("Invalid query. Tree is null.");
		}

		// Search for the node in the tree.
		Node<T> node = tree.getRoot().findKeyInChildren(key);
		if(node == null){
			System.out.println("Invalid query. Node with key " + key + " does not exist in tree.");
			return new ArrayList<Integer>();
		}
		// Return the ancestors of the node found.
		return getAncestorsOfNode(node);
	}
}

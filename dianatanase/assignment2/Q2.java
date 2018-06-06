import java.util.ArrayList;

//I assumed that if one node is the ancestor of another, then I return the ancestor node.
public class Q2 {

	// Return the key lowest common ancestor of two nodes in a tree, given their keys.
	// I assumed keys are integers.
	// The tree is an object as defined in Q1, storing a root node.
	public static <T> int getLCA(int key1, int key2, Tree<T> tree) throws Exception{
		
		if(tree == null || tree.isEmpty()){
			throw new Exception("Invalid query. Tree is null.");
		}
		
		int root_key = tree.getRoot().getKey();
		
		// Get the ancestors of each node using the method in Q1.
		// If size of the returned ArrayList containing keys of ancestors is 0
		// And key is not the root which does not have ancestors
		// Then the node in query does not exist in tree.
		ArrayList<Integer> ancestors1 = Q1.getAncestorsInTree(key1, tree);				
		int size1 = ancestors1.size();
		if(size1 == 0 && key1 != root_key){
			throw new Exception("Invalid query. Node with key " + key1 +" does not exist in tree.");
		}

		ArrayList<Integer> ancestors2 = Q1.getAncestorsInTree(key2, tree);
		int size2 = ancestors2.size();
		if(size2 == 0 && key2 != root_key){
			throw new Exception("Invalid query. Node with key " + key1 +" does not exist in tree.");
		}
		
		ancestors1.add(0, key1);
		ancestors2.add(0, key2);
		int i1 = size1; // ancestors1 and 2 have now size1+1 and size2+1 because of the added key.
		int i2 = size2;
		
		// Traverse the two ancestors list from right to left until we find a different key, which is the LCA.
		while(i1 >= 0 && i2 >= 0){
			if(ancestors1.get(i1) != ancestors2.get(i2)){
				break;
			}
			i1--;
			i2--;
		}
		
		return ancestors1.get(i1+1);
	}
}

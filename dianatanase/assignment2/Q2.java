import java.util.ArrayList;

//I assumed that if one node is the ancestor of another, then I return the ancestor node.
public class Q2 {

	// Return the key lowest common ancestor of two nodes in a tree, given their keys.
	// I assumed keys are integers.
	// The tree is an object as defined in Q1, storing a root node.
	public static <T> int getLCA(int key1, int key2, Tree<T> tree){
		
		if(tree == null || tree.isEmpty()){
			System.out.println("Invalid query. Tree is null.");
			return -1;
		}
		
		Node<T> root = tree.getRoot();
		
		// If keys are the same and exist in the three, return the key.
		if(key1 == key2){
			if(Q1.findNode(key1, root) != null){
				return key1;
			}
			else {
				System.out.println("Invalid query. Node with key " + key1 +" does not exist in tree.");
				return -1;
			}
		}
		
		int root_key = root.getKey();
		
		// If one of the nodes is the root and the other one exists in the tree, return the root.
		if(key1 == root_key){
			if(Q1.findNode(key2, root) != null)
				return root_key;
			else{
				System.out.println("Invalid query. Node with key " + key1 +" does not exist in tree.");
				return -1;
			}
		}
		
		if(key2 == root_key){
			if(Q1.findNode(key1, tree.getRoot()) != null)
				return root_key;
			else{
				System.out.println("Invalid query. Node with key " + key2 +" does not exist in tree.");
				return -1;
			}
		}

		// Get the ancestors of each node using the method in Q1.
		// If size of the returned ArrayList containing keys of ancestors is 0, then the node in query does not exist in tree.
		ArrayList<Integer> ancestors1 = Q1.getAncestorsInTree(key1, tree);		
		int size1 = ancestors1.size();
		if(size1 == 0){
			System.out.println("Invalid query. Node with key " + key1 +" does not exist in tree.");
			return -1;
		}

		ArrayList<Integer> ancestors2 = Q1.getAncestorsInTree(key2, tree);
		int size2 = ancestors2.size();
		if(size2 == 0){
			System.out.println("Invalid query. Node with key " + key2 +" does not exist in tree.");
			return -1;
		}
		
		// Check if one node is the ancestor of the other one, if so return it.
		if(ancestors2.contains(key1)){
			return key1;
		}
		if(ancestors1.contains(key2)){
			return key2;
		}
		
		int i1 = size1 - 1;
		int i2 = size2 - 1;
		
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

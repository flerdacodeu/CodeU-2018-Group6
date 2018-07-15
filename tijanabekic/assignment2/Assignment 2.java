//Q1. Given a binary tree and a key, write a function that prints all the ancestors of the key in the given binary tree.

public class Node {
	int data;
	Node left;
	Node right;
	Node parent;

	Node (int d, Node par) {
		data = d;
		parent = par;
	}
}

public class Q1 {
	public static void printAllAncestors(Node node) {
		if (node == null)
			return;
		Node cur = node.parent;
		// go up the tree
		while (cur != null) {
			System.out.println(cur.info);
			cur = cur.parent;
		}
	}
}

//Q2. Design an algorithm and write code to find the lowest common ancestor of two nodes in a binary tree. Avoid storing additional nodes in a data structure.

public class Q2 {
	public static int cntHight(Node n) {
		int ret = 0;
		while (n != null) {
			ret++;
			node = node.parent;
		}
		return ret;
	}

	public static Node commonAncestor(Node n1, Node n2) {

			//calculete the higths of both nodes in order to put them on the same level
			int hightN1 = cntHight(n1);
			int hightN2 = cntHight(n2);
			Node first = null, second = null;

			if (hightN1 > hightN2) {
				first = n1;
				second = n2;
			} else {
				first = n2;
				second = n1;
			}

			// get both nodes on the same level
			int move = Math.abs(hightN2 - hightN1);
			while (move > 0 && first != null) {
				first = first.parent;
				move--;
			}

			// find the common ancestor
			while (first != second && first != null && second != null) {
				first = first.parent;
				second = second.parent;
			}

			if (first == second)
				return first;
			else
				return null;

	}
}

class Testing {
	public static void main(String[] args) {
		// build a tree
		Node root = new Node(7, null);
		root.left = new Node(3, root);
		root.right = new Node(4, root);
		root.left.left = new Node(2, root.left);
		root.left.right = new Node(5, root.left);
		root.left.left.left = new Node(1, root.left.left);
		root.left.left.right = new Node(6, root.left.left);
		root.right.right = new Node (8, root.right);

		Node root2 = new Node(88, null);

		// test Q1
		System.out.println("Ancestors of node: "+ root.left.left.right.data);
		Q1.printAllAncestors(root.left.left.right);
		System.out.println("Ancestors of node: "+ root.data);
		Q1.printAllAncestors(root);

		 // test Q2
        Node result = null;
        System.out.println("Common ancestor of nodes: " + root.left.left.right.data + " , " + root.left.right.data);
        result = Q2.commonAncestor(root.left.left.right, root.left.right);
        if (result != null)
       		System.out.println(result.data);
       	else 
       		System.out.println("There is no common ancestor");

        System.out.println("Common ancestor of nodes: " + root.left.left.right.data + " , " + root.left.left.right.data);
        result = Q2.commonAncestor(root.left.left.right, root.left.left.right);
        if (result != null)
       		System.out.println(result.data);
       	else 
       		System.out.println("There is no common ancestor");

        System.out.println("Common ancestor of nodes: " + root.data + " , " + root.left.left.right.data);
        result = Q2.commonAncestor(root, root.left.right);
        if (result != null)
       		System.out.println(result.data);
       	else 
       		System.out.println("There is no common ancestor");

        System.out.println("Common ancestor of nodes: " + root2.data + " , " + root.left.left.right.data);
        result = Q2.commonAncestor(root2, root.left.right);
        if (result != null)
       		System.out.println(result.data);
       	else 
       		System.out.println("There is no common ancestor");
	}
}
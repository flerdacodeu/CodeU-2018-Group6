package assignment2;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Design an algorithm and write ​ code​ to find the lowest common ancestor of two nodes in a binary tree.
 * Avoid storing additional nodes in a data structure.
 */
public class Question2 {
    static boolean foundOne;
    static boolean foundTwo;

    /**
     * Helper method to find lowest common ancestor of two nodes
     *
     * @param currentNode
     * @param nodeOne
     * @param nodeTwo
     * @return lowest common ancestor or one of the nodes if not both present
     */
    private static <T> MyNode<T> findLowestCommonAncestorHelper(MyNode<T> currentNode, T nodeOne, T nodeTwo) {
        if (currentNode == null) {
            return null;
        }
        if (currentNode.getData().equals(nodeOne)) {
            foundOne = true;
            // could stop search and return here, this is just to make sure both nodes are in the tree
            if (!foundTwo) {
                findLowestCommonAncestorHelper(currentNode.getLeft(), nodeOne, nodeTwo);
                findLowestCommonAncestorHelper(currentNode.getRight(), nodeOne, nodeTwo);
            }
            return currentNode;
        }
        if (currentNode.getData().equals(nodeTwo)) {
            foundTwo = true;
            // could stop search and return here, this is just to make sure both nodes are in the tree
            if (!foundOne) {
                findLowestCommonAncestorHelper(currentNode.getLeft(), nodeOne, nodeTwo);
                findLowestCommonAncestorHelper(currentNode.getRight(), nodeOne, nodeTwo);
            }
            return currentNode;
        }

        MyNode leftSearch = findLowestCommonAncestorHelper(currentNode.getLeft(), nodeOne, nodeTwo);
        MyNode rightSearch = findLowestCommonAncestorHelper(currentNode.getRight(), nodeOne, nodeTwo);
        if (leftSearch != null && rightSearch != null) {
            return currentNode;
        }
        if (leftSearch != null) {
            return leftSearch;
        }
        if (rightSearch != null) {
            return rightSearch;
        }
        return null;
    }

    /**
     * @param currentNode current root of search
     * @param nodeOne     first node
     * @param nodeTwo     second node
     * @return common ancestor of first and second node or null if one node is not present
     */
    public static <T> MyNode<T> findLowestCommonAncestor(MyNode<T> currentNode, T nodeOne, T nodeTwo) {
        foundOne = false;
        foundTwo = false;
        MyNode result = findLowestCommonAncestorHelper(currentNode, nodeOne, nodeTwo);
        if (foundOne && foundTwo) {
            return result;
        }
        return null;

    }

    public static void main(String[] args) {
        /*
         *       a
         *      /  \
         *     b    c
         *   / \     \
         *  d  e      f
         * / \
         * g  h
         *
         */

        MyNode<Character> a = new MyNode<>('a');
        MyNode<Character> b = new MyNode<>('b');
        MyNode<Character> c = new MyNode<>('c');
        MyNode<Character> d = new MyNode<>('d');
        MyNode<Character> e = new MyNode<>('e');
        MyNode<Character> f = new MyNode<>('f');
        MyNode<Character> g = new MyNode<>('g');
        MyNode<Character> h = new MyNode<>('h');
        a.setLeft(b);
        a.setRight(c);
        b.setLeft(d);
        b.setRight(e);
        c.setRight(f);
        d.setLeft(g);
        d.setLeft(h);

        assertTrue(findLowestCommonAncestor(a, 'd', 'f').equals(a)); // nodes on different branches
        assertTrue(findLowestCommonAncestor(a, 'd', 'e').equals(b)); // nodes are "brothers", LCA is their parent
        assertTrue(findLowestCommonAncestor(a, 'd', 'b').equals(b)); // one of the nodes is LCA
        assertNull(findLowestCommonAncestor(a, 'b', 'w')); // one node not in tree
        assertNull(findLowestCommonAncestor(a, 'x', 'w')); // both nodes not in tree
    }
}

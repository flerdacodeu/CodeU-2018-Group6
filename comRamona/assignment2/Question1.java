package assignment2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Given a binary tree and a key, write a function that prints all the ancestors of the key in the given
 * binary tree.
 */
public class Question1 {
    /**
     * Helper method to find the path to a node and append nodes on the path to the resultPath list(mutable)
     *
     * @param node       current root of search
     * @param target     target value
     * @param resultPath list to append path
     * @param <T>        data type of the nodes
     * @return
     */

    private static <T> MyNode findAncestorsHelper(MyNode<T> node, T target, List<T> resultPath) {
        if (node == null) {
            return null;
        }
        if (node.getData().equals(target)) {
            return node;
        }
        if (findAncestorsHelper(node.getLeft(), target, resultPath) != null) {
            resultPath.add(node.getData());
            return node;
        }
        if (findAncestorsHelper(node.getRight(), target, resultPath) != null) {
            resultPath.add(node.getData());
            return node;
        }
        return null;
    }

    /**
     * @param root   Root node of the tree
     * @param target Target value to find
     * @param <T>    data type of the nodes
     * @return list of ancestors of the target node, null if node not found
     */
    public static <T> List<T> findAncestors(MyNode<T> root, T target) {
        List<T> resultPath = new LinkedList<>();
        MyNode result = findAncestorsHelper(root, target, resultPath);
        if(result == null){
            return null;
        }
        return resultPath;
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

        assertTrue(findAncestors(a, 'h').equals(Arrays.asList('d', 'b', 'a'))); // node is a leaf
        assertTrue(findAncestors(a, 'd').equals(Arrays.asList('b', 'a'))); // node is non-leaf
        assertTrue(findAncestors(a, 'a').equals(Arrays.asList())); // node is root, expect empty path
        assertNull(findAncestors(a, 'w')); // node not in tree, expect null

    }
}

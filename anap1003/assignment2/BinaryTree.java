/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import java.util.ArrayDeque;
import java.util.Deque;

public class BinaryTree<T> {
    private TreeNode<T> root;

    /**
     * Adds a node to the tree, according to its position from the root, given in
     * the form of a String (i.e. "LLL" should add a node in the leftmost position
     * in the tree, 3 levels deep).
     * If a node already exists at the targeted position, the adding fails.
     * @param key
     * @param positionFromRoot
     * @return 
     */
    public boolean addNode(T key, String positionFromRoot) {
        if (positionFromRoot.isEmpty()) { // attempt to add root node 
            if (root == null) { //no root node in the tree
                root = new TreeNode(key);
                return true;
            }
            return false; // root node already exists
        } else { // attempt to add leaves
            TreeNode tempNode = root;

            int i;
            for (i = 0; i < positionFromRoot.length() - 1; i++) {
                if (tempNode == null) {
                    return false;
                }
                switch (positionFromRoot.charAt(i)) {
                    case 'L':
                        tempNode = tempNode.getLeftChild();
                        break;
                    case 'R':
                        tempNode = tempNode.getRightChild();
                        break;
                    default:
                        return false;
                }
            }

            switch (positionFromRoot.charAt(i)) {
                case 'L':
                    if (tempNode.getLeftChild() != null) { // node already exists at the given position
                        return false;
                    }
                    tempNode.setLeftChild(new TreeNode(key, tempNode));
                    break;
                case 'R':
                    if (tempNode.getRightChild() != null) { // node already exists at the given position
                        return false;
                    }
                    tempNode.setRightChild(new TreeNode(key, tempNode));
                    break;
                default:
                    return false;
            }
        }

        return true;
    }

    /**
     * Returns the node with the given key, by traversing the tree in the level
     * order and checking for a key match.
     * If the node is not found, returns 'null'.
     * @param key
     * @return 
     */
    public TreeNode getNodeLvlOrder(T key) {
        Deque<TreeNode<T>> queue = new ArrayDeque();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            TreeNode tempNode = queue.removeFirst();
            if (tempNode != null && tempNode.getKey().equals(key)) {
                return tempNode;
            }
            if (TreeNode.hasLeftChild(tempNode)) { // checks that node isn't null and has a left child
                queue.addLast(tempNode.getLeftChild());
            }
            if (TreeNode.hasRightChild(tempNode)) { // checks that node isn't null and has right child
                queue.addLast(tempNode.getRightChild());
            }
        }
        return null;
    }
    
    /**
     * @param node
     * @return 
     */
    public static int getNodeDepth(TreeNode node){
        if(node == null){
            return -1;
        }
        
        TreeNode tempNode = node;
        int depth = 0;
        
        while((tempNode = tempNode.getParent()) != null){
            depth++;    
        }
        
        return depth;
    }

}

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
    public boolean addNode(T key, String positionFromRoot) throws Exception {
        if (positionFromRoot.isEmpty()) { // attempt to add root node 
            if (root == null) { //no root node in the tree
                root = new TreeNode(key);
                return true;
            }
            throw new Exception("Root node already exists.");
        } else { // attempt to add leaves
            TreeNode tempNode = root;

            int i;
            for (i = 0; i < positionFromRoot.length() - 1; i++) {
                if (tempNode == null) {
                    throw new Exception("String positionFromRoot points to a position that cannot be reached in the current layout of the tree.");
                }
                switch (positionFromRoot.charAt(i)) {
                    case 'L':
                        tempNode = tempNode.getLeftChild();
                        break;
                    case 'R':
                        tempNode = tempNode.getRightChild();
                        break;
                    default:
                        throw new Exception("String positionFromRoot has an invalid character.");
                }
            }

            switch (positionFromRoot.charAt(i)) {
                case 'L':
                    if (tempNode.hasLeftChild()) { // node already exists at the given position
                        throw new Exception("A node already exists at the position given by the string positionFromRoot.");
                    }
                    tempNode.setLeftChild(new TreeNode(key, tempNode));
                    break;
                case 'R':
                    if (tempNode.hasRightChild()) { // node already exists at the given position
                        throw new Exception("A node already exists at the position given by the string positionFromRoot.");
                    }
                    tempNode.setRightChild(new TreeNode(key, tempNode));
                    break;
                default:
                    throw new Exception("String positionFromRoot has an invalid character.");
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
            if (tempNode != null && tempNode.hasLeftChild()) { // checks that node isn't null and has a left child
                queue.addLast(tempNode.getLeftChild());
            }
            if (tempNode != null && tempNode.hasRightChild()) { // checks that node isn't null and has a right child
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

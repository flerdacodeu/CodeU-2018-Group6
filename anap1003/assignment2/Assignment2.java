package assignment2;

import java.util.LinkedList;
import java.util.List;

/**
 * The class has two main methods: getAncestorList and findCommonAncestor.
 * Method getAncestorList is the solution to Q1.
 * Method findCommonAncestor is the solution Q2.
 */
public class Assignment2 {

    /**
     * Stores all ancestors of a given node in a list. If the node is the root,
     * the list of ancestors is empty.
     * @param node
     * @return 
     */
    public static List<TreeNode> getAncestorList(TreeNode node) {
        if (node == null) {
            return null;
        }
        
        TreeNode ancestorNode = node;
        List<TreeNode> ancestorsList = new LinkedList();
        
        while ((ancestorNode = ancestorNode.getParent()) != null) {
            ancestorsList.add(ancestorNode);
        }
        
        return ancestorsList;
    }

    /**
     * First we find the depths of the nodes. Whichever node is 'deeper', we trace
     * back through its ancestors to the level of the other node. From there, we
     * check the nodes for equality. If they are not equal, we move both nodes
     * up by one level, and repeat the process until the nodes are equal.
     * As an optimization for the case where one of the given nodes is the root 
     * of the tree, we check if node depth is 0 (a.k.a. the node is the root) and
     * proceed to return the root immediately, since it is guaranteed to be the 
     * lowest common ancestor.
     * @param firstNode
     * @param secondNode
     * @return 
     */
    public static TreeNode findCommonAncestor(TreeNode firstNode, TreeNode secondNode){
        if (firstNode == null || secondNode == null) {
            return null;
        }
        
        int firstDepth, secondDepth;
        firstDepth = BinaryTree.getNodeDepth(firstNode);
        secondDepth = BinaryTree.getNodeDepth(secondNode);
        
        if (firstDepth == 0) { // firstNode is the root, guaranteed to be the common ancestor
            return firstNode;
        }
        
        if (secondDepth == 0) { // secondNode is the root, guaranteed to be the common ancestor
            return secondNode;
        }
        
        // firstNode deeper, traces back through tree to reach secondNode level
        while (firstDepth > secondDepth) {
            firstNode = firstNode.getParent();
            firstDepth--;
        }
        
        // secondNode deeper, traces back through tree to reach firstNode level
        while (secondDepth > firstDepth) {
            secondNode = secondNode.getParent();
            secondDepth--;
        }
        
        while (!firstNode.getKey().equals(secondNode.getKey())) {
            firstNode = firstNode.getParent();
            secondNode = secondNode.getParent();
        }
        
        return firstNode;
        
    }
    
}

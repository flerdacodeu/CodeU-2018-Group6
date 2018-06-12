import assignment2.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class Assignment2_Test {
    
    private BinaryTree buildTree() throws Exception {
        BinaryTree<Integer> tree = new BinaryTree<>();

        tree.addNode(7, "");
        tree.addNode(3, "L");   //       7
        tree.addNode(4, "R");   //      / \
        tree.addNode(2, "LL");  //     3   4
        tree.addNode(5, "LR");  //    / \   \
        tree.addNode(8, "RR");  //   2   5   8
        tree.addNode(1, "LLL"); //  / \
        tree.addNode(6, "LLR"); // 1   6
        
        return tree;
    }
    
    @Test
    public void testAssignmentExampleQ1() throws Exception {
        BinaryTree<Integer> testingTree = buildTree();

        List<Integer> ancestorsOf6 = Arrays.asList(2, 3, 7);
        List<Integer> ancestorsOf2 = Arrays.asList(3, 7);
        List<Integer> ancestorsOf4 = Arrays.asList(7);
        
        
        List<TreeNode> ancestorList = Assignment2.getAncestorList(testingTree.getNodeLvlOrder(6));
        List<Integer> ancestorKeyList = new LinkedList();
        for (TreeNode ancestor : ancestorList) {
            ancestorKeyList.add((Integer) ancestor.getKey());
        }
        assertEquals(ancestorKeyList, ancestorsOf6);
        
        ancestorList = Assignment2.getAncestorList(testingTree.getNodeLvlOrder(2));
        ancestorKeyList = new LinkedList();
        for (TreeNode ancestor : ancestorList) {
            ancestorKeyList.add((Integer) ancestor.getKey());
        }
        assertEquals(ancestorKeyList, ancestorsOf2);
        
        ancestorList = Assignment2.getAncestorList(testingTree.getNodeLvlOrder(4));
        ancestorKeyList = new LinkedList();
        for (TreeNode ancestor : ancestorList) {
            ancestorKeyList.add((Integer) ancestor.getKey());
        }
        assertEquals(ancestorKeyList, ancestorsOf4);
        
        ancestorList = Assignment2.getAncestorList(testingTree.getNodeLvlOrder(7));
        ancestorKeyList = new LinkedList();
        for (TreeNode ancestor : ancestorList) {
            ancestorKeyList.add((Integer) ancestor.getKey());
        }
        assertEquals(ancestorKeyList.isEmpty(), true); // root has no ancestors, so the ancestorList is empty
    }
    
    @Test
    public void testAssignmentExampleQ2() throws Exception {
        BinaryTree<Integer> testingTree = buildTree();

        Integer commonAncestor6_4 = 7;
        Integer commonAncestor6_5 = 3;
        Integer commonAncestor1_7 = 7;
        
        // common ancestors of nodes that exist in the tree
        assertEquals(commonAncestor6_4, Assignment2.findCommonAncestor(testingTree.getNodeLvlOrder(6), testingTree.getNodeLvlOrder(4)).getKey());
        assertEquals(commonAncestor6_5, Assignment2.findCommonAncestor(testingTree.getNodeLvlOrder(6), testingTree.getNodeLvlOrder(5)).getKey());
        assertEquals(commonAncestor1_7, Assignment2.findCommonAncestor(testingTree.getNodeLvlOrder(1), testingTree.getNodeLvlOrder(7)).getKey());
        
        // common ancestor of the root and a node that doesn't exist in the tree (given as a null value to the findCommonAncestormethod)
        assertEquals(null, Assignment2.findCommonAncestor(testingTree.getNodeLvlOrder(7), testingTree.getNodeLvlOrder(0)));
        
        // common ancestor of a node that exists in the tree and a node that doesn't (but is given as a seemingly valid tree node)
        assertEquals(null, Assignment2.findCommonAncestor(testingTree.getNodeLvlOrder(6), new TreeNode(9, new TreeNode(10, null))));

    }

}

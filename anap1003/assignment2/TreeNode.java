package assignment2;

public class TreeNode<T> {
    private T key;
    private TreeNode parent, leftChild, rightChild;

    public TreeNode(T key) {
        this.key = key;
    }

    public TreeNode(T key, TreeNode parent) {
        this.key = key;
        this.parent = parent;
    }

    public TreeNode getParent() {
        return parent;
    }

    public T getKey() {
        return key;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public static boolean hasLeftChild(TreeNode node) {
        return node != null && node.leftChild != null;
    }

    public static boolean hasRightChild(TreeNode node) {
        return node != null && node.rightChild != null;
    }
}

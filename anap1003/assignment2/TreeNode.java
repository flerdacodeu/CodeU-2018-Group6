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
        leftChild.parent = this;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
        rightChild.parent = this; // another solution to protecting the tree layout would be to change the access to protected
    }

    public boolean hasLeftChild() {
        return this.leftChild != null;
    }

    public boolean hasRightChild() {
        return this.rightChild != null;
    }
}

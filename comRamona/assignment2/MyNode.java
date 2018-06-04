/**
 * Class to represent a tree node. Has data and left and right children. Getters and setters in case we
 * need additional checks
 *
 * @param <T> type of data inside node
 */
public class MyNode<T> {
    private T data;
    private MyNode left;
    private MyNode right;

    public MyNode(T data) {
        this.data = data;
        left = null;
        right = null;
    }

    public MyNode getLeft() {
        return left;
    }

    public void setLeft(MyNode left) {
        this.left = left;
    }

    public MyNode getRight() {
        return right;
    }

    public void setRight(MyNode right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }
}

package Assignment1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import static org.junit.Assert.assertTrue;

/*
Implement an algorithm to find the kth to last element of a singly linked list.
 */
public class Question2 {

    /**
     * Return kth to last element of a singly linked list. Assumes we know the size of the linked list
     *
     * @param linkedList singly linked list
     * @param k          index to last element we want
     * @param <E>        generic for the linked list elements
     * @return kth to last element of the list. Returns null if k is larger that the number of elements in the list.
     */
    public static <E> E getKthToLastElementKnownSize(LinkedList<E> linkedList, int k) {
        int n = linkedList.size();
        if (k >= n || k < 0) {
            return null;
        }
        Iterator<E> iterator = linkedList.iterator();
        int pos = 0;
        while (pos != n - k - 1) {
            iterator.next();
            pos += 1;
        }

        return iterator.next();
    }

    /**
     * Return kth to last element of a singly linked list, when the total size is not known.
     *
     * @param linkedList singly linked list
     * @param k          index to last element we want
     * @param <E>        generic for the linked list elements
     * @return kth to last element of the list. Returns null if k is larger that the number of elements in the list.
     */
    public static <E> E getKthToLastElement(LinkedList<E> linkedList, int k) {
        if (k < 0) {
            return null;
        }
        // Use two iterators. The fast one will be k positions ahead the slow ones.
        // When fast one reaches the end, slow one has reached the k-th to last element
        Iterator<E> fastIterator = linkedList.iterator();
        Iterator<E> slowIterator = linkedList.iterator();

        //advance fast iterator
        while (fastIterator.hasNext() && k > 0) {
            fastIterator.next();
            k--;
        }
        if (k != 0) {
            return null;
        }

        E kthToLastElement = null;
        while (fastIterator.hasNext()) {
            fastIterator.next();
            kthToLastElement = slowIterator.next();
        }

        return kthToLastElement;
    }


    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        assertTrue(getKthToLastElement(list, 0) == 10); //last element
        assertTrue(getKthToLastElement(list, 1) == 9); //penultimate element
        assertTrue(getKthToLastElement(list, 4) == 6); //middle element
        assertTrue(getKthToLastElement(list, list.size() - 1) == 1); //first element
        assertTrue(getKthToLastElement(list, list.size()) == null); //k equal to list size
        assertTrue(getKthToLastElement(list, 20) == null); //k larger than list size

        assertTrue(getKthToLastElementKnownSize(list, 0) == 10);
        assertTrue(getKthToLastElementKnownSize(list, 1) == 9);
        assertTrue(getKthToLastElementKnownSize(list, list.size() - 1) == 1);
        assertTrue(getKthToLastElementKnownSize(list, list.size()) == null);
    }
}

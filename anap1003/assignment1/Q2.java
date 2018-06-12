package assignment1;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Class Q2 has one public method 'findKthLastElem', one private method 'outcomeOfFindingKthLastElem'
 * and a main method used for testing.
 * 
 * Method int findKthLastElem(int, LinkedList<Integer>) takes a number, k, 
 * and a LinkedList object and returns the kth to last element of the list, by
 * using two iterators which are k steps apart.
 * Method String outcomeOfFindingKthLastElem(int, LinkedList<Integer>) is used
 * for testing purposes.
 */
public class Q2 {

    public static int findKthLastElem(int k, LinkedList list) {

        int retValue = Integer.MIN_VALUE;

        if (k < 0) {
            return retValue;
        }

        if (k + 1 > list.size()) {
            return retValue;
        }

        // two 'pointers', one that is k steps ahead and reaches the end of the list first
        // leaving the second pointer at exactly k steps before the last element  
        Iterator itrKstepsAhead = list.iterator();
        Iterator itr = list.iterator();

        for (int i = 0; i < k && itrKstepsAhead.hasNext(); i++) {
            itrKstepsAhead.next();
        }

        if (!itrKstepsAhead.hasNext()) {
            return retValue; // k is bigger that the list size, retValue is still Integer.MIN_VALUE
        }

        do {
            retValue = (int) itr.next();
            itrKstepsAhead.next();
        } while (itrKstepsAhead.hasNext());

        return retValue;
    }

    private static String outcomeOfFindingKthLastElem(int k, LinkedList list) {
        String outcomeMessage;
        
        int kthElem = findKthLastElem(k, list);
        if (kthElem != Integer.MIN_VALUE) {
            outcomeMessage = "The " + k + ". to last element is: " + kthElem;
        } else {
            outcomeMessage = "The value of k is not valid!";
        }
        
        return outcomeMessage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args.length >= 2) {
            int k = Integer.parseInt(args[0]);
            LinkedList<Integer> list = new LinkedList();
            for (int i = 1; i < args.length; i++) {
                list.add(Integer.parseInt(args[i]));
            }
            
            System.out.println(outcomeOfFindingKthLastElem(k, list));
            
        } else {
            System.out.println("To find your own kth last element, please provide a number k, followed by a sequence of numbers as program inputs.");

            System.out.println("\n-----------------------------------------------------Testing-----------------------------------------------------\n");

            System.out.print(" == Testing list content: [ ");
            LinkedList<Integer> testingList = new LinkedList();
            for (int i = 0; i < 10; i++) {
                testingList.add(i);
                System.out.print(i + (i < 9 ? "," : "") + " ");
            }
            System.out.println("]\n");

            System.out.print(" --> Invalid input; k is negative: ");
            System.out.println(outcomeOfFindingKthLastElem(-1, testingList));
            
            System.out.print(" --> Invalid input; k is bigger that the list size: ");
            System.out.println(outcomeOfFindingKthLastElem(10, testingList));
            
            System.out.print(" --> Valid input; edgecase; kth elem is the last one: ");
            System.out.println(outcomeOfFindingKthLastElem(0, testingList));
            
            System.out.print(" --> Valid input; edgecase; kth elem is the first one: ");
            System.out.println(outcomeOfFindingKthLastElem(9, testingList));
            
            System.out.print(" --> Valid input; kth elem is in the middle of the list: ");
            System.out.println(outcomeOfFindingKthLastElem(5, testingList));
            
            System.out.println("\n-----------------------------------------------------------------------------------------------------------------");
        }

    }
}

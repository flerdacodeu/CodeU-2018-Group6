/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Ana
 */
public class Q2 {

    private static int q2(int k, LinkedList list) {

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

        for (int i = 0; i < k; i++) {
            itrKstepsAhead.next();
        }
        
        do {            
            retValue = (int) itr.next();
            itrKstepsAhead.next();
        } while (itrKstepsAhead.hasNext());

        return retValue;
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
            int kthElem = q2(k, list);
            if (kthElem != Integer.MIN_VALUE) {
                System.out.println("The " + k + ". element is: " + kthElem);
            } else {
                System.out.println("The value of k is not valid!");
            }
        } else {
            System.out.println("Please provide a number k, followed by a sequence of numbers as program inputs.");
        }

    }
}

package assignment6;

import java.util.LinkedList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class ParkingLotAllMovesTest {

    @Test
    public void testOneCarInRightPLace_returnNoMoves() throws Exception {
        int[] startState = new int[]{0, -1};
        int[] desiredState = new int[]{0, -1};
        ParkingLotAllMoves parkingLot = new ParkingLotAllMoves(startState, desiredState);
        LinkedList<LinkedList<Move>> result = parkingLot.rearrangeCarsInAllPossibilites();
        
        assertEquals(result.size(),1);
        assertEquals(result.get(0).size(),0);
    }

    @Test
    public void testExactlyOneCarInWrongPLace_returnOneMove() throws Exception {
        int[] startState = new int[]{-1, 0};
        int[] desiredState = new int[]{0, -1};
        ParkingLotAllMoves parkingLot = new ParkingLotAllMoves(startState, desiredState);
        LinkedList<LinkedList<Move>> result = parkingLot.rearrangeCarsInAllPossibilites();
        
        assertEquals(result.size(),1);
        assertEquals(result.get(0).size(),1);
        
        System.out.println("\n---testExactlyOneCarInWrongPLace---\n");
        
        for(LinkedList<Move> sequence : result) {
        	System.out.println("Size of next sequence: " + sequence.size());
        	for(Move m : sequence) {
        		System.out.println(m);
        	}
        	System.out.println();
        }
        System.out.println("Total sequences found: " + result.size());

    }

    @Test
    public void testSimpleCase() throws Exception {
        int[] startState = new int[]{0, 1, -1, 2};
        int[] desiredState = new int[]{2, 0, 1, -1};
        ParkingLotAllMoves parkingLot = new ParkingLotAllMoves(startState, desiredState);
        LinkedList<LinkedList<Move>> result = parkingLot.rearrangeCarsInAllPossibilites();
        
        System.out.println("\n---testSimpleCase---\n");
        int minMoves = Integer.MAX_VALUE;
        
        for(LinkedList<Move> sequence : result) {
        	int curSize = sequence.size();
        	System.out.println("Size of next sequence: " + curSize);
        	if(curSize < minMoves)
        		minMoves = curSize;
        	for(Move m : sequence) {
        		System.out.println(m);
        	}
        	System.out.println();
        }
        System.out.println("Total sequences found: " + result.size());
        
        assertEquals(minMoves, 3);
    }
    
    @Test
    public void testSmallReversed() throws Exception {
        int[] startState = new int[]{0, 1, 2, -1};
        int[] desiredState = new int[]{2, 1, 0, -1};
        ParkingLotAllMoves parkingLot = new ParkingLotAllMoves(startState, desiredState);
        LinkedList<LinkedList<Move>> result = parkingLot.rearrangeCarsInAllPossibilites();
        
        assertEquals(result.size(),2);
        assertEquals(result.get(0).size(),3);
        assertEquals(result.get(1).size(),3);
        
        System.out.println("---testReverse---");
                int minMoves = Integer.MAX_VALUE;
        
        for(LinkedList<Move> sequence : result) {
        	int curSize = sequence.size();
        	System.out.println("Size of next sequence: " + curSize);
        	if(sequence.size() < minMoves);
        		minMoves = curSize;
        	for(Move m : sequence) {
        		System.out.println(m);
        	}
        	System.out.println();
        }
        System.out.println("Total sequences found: " + result.size());       
    }

    @Test
    public void testSmallPermutation() throws Exception {
        int[] startState = new int[]{0, 1, 2, -1};
        int[] desiredState = new int[]{2, 0, 1, -1};
        ParkingLotAllMoves parkingLot = new ParkingLotAllMoves(startState, desiredState);
        LinkedList<LinkedList<Move>> result = parkingLot.rearrangeCarsInAllPossibilites();
        
        System.out.println("---testPermutation---");
                int minMoves = Integer.MAX_VALUE;
        
        for(LinkedList<Move> sequence : result) {
        	int curSize = sequence.size();
        	System.out.println("Size of next sequence: " + curSize);
        	if(sequence.size() < minMoves)
        		minMoves = curSize;
        	for(Move m : sequence) {
        		System.out.println(m);
        	}
        	System.out.println();
        }
        
        assertEquals(minMoves, 4);
        System.out.println("Total sequences found: " + result.size());
    }
    
    /*@Test
    public void testPermutation() throws Exception {
        int[] startState = new int[]{0, 1, 2, 3, 4, 5, -1};
        int[] desiredState = new int[]{-1, 0, 1, 2, 3, 4, 5};
        ParkingLotAllMoves parkingLot = new ParkingLotAllMoves(startState, desiredState);
        LinkedList<LinkedList<Move>> result = parkingLot.rearrangeCarsInAllPossibilites();
        
        System.out.println("---testPermutation---");
                int minMoves = Integer.MAX_VALUE;
        
        for(LinkedList<Move> sequence : result) {
        	int curSize = sequence.size();
        	System.out.println("Size of next sequence: " + curSize);
        	if(sequence.size() < minMoves);
        		minMoves = curSize;
        	for(Move m : sequence) {
        		System.out.println(m);
        	}
        	System.out.println();
        }
        System.out.println("Total sequences found: " + result.size());
        
    }

    @Test
    public void testReverse() throws Exception {
        int[] startState = new int[]{0, 1, 2, 3, 4, 5, -1};
        int[] desiredState = new int[]{5, 4, 3, 2, 1, 0, -1};
        ParkingLotAllMoves parkingLot = new ParkingLotAllMoves(startState, desiredState);
        LinkedList<LinkedList<Move>> result = parkingLot.rearrangeCarsInAllPossibilites();
        
        System.out.println("---testReverse---");
                int minMoves = Integer.MAX_VALUE;
        
        for(LinkedList<Move> sequence : result) {
        	int curSize = sequence.size();
        	System.out.println("Size of next sequence: " + curSize);
        	if(sequence.size() < minMoves);
        		minMoves = curSize;
        	for(Move m : sequence) {
        		System.out.println(m);
        	}
        	System.out.println();
        }
        System.out.println("Total sequences found: " + result.size());
        
    }
    
    @Test
    public void testCycles() throws Exception {
        int[] startState = new int[]{1, 3, 0, 4, 2, -1, 8, 7, 5, 6};
        int[] desiredState = new int[]{0, 1, 2, 3, 4, -1, 5, 6, 7, 8};
        ParkingLotAllMoves parkingLot = new ParkingLotAllMoves(startState, desiredState);
        LinkedList<LinkedList<Move>> result = parkingLot.rearrangeCarsInAllPossibilites();
        
        System.out.println("---testCycles---");
                int minMoves = Integer.MAX_VALUE;
        
        for(LinkedList<Move> sequence : result) {
        	int curSize = sequence.size();
        	System.out.println("Size of next sequence: " + curSize);
        	if(sequence.size() < minMoves);
        		minMoves = curSize;
        	for(Move m : sequence) {
        		System.out.println(m);
        	}
        	System.out.println();
        }
        System.out.println("Total sequences found: " + result.size());
        
    }*/
    

    @Test(expected = Exception.class)
    public void givenInvalidStartState_throwError() throws Exception {
        int[] startState = new int[]{0, 1, 2, 2};
        int[] desiredState = new int[]{2, 1, 0, -1};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState);
        parkingLot.rearrangeCars();
    }

    @Test(expected = Exception.class)
    public void givenInvalidEndState_throwError() throws Exception {
        int[] startState = new int[]{0, 1, -1};
        int[] desiredState = new int[]{-1, 0, 2};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState);
        parkingLot.rearrangeCars();
    }
    
    @Test(expected = Exception.class)
    public void givenInvalidFreeSpaces_throwError() throws Exception {
        int[] startState = new int[]{0, -1, -1};
        int[] desiredState = new int[]{-1, 0, 1};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState);
        parkingLot.rearrangeCars();
    } 

}
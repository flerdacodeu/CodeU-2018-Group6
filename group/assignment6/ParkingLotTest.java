package assignment6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ParkingLotTest {

	// Tests with no constraints.
    @Test
    public void testOneCarInRightPLace_returnNoMoves() throws Exception {
        int[] startState = new int[]{0, -1};
        int[] desiredState = new int[]{0, -1};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, null);
        parkingLot.rearrangeCars();
        assertArrayEquals(desiredState, parkingLot.getEndState());
        assertEquals(0, parkingLot.getMoves().size());
    }

    @Test
    public void testExactlyOneCarInWrongPLace_returnOneMove() throws Exception {
        int[] startState = new int[]{-1, 0};
        int[] desiredState = new int[]{0, -1};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, null);
        parkingLot.rearrangeCars();
        assertArrayEquals(desiredState, parkingLot.getEndState());
		assertEquals(1, parkingLot.getMoves().size());
        
        System.out.println("\nExactly one car in wrong place test => move size: " + parkingLot.getMoves().size());
        parkingLot.printMoves();
    }

    @Test
    public void testSimpleCase() throws Exception {
        int[] startState = new int[]{0, 1, -1, 2};
        int[] desiredState = new int[]{2, 0, 1, -1};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, null);
        parkingLot.rearrangeCars();
        assertArrayEquals(desiredState, parkingLot.getEndState());
     
        System.out.println("\nSimple case test => move size: " + parkingLot.getMoves().size());
        parkingLot.printMoves();
    }

    @Test
    public void testPermutation() throws Exception {
        int[] startState = new int[]{0, 1, 2, 3, 4, 5, -1};
        int[] desiredState = new int[]{-1, 0, 1, 2, 3, 4, 5};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, null);
        parkingLot.rearrangeCars();
        assertArrayEquals(desiredState, parkingLot.getEndState());

        System.out.println("\nPermutation test => move size: " + parkingLot.getMoves().size());
        parkingLot.printMoves();
    }

    @Test
    public void testReverse() throws Exception {
        int[] startState = new int[]{0, 1, 2, 3, 4, 5, -1};
        int[] desiredState = new int[]{5, 4, 3, 2, 1, 0, -1};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, null);            
        parkingLot.rearrangeCars();
        assertArrayEquals(desiredState, parkingLot.getEndState());
        
        System.out.println("\nReverse test => move size: " + parkingLot.getMoves().size());
        parkingLot.printMoves();
    }
    
    @Test
    public void testCycles() throws Exception {
        int[] startState = new int[]{1, 3, 0, 4, 2, -1, 8, 7, 5, 6};
        int[] desiredState = new int[]{0, 1, 2, 3, 4, -1, 5, 6, 7, 8};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, null);
        parkingLot.rearrangeCars();
        assertArrayEquals(desiredState, parkingLot.getEndState());
        
        System.out.println("\nCycles test => move size: " + parkingLot.getMoves().size());
        parkingLot.printMoves();
    }

    @Test(expected = Exception.class)
    public void givenInvalidStartState_throwError() throws Exception {
        int[] startState = new int[]{0, 1, 2, 2};
        int[] desiredState = new int[]{2, 1, 0, -1};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, null);
        parkingLot.rearrangeCars();
    }

    @Test(expected = Exception.class)
    public void givenInvalidEndState_throwError() throws Exception {
        int[] startState = new int[]{0, 1, -1};
        int[] desiredState = new int[]{-1, 0, 2};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, null);
        parkingLot.rearrangeCars();
    }
    
    @Test(expected = Exception.class)
    public void givenInvalidFreeSpaces_throwError() throws Exception {
        int[] startState = new int[]{0, -1, -1};
        int[] desiredState = new int[]{-1, 0, 1};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, null);
        parkingLot.rearrangeCars();
    }
    
    // Tests with constraints.
    @Test(expected = Exception.class)
    public void givenInvalidStartStateAndConstraints_throwError() throws Exception {
        int[] startState = new int[]{0, 1, 2, -1};
        int[] desiredState = new int[]{2, 1, 0, -1};
        List<Integer>[] constraints = new ArrayList[]{
          new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList() 
        };
        constraints[0].add(0);
        constraints[1].add(2);
        
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, constraints);
        parkingLot.rearrangeCars();
    }
    
    @Test(expected = Exception.class)
    public void givenInvalidEndStateAndConstraints_throwError() throws Exception {
        int[] startState = new int[]{0, 1, 2, -1};
        int[] desiredState = new int[]{2, 1, 0, -1};
        List<Integer>[] constraints = new ArrayList[]{
          new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList() 
        };
        constraints[0].add(2);
        constraints[1].add(0);
        
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, constraints);
        parkingLot.rearrangeCars();
    }
    
    @Test(expected = Exception.class)
    public void  noPossibleMove_throwError()  throws Exception {
		int[] startState = new int[]{0, -1, 1};
        int[] desiredState = new int[]{1, -1, 0};
        List<Integer> constraintsSpace1 = Arrays.asList(1,0);
        List[] forbiddenParkingSlots = new List[startState.length];
        forbiddenParkingSlots[1] = constraintsSpace1;
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, forbiddenParkingSlots);
        parkingLot.rearrangeCars();
    }
    
    @Test
    public void testOneConstraintSmallList() throws Exception {
        int[] startState = new int[]{0, -1, 1};
        int[] desiredState = new int[]{-1, 1, 0};
        List<Integer> constraintsSpace0 = Arrays.asList(1);
        List[] forbiddenParkingSlots = new List[startState.length];
        forbiddenParkingSlots[0] = constraintsSpace0;
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, forbiddenParkingSlots);
        parkingLot.rearrangeCars();
        
        System.out.println("\nOne small constraint test => move size: " + parkingLot.getMoves().size());
        parkingLot.printMoves();   
    }
    
    @Test
    public void testConstraints2() throws Exception {
		int[] startState = new int[]{0, -1, 1, 2};
        int[] desiredState = new int[]{1, 2, 0, -1};
        List<Integer> constraintsSpace3 = Arrays.asList(1,0);
        List[] forbiddenParkingSlots = new List[startState.length];
        forbiddenParkingSlots[1] = constraintsSpace3;
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, forbiddenParkingSlots);
        parkingLot.rearrangeCars();	        
        
        System.out.println("\nConstraints 2 test => move size: " + parkingLot.getMoves().size());
        parkingLot.printMoves();	 
    }
    
    @Test
    public void testConstraints3() throws Exception {
		int[] startState = new int[]{0, -1, 1, 2};
        int[] desiredState = new int[]{1, 2, 0, -1};
        List<Integer> constraintsSpace3 = Arrays.asList(1,0);
        List[] forbiddenParkingSlots = new List[startState.length];
        forbiddenParkingSlots[3] = constraintsSpace3;
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, forbiddenParkingSlots);
        parkingLot.rearrangeCars();
        List<Move> result = parkingLot.getMoves();
        
        System.out.println("\nConstraints 3 test => move size: " + parkingLot.getMoves().size());
        parkingLot.printMoves();	        	         
    }
    
    @Test
    public void testConstraintsMedium() throws Exception {
        int[] startState = new int[]{0, -1, 1, 2, 3};
        int[] desiredState = new int[]{1, -1, 2, 3, 0};
        List<Integer> constraintsSpace1 = Arrays.asList(0,2);
        List[] forbiddenParkingSlots = new List[startState.length];
        forbiddenParkingSlots[1] = constraintsSpace1;
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, forbiddenParkingSlots);
        parkingLot.rearrangeCars();
        
        System.out.println("\nConstraints medium test => move size: " + parkingLot.getMoves().size());
        parkingLot.printMoves();	 
    }
   
    @Test
    public void testConstraintsWithPossibleLoop() throws Exception {
        int[] startState = new int[]{0, -1, 1, 4, 2, 3, 5};
        int[] desiredState = new int[]{2, 4, 3, 1, 5, -1, 0};
        List<Integer> constraintsSpace5 = Arrays.asList(0,2,5);
        List[] forbiddenParkingSlots = new List[startState.length];
        forbiddenParkingSlots[5] = constraintsSpace5;
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, forbiddenParkingSlots);
        parkingLot.rearrangeCars();
        List<Move> result = parkingLot.getMoves();
        
        System.out.println("\nConstraints longer with possible loop test => move size: " + parkingLot.getMoves().size());
        parkingLot.printMoves();	
    }
    
    @Test
    public void testConstraintsInMoreSpaces() throws Exception {
        int[] startState = new int[]{0, -1, 1, 4, 2, 3, 5};
        int[] desiredState = new int[]{2, 4, 3, 1, 5, -1, 0};
        List<Integer> constraintsSpace5 = Arrays.asList(0,2,5);
        List<Integer> constraintsSpace3 = Arrays.asList(2);
        List[] forbiddenParkingSlots = new List[startState.length];
        forbiddenParkingSlots[3] = constraintsSpace3;
        forbiddenParkingSlots[5] = constraintsSpace5;
        ParkingLot parkingLot = new ParkingLot(startState, desiredState, forbiddenParkingSlots);
        parkingLot.rearrangeCars();
        List<Move> result = parkingLot.getMoves();
        
        System.out.println("\nConstraints in more spaces test => move size: " + parkingLot.getMoves().size());
        parkingLot.printMoves();	
    }
}
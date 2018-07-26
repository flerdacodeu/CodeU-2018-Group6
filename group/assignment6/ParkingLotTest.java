package assignment6;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ParkingLotTest {

    @Test
    public void testOneCarInRightPLace_returnNoMoves() {
        int[] startState = new int[]{0, -1};
        int[] desiredState = new int[]{0, -1};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState);
        assertArrayEquals(desiredState, parkingLot.getEndState());
        assertEquals(0, parkingLot.getMoves().size());
    }

    @Test
    public void testExactlyOneCarInWrongPLace_returnOneMove() {
        int[] startState = new int[]{-1, 0};
        int[] desiredState = new int[]{0, -1};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState);
        assertArrayEquals(desiredState, parkingLot.getEndState());
        assertEquals(1, parkingLot.getMoves().size());
    }

    @Test
    public void testSimpleCase() {
        int[] startState = new int[]{0, 1, -1, 2};
        int[] desiredState = new int[]{2, 0, 1, -1};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState);
        assertArrayEquals(desiredState, parkingLot.getEndState());
    }


    @Test
    public void testPermutation() {
        int[] startState = new int[]{0, 1, 2, 3, 4, 5, -1};
        int[] desiredState = new int[]{-1, 0, 1, 2, 3, 4, 5};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState);
        assertArrayEquals(desiredState, parkingLot.getEndState());
    }

    @Test
    public void testReverse() {
        int[] startState = new int[]{0, 1, 2, 3, 4, 5, -1};
        int[] desiredState = new int[]{5, 4, 3, 2, 1, 0, -1};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState);
        assertArrayEquals(desiredState, parkingLot.getEndState());
    }

    @Test(expected = AssertionError.class)
    public void givenInvalidStartState_throwError() {
        int[] startState = new int[]{0, 1, 2, 2};
        int[] desiredState = new int[]{2, 1, 0, -1};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState);
        assertArrayEquals(desiredState, parkingLot.getEndState());
    }

    @Test(expected = AssertionError.class)
    public void givenInvalidEndState_throwError() {
        int[] startState = new int[]{0, 1, -1};
        int[] desiredState = new int[]{-1, 0, 2};
        ParkingLot parkingLot = new ParkingLot(startState, desiredState);
        assertArrayEquals(desiredState, parkingLot.getEndState());
    }
}
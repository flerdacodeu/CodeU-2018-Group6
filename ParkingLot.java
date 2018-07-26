import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


/**
There is a parking lot with N spaces and N-1 cars in it. Your task is to write an algorithm 
to rearrange the cars in a given way. Only one car can be moved at a time to the empty slot
*/
public class ParkingLot {
    /**
    We have assummed cars have ids from 0 to N-2 and parking lots have ids from 0 to N-1. (both inclusive).
    We represent the parking lot state as an array, where the content of each position p is the id of the car
    situated on parking lot position p. An id of -1 represents an empty parking lot.
    */
    private int emptySpotIndex = -1;
    private int[] parkingLotCurrent; //car in each position of the parking lot
    private int[] carToPosition; //current position of each car
    private List<String> moves;

    public ParkingLot(int[] parkingLotCurrent, int[] parkingLotEnd) {
        checkValidInput(parkingLotCurrent, parkingLotEnd);
        int N = parkingLotCurrent.length;
        carToPosition = new int[N - 1];
        this.parkingLotCurrent = parkingLotCurrent;
        moves = new ArrayList<>();
        if (initialEqualsFinalFreeSpace(parkingLotCurrent, parkingLotEnd)) {
        	for (int i = 0; i < N-1; i++) {
                if (parkingLotCurrent[i] == -1) {
                    if (i == 0) {
                    	int counter = i+1;
                    	while (counter<N-1 && parkingLotCurrent[counter] == parkingLotEnd[counter])
                    	{counter++;}
                    	if (counter!=N-1) {
                    		int temp = parkingLotCurrent[i];
                    		parkingLotCurrent[i] = parkingLotCurrent[counter];
                    		parkingLotCurrent[counter] = temp;
                    	}
                    } else {
                    	int counter = i-1;
                    	while (counter>0 && parkingLotCurrent[counter] == parkingLotEnd[counter])
                    	{counter--;}
                    	int temp = parkingLotCurrent[i];
                    	parkingLotCurrent[i] = parkingLotCurrent[i-1];
                    	parkingLotCurrent[i-1] = temp;
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {

            if (parkingLotCurrent[i] == -1) {
                emptySpotIndex = i;
            } else {
                carToPosition[parkingLotCurrent[i]] = i;
            }
        }
        // iterate through, swap the car currently occupying space i with the desired end state car
        // using the emptySpace for swapping when necessary
        for (int i = 0; i < N; i++) {
            int currentCar = parkingLotCurrent[i];
            int desiredCar = parkingLotEnd[i];
            if (currentCar != desiredCar) {
                // don't do anything if space is already empty
                if (currentCar != -1) {
                    moves.add(String.format("move Car%s from Space%s to Space%s", currentCar, i, emptySpotIndex));
                    parkingLotCurrent[emptySpotIndex] = currentCar;
                    carToPosition[currentCar] = emptySpotIndex;
                    parkingLotCurrent[i] = -1;
                    emptySpotIndex = i;
                }
                // don't need to do anything if space should be empty
                if (desiredCar != -1) {
                    int desiredCarPosition = carToPosition[desiredCar];
                    moves.add(String.format("move Car%s from Space%s to Space%s", desiredCar, desiredCarPosition, i));
                    parkingLotCurrent[i] = desiredCar;
                    parkingLotCurrent[desiredCarPosition] = -1;
                    emptySpotIndex = desiredCarPosition;
                    carToPosition[desiredCar] = i;
                }

            }
        }
        for (String move : moves) {
            System.out.println(move);
        }
    }

    public static void checkValidInput(int[] start, int[] end) {
        assertEquals(start.length, end.length);
        boolean[] found = new boolean[start.length];
        for (int i = 0; i < start.length; i++) {
            int element = start[i];
            assertTrue(element >= -1 && element < start.length - 1);
            assertFalse("Duplicate car found, invalid input.", found[element + 1]);
            found[element + 1] = true;
        }

        found = new boolean[start.length];
        for (int i = 0; i < end.length; i++) {
            int element = end[i];
            assertTrue(element >= -1 && element < end.length - 1);
            assertFalse("Duplicate car found, invalid input.", found[element + 1]);
            found[element + 1] = true;
        }
    }
    
	public boolean initialEqualsFinalFreeSpace(int[] start, int[] end) {
    	for (int i = 0; i < start.length; i++) {

            if (start[i] == -1 && end[i] == -1) {
                return true;
            }
        }
    	return false;
    }

    public List<String> getMoves() {
        return moves;
    }

    public int[] getEndState() {
        return parkingLotCurrent;
    }
}

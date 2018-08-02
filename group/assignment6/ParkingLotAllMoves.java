package assignment6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * There is a parking lot with N spaces and N-1 cars in it. Your task is to
 * write an algorithm to rearrange the cars in a given way. Only one car can be
 * moved at a time to the empty slot.
 */
public class ParkingLotAllMoves {

    /**
     * We have assumed cars have ids from 0 to N-2 and parking lots have ids
     * from 0 to N-1. (both inclusive). We represent the parking lot state as an
     * array, where the content of each position p is the id of the car situated
     * on parking lot position p. An id of -1 represents an empty parking lot.
     */
    private int currentEmptySpotIndex = -1;
    private int endEmptySpotIndex = -1;
    private int[] parkingLotCurrent; //car in each position of the parking lot
    private int[] parkingLotEnd;  // final desired state of the parking lot
    private int parkingLotSize;
    private LinkedList<LinkedList<Move>> allSequencesOfMoves;

    
    public ParkingLotAllMoves(int[] parkingLotCurrent, int[] parkingLotEnd) {

        this.parkingLotSize = parkingLotCurrent.length;
        this.parkingLotCurrent = parkingLotCurrent;
        this.parkingLotEnd = parkingLotEnd;
        this.allSequencesOfMoves = new LinkedList<LinkedList<Move>>();
    }

    public LinkedList<LinkedList<Move>> rearrangeCarsInAllPossibilites() throws Exception {

        checkValidInputAndInitFreeSpaces();
        
        HashMap<Integer, Integer> initialStateMap = new HashMap<>();
    	for(int i = 0; i < parkingLotSize; i++) {
    	  	initialStateMap.put(i, parkingLotCurrent[i]);
    	}
    	
    	HashSet<HashMap<Integer, Integer>> allCurrentSeenStates = new HashSet<HashMap<Integer, Integer>>();
    	allCurrentSeenStates.add(initialStateMap);	
    	
    	generateAllMoves(initialStateMap, currentEmptySpotIndex, new LinkedList<Move>(), allCurrentSeenStates);
    	return allSequencesOfMoves;
    }

    private void checkValidInputAndInitFreeSpaces() throws Exception {

        if (parkingLotCurrent.length != parkingLotEnd.length) {
            throw new Exception("The length of start and end states do not match.");
        }

        checkValidParkingState(parkingLotCurrent);
        checkValidParkingState(parkingLotEnd);
    }

    private void checkValidParkingState(int[] parkingLot) throws Exception {

        int N = parkingLot.length;
        boolean[] found = new boolean[parkingLot.length];
        int countFreeSpaces = 0;

        for (int i = 0; i < N; i++) {
            int parkedCarId = parkingLot[i];

            // Check cars are represented with valid integers.
            if (parkedCarId < -1 || parkedCarId > N - 1) {
                throw new Exception("Car id is represented with invalid number.");
            }

            // Check only the parking lot has only one free space.
            if (parkedCarId == -1) {
                countFreeSpaces++;
                if (countFreeSpaces > 1) {
                    throw new Exception("Too many free spaces.");
                }
                if (currentEmptySpotIndex == -1) {
                    currentEmptySpotIndex = i;
                } else {
                    endEmptySpotIndex = i;
                }
            }

            // Check parking lot has unique car ids.
            if (found[parkedCarId + 1]) {
                throw new Exception("Duplicate car found, invalid input.");
            }
            found[parkedCarId + 1] = true;
        }
    }
        
    // Iterate through the space, move each car (not in right position) to free space and start again until we reach the final state.
    private void generateAllMoves(HashMap<Integer, Integer> currentState, int currentFreeSpace, LinkedList<Move> currentMovesInThisSequence, HashSet<HashMap<Integer, Integer>> allSeenStatesInThisSequence){
    	if(isStateFinal(currentState)){
    		allSequencesOfMoves.add(currentMovesInThisSequence);
    		return;
    	}
    	
    	for(int i = 0; i < parkingLotSize; i++){    		
    		if(i != currentFreeSpace){		
     			int car = currentState.get(i);
     			// Don't move cars already in correct position.
     			if (car != parkingLotEnd[i]) {
     		    	// Move every other car to free space.
     				int tmpFreeSpace = currentFreeSpace;
        			int to = tmpFreeSpace;
        			
        			// Copy the current state and do the move.
        			HashMap<Integer, Integer> resultingState = new HashMap<>();
        			resultingState.putAll(currentState);
        			
        			resultingState.replace(tmpFreeSpace, car);
        			resultingState.replace(i, -1);
        			tmpFreeSpace = i;

        			// Check this move does not lead to an already seen state for this current sequence
        			if(!allSeenStatesInThisSequence.contains(resultingState)){
        				
        				Move move = new Move(car, i, to, resultingState);        	
        				
        				// Copy the current list of moves and add the new move.
        				LinkedList<Move> resultingCurrentMovesSequence = new LinkedList<>();
        				resultingCurrentMovesSequence.addAll(currentMovesInThisSequence);			
        				resultingCurrentMovesSequence.add(move);
        				      
        				// Copy the current list of seen states and add the new state.
        				HashSet<HashMap<Integer, Integer>> resultingSeenStates = new HashSet<HashMap<Integer, Integer>>();
        				resultingSeenStates.addAll(allSeenStatesInThisSequence);
        				resultingSeenStates.add(resultingState);
        					 				
        				// Generates next moves from this new state.
        				generateAllMoves(resultingState, tmpFreeSpace, resultingCurrentMovesSequence, resultingSeenStates);   
        			}    			
    			}  
    		}
    	}
    }
    
    private boolean isStateFinal(HashMap<Integer, Integer> parkingLotState){
    	for(int i = 0; i < parkingLotSize; i++)
    		if(parkingLotEnd[i] != parkingLotState.get(i))
    			return false;
    	return true;
    }
   
}

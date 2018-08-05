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
public class ParkingLot {

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
    private int[] carToPosition; //current position of each car
    private List[] forbiddenParkingSpaces; // list of which cars are forbbiden in each spot.
    private int parkingLotSize;
    private List<Move> movesNoConstraints;
    private LinkedList<LinkedList<Move>> movesConstraints;

    public ParkingLot(int[] parkingLotCurrent, int[] parkingLotEnd, List[] forbiddenParkingSlots) {

        this.parkingLotSize = parkingLotCurrent.length;
        this.parkingLotCurrent = parkingLotCurrent;
        this.parkingLotEnd = parkingLotEnd;
        this.forbiddenParkingSpaces = forbiddenParkingSlots;
        movesNoConstraints = new ArrayList<>();
    }

    public void rearrangeCars() throws Exception {

        checkValidInputAndInitFreeSpaces();

        initCarToPosition();
        
        if(forbiddenParkingSpaces != null) {
        	// Initialiase auxiliary variables for the recursive method to compute moves with constraints.
        	this.movesConstraints = new LinkedList<LinkedList<Move>>();
            HashMap<Integer, Integer> initialStateMap = new HashMap<>();
        	for(int i = 0; i < parkingLotSize; i++) {
        	  	initialStateMap.put(i, parkingLotCurrent[i]);
        	}
        	
        	HashSet<HashMap<Integer, Integer>> allCurrentSeenStates = new HashSet<HashMap<Integer, Integer>>();
        	allCurrentSeenStates.add(initialStateMap);	
        	
        	doAllMovesWithConstraints(initialStateMap, currentEmptySpotIndex, new LinkedList<Move>(), allCurrentSeenStates);
        	// No possible sequence of moves has been found.
        	if(movesConstraints.size() == 0) {
    			throw new Exception("Out of options, constraints make the moves impossible.");
    		}
        }
       
        // No constraints
        else {
        	doAllMovesNoConstraints();
        }
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

            // check if car shouldn't be in current parking slot
            if (forbiddenParkingSpaces != null && forbiddenParkingSpaces[i] != null && carForbiddenToParkInSpace(parkedCarId, forbiddenParkingSpaces[i])) {
                throw new Exception("Car is parked in a forbidden parking space.");
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

    // See if car appears in the forbbiden list for the current spot checked.
    private boolean carForbiddenToParkInSpace(int carId, List<Integer> carsForbiddenToParkInSpace) {
    	if(carsForbiddenToParkInSpace == null)
    		return true;
        for (Integer currentCarId : carsForbiddenToParkInSpace) {
            if (currentCarId == carId) {
                return true;
            }
        }
        return false;
    }

    // For each car, save its current position.
    private void initCarToPosition() {

        carToPosition = new int[parkingLotSize - 1];
        for (int i = 0; i < parkingLotSize; i++) {
            if (parkingLotCurrent[i] != -1) {
                carToPosition[parkingLotCurrent[i]] = i;
            }
        }
    }

    private void doAllMovesNoConstraints() {

        // Iterate through and check whether current and end state match.
        // While possible, reduce the number of moves by trying to move to the current free space the car that should be there in the end.
        // When the current free spot matches the end free spot, swap the the car at position i with the car that is supposed to be at that
        // position by making two moves. This will change the position of the free space again and trigger the while loop in the next iteration
        // minimizing the number of moves need to rearrage the cars at the parking lot.
        for (int i = 0; i < parkingLotSize; i++) {
            // If possible, firstly move to the current free space the car that should be there in the end.
            // this while reduces the number of moves from 12 to 6 for testPermutation
            // and from 5 to 3 in testSimpleCase.
            while (currentEmptySpotIndex != endEmptySpotIndex) {
                int desiredCar = parkingLotEnd[currentEmptySpotIndex];
                int currentDesiredCarPosition = carToPosition[desiredCar];
                doOneMove(desiredCar, currentDesiredCarPosition, currentEmptySpotIndex);
            }

            int currentCar = parkingLotCurrent[i];
            int desiredCar = parkingLotEnd[i];
            if (currentCar != desiredCar) {
                // move from the space, only if it's not already empty
                if (currentCar != -1) {
                    doOneMove(currentCar, i, currentEmptySpotIndex);
                }
                // move to the space, only if it should not be empty
                if (desiredCar != -1) {
                    int desiredCarPosition = carToPosition[desiredCar];
                    doOneMove(desiredCar, desiredCarPosition, i);
                }
            }
        }
    }

    private void doOneMove(int carId, int fromSpace, int toSpace) {
        parkingLotCurrent[toSpace] = carId;
        parkingLotCurrent[fromSpace] = -1;
        currentEmptySpotIndex = fromSpace;
        carToPosition[carId] = toSpace;
        
        movesNoConstraints.add(new Move(carId, fromSpace, toSpace, parkingLotCurrent));
     }
    
    // Iterate through the space, move each car (not in right position) to free space and start again until we reach the final state.
    // Only do moves that are possible.
    private void doAllMovesWithConstraints(HashMap<Integer, Integer> currentState, int currentFreeSpace, LinkedList<Move> currentMovesInThisSequence, HashSet<HashMap<Integer, Integer>> allSeenStatesInThisSequence){
    	if(isStateFinal(currentState)){
    		// We found a possible solution.
    		movesConstraints.add(currentMovesInThisSequence);
    		return;
    	}
    	
    	// One solution already found, no need to search for more.
    	if(movesConstraints.size() != 1) {
    		for(int i = 0; i < parkingLotSize; i++){    		
        		if(i != currentFreeSpace){		
         			int car = currentState.get(i);
         			// Don't move cars already in correct position.
         			if (car != parkingLotEnd[i]) {
         		    	// Move every other car to free space.
         				int tmpFreeSpace = currentFreeSpace;
            			int to = tmpFreeSpace;          			
            			// If move is valid, check the state was not seen before and continue moving cars.
            			if(isValidMove(car, to)) {
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
                				doAllMovesWithConstraints(resultingState, tmpFreeSpace, resultingCurrentMovesSequence, resultingSeenStates);                  				   				
                			}    			
            			}        			
        			}  
        		}
    		}
    	}
    }
    
    private boolean isValidMove(int car, int to) {
    	if(forbiddenParkingSpaces[to] == null)
    		return true;
    	return !forbiddenParkingSpaces[to].contains(car);
    }
    
    private boolean isStateFinal(HashMap<Integer, Integer> parkingLotState){
    	for(int i = 0; i < parkingLotSize; i++)
    		if(parkingLotEnd[i] != parkingLotState.get(i))
    			return false;
    	return true;
    }
   
    public void printMoves() throws Exception {
    	List<Move> finalMoves = getMoves();
        for (Move move : finalMoves) {
            System.out.println(move);
        }
    }

    public List<Move> getMoves() throws Exception {
    	if(forbiddenParkingSpaces != null)
    		if(movesConstraints.size() == 0) {
    			throw new Exception("Out of options, constraints make the moves impossible.");
    		}
    		else {
    			return movesConstraints.get(0);
    		}    		
        return movesNoConstraints;
    }

    public int[] getEndState() {
    	if(forbiddenParkingSpaces == null)
    		return parkingLotCurrent;
    	return parkingLotEnd;
    }
}

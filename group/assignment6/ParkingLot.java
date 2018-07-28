package assignment6;
import java.util.ArrayList;
import java.util.List;

/**
There is a parking lot with N spaces and N-1 cars in it. Your task is to write an algorithm 
to rearrange the cars in a given way. Only one car can be moved at a time to the empty slot.
*/
public class ParkingLot {
    /**
    We have assummed cars have ids from 0 to N-2 and parking lots have ids from 0 to N-1. (both inclusive).
    We represent the parking lot state as an array, where the content of each position p is the id of the car
    situated on parking lot position p. An id of -1 represents an empty parking lot.
    */
    private int currentEmptySpotIndex = -1;
    private int endEmptySpotIndex = -1;
    private int[] parkingLotCurrent; //car in each position of the parking lot
    private int[] parkingLotEnd;  // final desired state of the parking lot
    private int[] carToPosition; //current position of each car
    private int parkingLotSize;   
    private List<Move> moves; 

    public ParkingLot(int[] parkingLotCurrent, int[] parkingLotEnd) throws Exception {
    	
        checkValidInput(parkingLotCurrent, parkingLotEnd);
         
        this.parkingLotSize = parkingLotCurrent.length;
        this.parkingLotCurrent = parkingLotCurrent;
        this.parkingLotEnd = parkingLotEnd;
        moves = new ArrayList<>();
        
        initFreeSpaces();      
        initCarToPosition();
        doAllMoves();      
    }

    private static void checkValidInput(int[] start, int[] end) throws Exception {
    	
       	if(start.length != end.length)
    		throw new Exception("The length of start and end states do not match.");
    	
       	checkValidParkingState(start);
       	checkValidParkingState(end);
    }
    
    private static void checkValidParkingState(int[] array) throws Exception {
    	
    	 int N = array.length;
         boolean[] found = new boolean[array.length];
         int countFreeSpaces = 0;
         
    	 for (int i = 0; i < N; i++) {
            int element = array[i];
            
            // Check cars are represented with valid integers.
            if(element < -1 || element > N - 1)
             	throw new Exception("Car id is represented with invalid number.");
            
            // Check only the parking lot has only one free space.
            if(element == -1) {
            	countFreeSpaces++;
            	if(countFreeSpaces > 1)
            		throw new Exception("Too many free spaces.");   
            }             	
            
            // Check parking lot has unique car ids.
         	if(found[element + 1])
         		throw new Exception("Duplicate car found, invalid input.");
            found[element + 1] = true;           
         }       
    }
    
    // Initialize the free space index of the start and end states.
    private void initFreeSpaces() {
    	
    	for (int i = 0; i < parkingLotSize; i++) {
            if(parkingLotCurrent[i] == -1)
            	currentEmptySpotIndex = i;
            if(parkingLotEnd[i] == -1)
            	endEmptySpotIndex = i;
            if(currentEmptySpotIndex != -1 && endEmptySpotIndex != -1)
            	break;
        }
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
	
	private void doAllMoves() {
	
		// If possible, firstly move to the current free space the car that should be there in the end.
		// this while reduces the number of moves from 12 to 6 for testPermutation
		// and from 5 to 3 in testSimpleCase.
		while (currentEmptySpotIndex != endEmptySpotIndex) {		
			int desiredCar = parkingLotEnd[currentEmptySpotIndex];
			int currentDesiredCarPosition = carToPosition[desiredCar];
			doOneMove(desiredCar, currentDesiredCarPosition, currentEmptySpotIndex);
		}
		
		// Then do the rest of the swaps.
		// iterate through, swap the car currently occupying space i with the desired end state car
        // using the emptySpace for swapping when necessary
       for (int i = 0; i < parkingLotSize; i++) {
            int currentCar = parkingLotCurrent[i];
            int desiredCar = parkingLotEnd[i];
            if (currentCar != desiredCar) {
                // don't do anything if space is already empty
                if (currentCar != -1) {
                	doOneMove(currentCar, i, currentEmptySpotIndex);
                }
                // don't need to do anything if space should be empty
                if (desiredCar != -1) {
                    int desiredCarPosition = carToPosition[desiredCar];
                    doOneMove(desiredCar, desiredCarPosition, i);
                }
            }
        }
	}
	
	private void doOneMove(int carId, int fromSpace, int toSpace) {
		moves.add(new Move(carId, fromSpace, toSpace));
		parkingLotCurrent[toSpace] = carId;
		parkingLotCurrent[fromSpace] = -1;
		currentEmptySpotIndex = fromSpace;
		carToPosition[carId] = toSpace;			
	}
	
	public void printMoves () {
		for(Move move : moves)
			System.out.println(move.toString());
    }

    public List<Move> getMoves() {
        return moves;
    }
    
    public int[] getEndState() {
        return parkingLotCurrent;
    }
}

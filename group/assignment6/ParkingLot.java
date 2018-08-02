package assignment6;

import java.util.ArrayList;
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
    private List<Integer>[] forbiddenParkingSpaces;
    private int parkingLotSize;
    private List<Move> moves;

    public ParkingLot(int[] parkingLotCurrent, int[] parkingLotEnd, List[] forbiddenParkingSlots) {

        this.parkingLotSize = parkingLotCurrent.length;
        this.parkingLotCurrent = parkingLotCurrent;
        this.parkingLotEnd = parkingLotEnd;
        this.forbiddenParkingSpaces = forbiddenParkingSlots;
        moves = new ArrayList<>();
    }

    public void rearrangeCars() throws Exception {

        checkValidInputAndInitFreeSpaces();

        initCarToPosition();
        doAllMoves();
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
            if (forbiddenParkingSpaces != null && carForbiddenToParkInSpace(parkedCarId, forbiddenParkingSpaces[i])) {
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
    
    private boolean carForbiddenToParkInSpace(int carId, List<Integer> carsForbiddenToParkInSpace) {
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

    private void doAllMoves() {

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
        moves.add(new Move(carId, fromSpace, toSpace));
        parkingLotCurrent[toSpace] = carId;
        parkingLotCurrent[fromSpace] = -1;
        currentEmptySpotIndex = fromSpace;
        carToPosition[carId] = toSpace;
    }

    public void printMoves() {
        for (Move move : moves) {
            System.out.println(move.toString());
        }
    }

    public List<Move> getMoves() {
        return moves;
    }

    public int[] getEndState() {
        return parkingLotCurrent;
    }
}

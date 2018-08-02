package assignment6;

public class Move {

	int carId;
	int fromSpace;
	int toSpace;
	
	Move(int carId, int fromSpace, int toSpace){
		this.carId = carId;
		this.fromSpace = fromSpace;
		this.toSpace = toSpace;
		
	}

	public String toString(){
		return String.format("Move car %d from space %d to space %d.", carId, fromSpace, toSpace);
	}
}
package assignment6;

import java.util.HashMap;

public class Move {

	int carId;
	int fromSpace;
	int toSpace;
	String state;
	
	Move(int carId, int fromSpace, int toSpace){
		this.carId = carId;
		this.fromSpace = fromSpace;
		this.toSpace = toSpace;
		
	}
	
	Move(int carId, int fromSpace, int toSpace, HashMap<Integer, Integer> state) {
		this.carId = carId;
		this.fromSpace = fromSpace;
		this.toSpace = toSpace;
		this.state = state.get(0).toString();
		for(int i = 1; i < state.size(); i++){
			this.state = this.state + ", " + state.get(i);
		}
	}

	public String toString(){
		if(state != null)
			return String.format("Move car %d from space %d to space %d => %s", carId, fromSpace, toSpace, state);
		return String.format("Move car %d from space %d to space %d", carId, fromSpace, toSpace);
	}
}

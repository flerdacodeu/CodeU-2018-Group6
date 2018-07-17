package assignment5;

import java.util.HashSet;

public class Vertex {

    private Character letter;
    private int depth;
    private String state;

    private HashSet<Vertex> childrenVertices;

    Vertex(Character letter) {
        this.letter = letter;
        depth = 0;
        state = "not_visited";
        childrenVertices = new HashSet<>();
    }
    
    Character getLetter() {
        return letter;
    }

    void increaseDepth() {
        this.depth = this.depth + 1;
    }
    
    void decreaseDepth() {
        this.depth = this.depth - 1;
    }

    void addChildVertex(Vertex child) {
        childrenVertices.add(child);
    }

    int getDepth() {
        return depth;
    }

    String getState() {
        return state;
    }
    
    void setVisited() {
        state = "visited";
    }
    
    void setNotVisited() {
        state = "not_visited";
    }
    
    HashSet<Vertex> getChildrenVertices() {
        return childrenVertices;
    }
}

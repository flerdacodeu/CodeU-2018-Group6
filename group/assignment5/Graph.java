package assignment5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Graph {

    
    private HashMap<Character, Vertex> mapLetterToVertex = new HashMap<>();
    private ArrayList<Vertex> vertices;
    
    private LinkedList<LinkedList<Character>> allPossibleOrders;
    private LinkedList<Character> currentOrder;

    Graph() {
        vertices = new ArrayList<>();
        allPossibleOrders = new LinkedList<>();
        currentOrder = new LinkedList<>();
    }
    
    boolean containsVertex(Character letter) {
        return mapLetterToVertex.containsKey(letter);
    }

    Vertex addVertex(Character letter) {
        Vertex vertex = new Vertex(letter);
        mapLetterToVertex.put(letter, vertex);
        vertices.add(vertex);
        return vertex;
    }
    
    Vertex getVertex(Character letter) {
        return mapLetterToVertex.get(letter);
    }

    void addEdge(Vertex parent, Vertex child) {
        parent.addChildVertex(child);
        child.increaseDepth();
    }

    // Goes through all the possible topological orderings by backtracking through the graph and trying a different combination every time
    // 1. at first all the vertices are not_visited
    // 2. take a vertix that is not_visited and has depth 0 (no incoming edges) and process it (change state and decrease depth of children)
    // 3. add the vertix to the current order and call the recursive function
    // 4. after the recursive call of this vertex finishes, backtrack by de-processing the vertex (return state to not_visited and increase depth of children) and remove it from the list
    LinkedList<LinkedList<Character>> allTopologicalSortOrders() {
        // flag to know if all the vertices are visited in one possible alphabet
        boolean allFound = false;
        
        for (Vertex vertex: vertices) {
            if (vertex.getState().equals("not_visited") && vertex.getDepth() == 0) {
                vertex.setVisited();
                // "delete" the branch to every child by decreasing the depth
                Iterator<Vertex> iter = vertex.getChildrenVertices().iterator();
                while(iter.hasNext()) {
                    Vertex child = iter.next();
                    child.decreaseDepth();
                }
                
                currentOrder.add(vertex.getLetter());
                allTopologicalSortOrders();
                
                // reset the vertix info allowing it to be chosen again in another combination
                vertex.setNotVisited();
                iter = vertex.getChildrenVertices().iterator();
                while(iter.hasNext()) {
                    Vertex child = iter.next();
                    child.increaseDepth();
                }
                currentOrder.remove(vertex.getLetter());
                
                allFound = true;
            }
        }
        
        if (!allFound) {
            // make different lists for each possible alphabet
            LinkedList<Character> newOrder = new LinkedList<>();
            for (Character letter : currentOrder) {
                newOrder.add(letter);
            }
            allPossibleOrders.add(newOrder);
        }
        
        return allPossibleOrders;
    }
    
}

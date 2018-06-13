import java.util.ArrayList;
import java.util.HashSet;

// Create a vertex class to represent the entries in the grid.
// Each vertex has the associated character and the position in the grid (line and column).
// For each vertex, store the vertices that can be reached from it (children), going in the 8 directions.
class Vertex{
	
	char ch;
	int line;
	int column;
	ArrayList<Vertex> children = new ArrayList<Vertex>();
	
	Vertex(int line, int column, char ch){
		this.line = line;
		this.column = column;
		this.ch = ch;
	}
	
	public void addChild(Vertex v){
		children.add(v);
	}
	
	public int getLine(){
		return line;
	}
	
	public int getColumn(){
		return column;
	}
	
	public ArrayList<Vertex> getChildren(){
		return children;
	}
	
	public char getChar(){
		return ch;
	}	
}

// A graph represented by its vertices.
// Vertices are stored in a matrix such that
// element at (i,j) is the corresponding Vertex of the cell at (i,j) in the given grid.
// Grid is an n x m matrix of chars.
class Graph{
	Vertex[][] vertices;
	int lines;
	int columns;
	
	// lines and columns are the size of the initial grid used to construct the representation.
	Graph(int lines, int columns){
		this.lines = lines;
		this.columns = columns;
		vertices = new Vertex[lines][columns];
	}
	
	// Create the vertices and the relation between them.
	// Return a matrix of Vertex objects.
	public Vertex[][] createVertices(char[][] grid){
		
		// Create a vertex for each cell in the grid.
		for(int i = 0; i < lines; i++){
			for(int j = 0; j < columns; j++){
				Vertex v = new Vertex(i, j, grid[i][j]);
				vertices[i][j] = v;
			}
		}
		
		// For each vertex, add its children (in the 8 directions).
		for(int i = 0; i < lines; i++){
			for(int j = 0; j < columns; j++){
				addChildrenToVertex(i, j);	
			}
		}
		
		return vertices;
	}
	
	// Given position (i,j) of a vertex in vertices matrix, add its children (in the 8 directions).s
	public void addChildrenToVertex(int i, int j){
		
		if(i != 0){
			vertices[i][j].addChild(vertices[i-1][j]); // up
			if(j != 0)
				vertices[i][j].addChild(vertices[i-1][j-1]); // diagonal up left
			if(j != columns-1)
				vertices[i][j].addChild(vertices[i-1][j+1]); // diagonal up right
		}
		if( i != lines-1){
			vertices[i][j].addChild(vertices[i+1][j]); // down
			if(j != 0)
				vertices[i][j].addChild(vertices[i+1][j-1]); // diagonal down left
			if(j != columns-1)
				vertices[i][j].addChild(vertices[i+1][j+1]); // diagonal down right	
		}
		if(j != 0)
			vertices[i][j].addChild(vertices[i][j-1]); // left
		if(j != columns-1)
			vertices[i][j].addChild(vertices[i][j+1]); // right	
	}
}

public class Q1 {

	// Create the vertices from the grid and use DFS from each through its children to form words.
	// lines and columns are the size of the grid, grid is an n x m matrix of chars.
	// Return an HashSet (to avoid duplicates) of the found words.
	public static HashSet<String> findWords(char[][] grid, Dictionary dict, int lines, int columns) throws Exception{
		
		if( lines <= 0 || columns <= 0){
			throw new IllegalArgumentException("Invalid grid");
		}

		HashSet<String> foundWords = new HashSet<String>(); 
		Graph graph = new Graph(lines, columns);
		Vertex[][] vertices = graph.createVertices(grid);
		
		for(int i = 0; i < lines; i++)
			for(int j = 0; j < columns; j++){
				
				// Check the word can start with the corresponding letter in the grid.
				String cur_prefix = grid[i][j] + "";
				
				if (dict.isPrefix(cur_prefix)){
					if(dict.isWord(cur_prefix)) // assume we can have one-letter words in dict.
						foundWords.add(cur_prefix);
					// Every time we start from a vertex, keep a visited matrix to avoid going to the same cell twice.
					boolean visited[][] = new boolean[lines][columns];
					// DFS to form all the words from this current vertex.
					DFShelper(vertices[i][j], cur_prefix, visited, dict, foundWords);
				}					
			}
		
		return foundWords;
	}

	// Recursive function for DFS traversal.
	private static void DFShelper(Vertex v,String prefix,  boolean visited[][], Dictionary dict, HashSet<String> foundWords){
		
		// Mark the current vertex visited.
		visited[v.getLine()][v.getColumn()] = true;
		
		// Traverse the children of the current vertex.
		for(Vertex child: v.getChildren()){
			if(!visited[child.getLine()][child.getColumn()]){
				String new_prefix = prefix + child.getChar();
				
				// If we form an accepted prefix, keep adding characters with depth search.
				if(dict.isPrefix(new_prefix)){
					if(dict.isWord(new_prefix)) // If the prefix is also a word in dictionary, add it to final list.
						foundWords.add(new_prefix);
					DFShelper(child, new_prefix, visited, dict, foundWords);					
				}
			}			
		}
		visited[v.getLine()][v.getColumn()] = false;
	}
}

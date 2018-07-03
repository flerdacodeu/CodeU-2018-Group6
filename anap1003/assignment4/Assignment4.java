package assignment4;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Assignment4 {

    private boolean areAdjacent(int tile1, int tile2, int cols) {
        int row1 = tile1 / cols;
        int col1 = tile1 % cols;
        int row2 = tile2 / cols;
        int col2 = tile2 % cols;
        return row1 == row2 && Math.abs(col1 - col2) == 1 || col1 == col2 && Math.abs(row1 - row2) == 1;
    }

    private boolean belongsToIsland(int tile, Set<Integer> island, int cols) {
        for (int part : island) {
            if (areAdjacent(tile, part, cols)) {
                return true;
            }
        }
        return false;
    }
    
    // merge all islands in list and add the tile
    private Set<Integer> mergeIslandsWithTile(List<Set<Integer>> islands, int tile) {
        Set<Integer> mergedIsland = new HashSet<>();
        for (Set<Integer> island : islands) {
            mergedIsland.addAll(island);
        }
        mergedIsland.add(tile);
        return mergedIsland;
    }

    public int findNumOfIslandsInSea(int rows, int columns, boolean[][] sea) {
        List<Set<Integer>> islands = new LinkedList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (sea[i][j]) {
                    int tile = i * columns + j;
                    List<Set<Integer>> islandsTileBelongsTo = new LinkedList<>();
                    for (Set<Integer> island : islands) {
                        if (belongsToIsland(tile, island, columns)) {
                            islandsTileBelongsTo.add(island);
                        }
                    }
                    if (islandsTileBelongsTo.isEmpty()) {
                        Set<Integer> island = new HashSet<>();
                        island.add(tile);
                        islands.add(island);
                    } else {
                        for (Set<Integer> island : islandsTileBelongsTo) {
                            islands.remove(island);
                        }
                        islands.add(mergeIslandsWithTile(islandsTileBelongsTo, tile));
                    }
                }
            }
        }

        return islands.size();
    }

}

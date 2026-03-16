public class TileChecker {
    private final char[][] grid;

    public TileChecker(char[][] grid){
        this.grid = grid;
    }

    public boolean isInBounds(int rows, int columns){
        return ((rows >= 0) && (rows < grid.length) && (columns >=0) && (columns < grid[0].length));
    }

    public boolean isForbiddenTile(int rows, int columns){
        return (isInBounds(rows, columns) && (grid[rows][columns] == '#'));
    }

    public boolean isJumpBlockedTile(int rows, int columns){
        return (grid[rows][columns] == 'J');
    }

    public boolean isDiagonalJumpBlockedTile(int rows, int columns){
        return (grid[rows][columns] == 'X');
    }

    public boolean isEndGoalTile(int rows, int columns){
        return ((rows == grid.length - 1) && (columns == grid[0].length - 1));
    }
    public char getTile(int rows, int columns){
        return grid[rows][columns];
    }

}

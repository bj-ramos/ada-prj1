public class TileChecker {
    private final char[][] grid;
    
    /**
     * This class serves to make the specific verifications for each type of tile found on the grid
     * 
     */
    public TileChecker(char[][] grid){
        this.grid = grid;
    }

    /**
     * This method verifies if the coordinate provided is in bounds
     * 
     * @param row  the row of the specific position
     * @param column the column of the specific position
     * 
     * @return true if the position is inside the grid
     *         false if out of the grid
     */
    public boolean isInBounds(int row, int column){
        return ((row >= 0) && (row < grid.length) && (column >=0) && (column < grid[0].length));
    }

    /**
     * This method verifies if the position has a '#' tile
     * 
     * @param row  the row of the specific position
     * @param column the column of the specific position
     * 
     * @return true if the position is a '#' tile and in bounds
     *         false if not
     */
    public boolean isForbiddenTile(int row, int column){
        return (isInBounds(row, column) && (grid[row][column] == '#'));
    }

    /**
     * This method verifies if the position has a 'J' tile
     * 
     * @param row       the row of the specific position
     * @param column    the column of the specific position
     * 
     * @return true if the position is a 'J' tile
     *         false if not
     */
    public boolean isJumpBlockedTile(int rows, int columns){
        return (grid[rows][columns] == 'J');
    }

    /**
     * This method verifies if the position has a 'X' tile
     * 
     * @param row       the row of the specific position
     * @param column    the column of the specific position
     * 
     * @return true if the position is a 'X' tile
     *         false if not
     */
    public boolean isDiagonalJumpBlockedTile(int row, int column){
        return (grid[row][column] == 'X');
    }

    /**
     * This method verifies if the position is the right bottom corner which is the goal
     * 
     * @param row       the row of the specific position
     * @param column    the column of the specific position
     * 
     * @return true if the position is the right bottom corner
     *         false if not
     */
    public boolean isEndGoalTile(int row, int column){
        return ((row == grid.length - 1) && (column == grid[0].length - 1));
    }

    /**
     * This method gets the tile in the specific position
     * 
     * @param row       the row of the specific position
     * @param column    the column of the specific position
     * 
     * @return the character of tile of the specific position
     * 
     */
    public char getTile(int row, int column){
        return grid[row][column];
    }

}

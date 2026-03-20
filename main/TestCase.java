public class TestCase {
   public int rows;
   public int columns;
   public int maxConsecutiveJumps;
   public int maxTotalJumps;
   public char[][] mapGrid;
/**
 * 
 * This class serves to save the parameters for each indiviual testCase
 * 
 * Preconditions: 
 *      1 <= rows <= 400
 *      1 <= columns <= 400
 *      1 <= maxConsecutiveJumps <= 5
 *      1 <= maxTotalJumps <= 10
 * 
 * @param rows                  The number of rows
 * @param columns               The number of columns
 * @param maxConsecutiveJumps   The maximum number of consecutive jumps allowed
 * @param maxTotalJumps         The maximum total number of jumps allowed
 * @param mapGrid               The 2D grid representing the map for the test case
 *  
 */
   public TestCase(int rows, int columns, int maxConsecutiveJumps, int maxTotalJumps, char[][] mapGrid){
        this.rows = rows;
        this.columns = columns;
        this.maxConsecutiveJumps = maxConsecutiveJumps;
        this.maxTotalJumps = maxTotalJumps;
        this.mapGrid = mapGrid;
    }

    /**
     *  This class calls for the class focused on the realization of the path calculations
     * 
     *  @return the number of possible paths in the given testCase  
     */
    public long solve(){
        PathsRec solver = new PathsRec(this);
        return solver.countPaths(0,0,0,0);
    }

}

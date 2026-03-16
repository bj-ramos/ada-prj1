public class PathsRec {
    private static final long modValue = 1000000007;
    private final TestCase tCase;
    private final TileChecker tChecker;
    private final long[][][][] memoizedPaths;


    public PathsRec(TestCase tCase) {
        this.tCase = tCase;
        memoizedPaths = new long[tCase.rows][tCase.columns][tCase.maxConsecutiveJumps+1][tCase.maxTotalJumps+1];
        this.tChecker = new TileChecker(tCase.mapGrid);
        resetMemoizedPaths();
    }

    private void resetMemoizedPaths(){
        for(int a = 0; a < tCase.rows; a++){
            for(int b = 0; b < tCase.columns; b++){
                for(int c = 0; c <= tCase.maxConsecutiveJumps; c++){
                    for(int d = 0; d <= tCase.maxTotalJumps; d++){
                        memoizedPaths[a][b][c][d] = -1;
                    }
                }
            }
        }
    }

    public long countPaths(int rows, int columns, int consecJumps, int totalJumps){
        if (!tChecker.isInBounds(rows, columns)){
            return 0;
        }

        if (tChecker.isForbiddenTile(rows, columns)){
            return 0;
        }

        // If jump counters are exceeded
        if ((consecJumps > tCase.maxConsecutiveJumps) || (totalJumps > tCase.maxTotalJumps)){
            return 0;
        }

        // If already in memory, return it instead of calling more recursion
        if (memoizedPaths[rows][columns][consecJumps][totalJumps] != -1){
            return memoizedPaths[rows][columns][consecJumps][totalJumps];
        }

        // Base case
        if(tChecker.isEndGoalTile(rows, columns)){
            return 1;
        }

        long pathsFound = 0;

        // For non-jump regular moves
        // step right
        pathsFound = (pathsFound + countPaths(rows, columns + 1, 0, totalJumps)) % modValue;
        // step downwards
        pathsFound = (pathsFound + countPaths(rows + 1, columns, 0, totalJumps)) % modValue;

        // For jump moves
        // If the jump counters have NOT been exceeded yet AND all jumping is allowed in this block
        if((consecJumps < tCase.maxConsecutiveJumps) && (totalJumps < tCase.maxTotalJumps) && !tChecker.isJumpBlockedTile(rows,columns)){

            // jump downwards
            pathsFound = (pathsFound + countPaths(rows + 2, columns, consecJumps + 1, totalJumps + 1)) % modValue;

            // If diagonal jumps are allowed
            if(!tChecker.isDiagonalJumpBlockedTile(rows,columns)){

                // jump diagonally down right
                pathsFound = (pathsFound + countPaths(rows + 1, columns + 1, consecJumps + 1, totalJumps + 1)) % modValue;

                // jump diagonally down left
                pathsFound = (pathsFound + countPaths(rows + 1, columns - 1, consecJumps + 1, totalJumps + 1) % modValue);
            }

        }

        memoizedPaths[rows][columns][consecJumps][totalJumps] = pathsFound;
        return pathsFound;
    }
}

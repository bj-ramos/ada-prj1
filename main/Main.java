import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    static int rows ;
    static int columns;
    static int maxConsecutiveJumps;
    static int maxTotalJumps;
    static final long modValue = 1000000007;

    static long[][][][] memoizedPaths;
    static int[][] gridMapKey;

    static HashMap<Integer, Character> mapLayout = new HashMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // Reads the problem basically

        int numTestCases = Integer.parseInt(bufferedReader.readLine());    

        for(int o = 0; o < numTestCases; o++){
        String testCaseInfo = bufferedReader.readLine();
        String[] parts = testCaseInfo.split(" ");

        rows = Integer.parseInt(parts[0]);
        columns = Integer.parseInt(parts[1]);

        maxConsecutiveJumps = Integer.parseInt(parts[2]);
        maxTotalJumps = Integer.parseInt(parts[3]);

        gridMapKey = new int[rows][columns];
        memoizedPaths = new long[rows + 1][columns + 1][maxConsecutiveJumps + 1][maxTotalJumps + 1];

        int keyCounter = -1;

        for (int i = 0; i < rows; i++) {
            String line = bufferedReader.readLine();
            for (int j = 0; j < columns; j++) {
                gridMapKey[i][j] = keyCounter + 1;
                mapLayout.put(gridMapKey[i][j],line.charAt(j));
                keyCounter++;
            }
        }

        // need to fill memoized path with -1s
        for(int b = 0; b < rows + 1; b++ ){
            for(int r = 0; r<columns + 1; r++){
                for(int u = 0; u<maxConsecutiveJumps + 1; u++){
                    for(int n = 0; n < maxTotalJumps + 1; n++){
                        memoizedPaths[b][r][u][n] = -1;
                    }
                }
            }
        }

       // System.out.println("Map -> " + mapLayout);

        long answer = countPaths(0,0,0,0);
    System.out.println("Paths : " + answer % modValue);
    }
        }

    public static long countPaths(int rows, int columns, int consecutiveJumps, int totalJumps) {

        if( (rows < 0) || (rows >= Main.rows) || (columns < 0) || (columns >= Main.columns)){
            return 0;
        }
  
        if ((Main.mapLayout.get(gridMapKey[rows][columns]) == '#') || (consecutiveJumps > Main.maxConsecutiveJumps) || (totalJumps > Main.maxTotalJumps)) {
          return 0;
        }
    
        if (Main.rows - 1 == rows && Main.columns - 1 == columns) {
            return 1;
        }

        if(memoizedPaths[rows][columns][consecutiveJumps][totalJumps] != -1){
            return memoizedPaths[rows][columns][consecutiveJumps][totalJumps];
        }

        // anda para a direita
        long right = countPaths(rows, columns + 1, 0, totalJumps);

        // anda para baixo
        long down = countPaths(rows + 1, columns, 0, totalJumps);

        long rightDown = 0;
        long downDown = 0;
        long leftDown = 0;
        
        if(((consecutiveJumps < Main.maxConsecutiveJumps) && (totalJumps < Main.maxTotalJumps)) && (Main.mapLayout.get(gridMapKey[rows][columns]) != 'J')) {

        // salta para baixo e baixo
        downDown += countPaths(rows + 2, columns, consecutiveJumps + 1, totalJumps + 1);
        
            if((Main.mapLayout.get(gridMapKey[rows][columns]) != 'X')){

            // salta para direita e baixo
            rightDown += countPaths(rows + 1, columns + 1, consecutiveJumps + 1, totalJumps + 1);

            // salta para esquerda e baixo
            leftDown += countPaths(rows + 1, columns - 1, consecutiveJumps + 1, totalJumps + 1);

            }
        }

        memoizedPaths[rows][columns][consecutiveJumps][totalJumps] = right + down + rightDown + downDown + leftDown;
        

        return right + down + rightDown + downDown + leftDown;

    }
}

        // Ava steps one tile at a time, either to the RIGHT or DOWN
        // Ava can jump a limited number of times diagonally LEFTDOWN, DOWN or RIGHTDOWN
        // Keep in mind the journey starts at the top-left and ends at the bottom-right (a matrix) in regular reading order
        // First input line gives us T = numTestCases, representing the number of tests
        // For each test case there will be another line containing 4 ints, R,C,M,N corresponding to ROWS COLUMNS maxConsecutiveJumps maxJumps
        // Following this line there will be ROWS number of lines each with COLUMNS characters (a matrix) each character conditionMatrix[R][C] = character, indicating the properties of the tile
        // "." means free movement without restrictions
        // "X" means diagonal jumps are forbidden (left to down; right to down)
        // "J" means ALL jumps are forbidden (diagonal or just down)
        // "#" means this tile cannot be stepped on (must avoid/not valid path)

        /**
         * After brainstorming on my whiteboard for a whole day, searching the internet for DP examples, watching videos and asking an LLM to think alongside me I managed to squeeze the following concept
         * We should approach this as a state-based problem represented by a 4-dimensional array with recursion and memoization. It can also be represented by a DAG directed acyclical graph (which is what I used to draw examples and manually compute)
         * states are represented by paths[r][c][s][k] where R are rows, c are columns, s is the number of maxConsecutiveJumps (a counter) and k is the number of jumps in the current test case (another counter)
         *
         * it goes something like
         *
         * pathsRec(R, C , S , K)
         *
         *
         *
         * countPaths(r,c,s,k){
         *  if (this state is invalid) -> return 0 // need to write verifications for IS this tile out of bounds; is it nullTile #; is k > N; is s > M
         *
         *  if (this is the endGoal) -> return 1 // need to write a verification for endGoal (if r == R-1 and c == C-1 means its the end of the grid)
         *
         *  if (this countPaths(r,c,s,k) already exists in memory, saved in our structure) -> return it from the structure
         *
         *  int paths = 0
         *
         *  considering regular moves (no jumps)
         *  paths += countPaths(r, c+1, s, 0) move right
         *  paths += countPaths(r+1, c, s, 0) move down
         *
         *  considering jumps
         *  if s < M and k < N:
         *  paths += countPaths(r+2, c, s+1, k+1) down-down jump
         *
         *  if thisTile != J and thisTile != X:
         *  paths += countPaths(r+1, c+1, s+1, k+1) down right jump
         *  paths += countPaths(r+1, c-1, s+1, k+1) down left jump
         *
         *  store paths in memory structure
         *  return paths
         *
         * }
         */

        /**
         * Arbitrary Constraints:
         * 1 <= numTestCases <= 20
         * 1 <= rows <= 400
         * 1 <= columns <= 400
         * 1 <= maxConsecutiveJumps <= 5
         * 1 <= maxJumps <= 10
         * ----
         * output numberPaths should be modulo 10^9 + 7 ( mod)
         * final output answer is an i number of lines printing the ith Paths each in respect to the ith test case
         */
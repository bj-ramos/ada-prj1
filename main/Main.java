import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {

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
         * countPaths(r,c,s,k){
         *  if (this state is invalid) -> return 0 // need to write verifications for IS this tile out of bounds; is it nullTile #; is k > N; is s > M
         *
         *  if (this is the endGoal) -> return 1 // need to write a verification for endGoal (if r == R-1 and c== C-1 means its the end of the grid)
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

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        // for now lets assume a SINGULAR test case, then once it works we refactor for multiple test case compatibility (should be straightforward)


        // Reads the problem basically
        String testCaseInfo = bufferedReader.readLine();
        String[] parts = testCaseInfo.split(" ");

        int r = Integer.parseInt(parts[0]);
        int c = Integer.parseInt(parts[1]);
        int m = Integer.parseInt(parts[2]);
        int n = Integer.parseInt(parts[3]);


        HashMap<Integer, Character> mapLayout = new HashMap<>();

        for (int i = 0; i < r; i++) {
            String line = bufferedReader.readLine();
            for (int j = 0; j < c; j++) {
                int key = i * c + j;
                mapLayout.put(key,line.charAt(j));
            }
        }

        System.out.println(mapLayout);

        String[][] board = buildBoard(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), bufferedReader);
        
        System.out.println(turnModulo(solver(board, Integer.parseInt(parts[2]), Integer.parseInt(parts[3])))); //ya eu sei q isto tá trágico

        }

    }
    /**
     * Method used to create the board by receiving the rows and columns 
     * 
     */
    private static String[][] buildBoard(int rows, int columns, BufferedReader br) throws IOException{
        String[][] board = new String[rows][columns];
        for(int i = 0; i < rows; i++){
            String row = br.readLine();
            String tiles[] = row.split("");
            for(int j = 0; j < columns; j++){
                board[i][j] = tiles[j];
            }
        }

        return board;
    }
    /**
    // possible movements: R- right; 
    //                     D - down; 
    //                     RD - jump right+down; 
    //                     DD - jump down+down; 
    //                     LD = jump left+down
    // Main solving method to return the total number of possible results
    // TODO:implement DP method
    */
        private static long solver( String[][] board, int consJumps, int maxJumps){
        
        return 0;
        }

        //Method to turn the solution calculated with solver into modulo
        private static String turnModulo(long solution){
            return "ya";
        }

}

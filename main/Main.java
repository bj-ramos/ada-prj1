import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
         * Arbitrary Constraints:
         * 1 <= numTestCases <= 20
         * 1 <= rows <= 400
         * 1 <= columns <= 400
         * 1 <= maxConsecutiveJumps <= 5
         * 1 <= maxJumps <= 10
         * ----
         * output numberPaths should be modulo 10^9 + 7 ( mod)
         * final output answer is an i number of lines printing the ith numberPaths each in respect to the ith test case
         */

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int numTestCases = Integer.parseInt(bufferedReader.readLine());

        for(int i=0; i< numTestCases; i++){
        String parameters = bufferedReader.readLine();
        String parts[] = parameters.split(" ");
        
        // parts[0] - nr of lines
        // parts[1] - nr of columns
        // parts[2] - nr of maximum consecutive jumps
        // parts[3] - nr of maximum total jumps

        // TODO: loop recursividade cena toda marada para construir 
        // o map com parts[0] e parts[1] com buffReader, 
        // ske tentar fazer double loop primeiro para ver se td funciona e dps fazer bem

        }

    }
}

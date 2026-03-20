import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {

        // input reading
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int numTestCases = Integer.parseInt(bufferedReader.readLine());

        if ((numTestCases >= 1) && (numTestCases <= 20)) {

            for (int n = 0; n < numTestCases; n++) {
                String testCaseInfo = bufferedReader.readLine();
                String[] parts = testCaseInfo.split(" ");

                int rows = Integer.parseInt(parts[0]);
                int columns = Integer.parseInt(parts[1]);
                int maxConsecutiveJumps = Integer.parseInt(parts[2]);
                int maxTotalJumps = Integer.parseInt(parts[3]);

                if (validInputs(rows, columns, maxConsecutiveJumps, maxTotalJumps)) {

                    char[][] mapGrid = new char[rows][columns];

                    for (int i = 0; i < rows; i++) {
                        String line = bufferedReader.readLine();
                        for (int j = 0; j < columns; j++) {
                            mapGrid[i][j] = line.charAt(j);
                        }
                    }

                    TestCase tCase = new TestCase(rows, columns, maxConsecutiveJumps, maxTotalJumps, mapGrid);
                    System.out.println(tCase.solve());
                }
            }
        }
    }

    /**
     * This method verifies if the precoditions are met
     * 
     * @param rows    The number of rows
     * @param columns The number of columns
     * @param maxCJ   The maximum number of consecutive jumps allowed
     * @param maxTJ   The maximum total number of jumps allowed
     * 
     * @return true if the preconditions are met
     *         false if not
     */
    private static boolean validInputs(int rows, int columns, int maxCJ, int maxTJ) {
        return ((rows >= 1) && (rows <= 400) && (columns >= 1) && (columns <= 400) && (maxCJ >= 1) && (maxCJ <= 5)
                && (maxTJ >= 1) && (maxTJ <= 10));
    }

}

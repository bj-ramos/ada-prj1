public class TestCase {
   public int rows;
   public int columns;
   public int maxConsecutiveJumps;
   public int maxTotalJumps;
   public char[][] mapGrid;

   public TestCase(int rows, int columns, int maxConsecutiveJumps, int maxTotalJumps, char[][] mapGrid){
        this.rows = rows;
        this.columns = columns;
        this.maxConsecutiveJumps = maxConsecutiveJumps;
        this.maxTotalJumps = maxTotalJumps;
        this.mapGrid = mapGrid;
    }

    public long solve(){
        PathsRec solver = new PathsRec(this);
        return solver.countPaths(0,0,0,0);
    }

}

import java.util.ArrayList;

class Solution {
    private int[][] gridInt;
    private boolean[][] visited;
    private int R;
    private int C;
    private static final int[][] dirs={{-1,0}, {0,1}, {1,0}, {0,-1}};

    public int numIslands(char[][] grid) {
        if(grid.equals(null)){
            return 0;
        }

        this.R = grid.length;
        if(R == 0){
            return 0;
        }

        this.C = grid[0].length;
        if(C == 0){
            return 0;
        }

        gridInt = new int[R][C];
        for(int x=0; x < R; x++){
            for(int y=0; y < C; y++){
                gridInt[x][y] = grid[x][y] - '0';
            }
        }

        visited = new boolean[R][C];
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=0; i< R; i++){
            for(int j=0; j< C; j++){
                if(gridInt[i][j]==1 && !visited[i][j]){
                    int count = dfs(i,j);
                    System.out.println(count);
                    res.add(count);
                }
            }
        }
        return res.size();
    }

    private int dfs(int x, int y){

        int res = 1;
        visited[x][y]=true;

        for(int d=0; d< dirs.length; d++){
            int nextX = x + dirs[d][0];
            int nextY = y + dirs[d][1];
            if(inArea(nextX,nextY) && gridInt[nextX][nextY] == 1 && !visited[nextX][nextY]){
                res += dfs(nextX, nextY);
            }
        }

        return res;
    }

    private boolean inArea(int x, int y){
        return x>=0 && x<R && y>=0 && y<C;
    }

    public static void main(String[] args){
        char[][] grid = {{'1','1','1','1','0'},{'1','1','0','1','0'},{'1','1','0','0','0'},{'0','0','0','0','0'}};
        Solution solution = new Solution();
        int count = solution.numIslands(grid);
        System.out.println(count);
    }
}
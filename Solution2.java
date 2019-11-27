import java.util.LinkedList;
import java.util.Queue;

class Solution2 {

    private boolean[][] visited;
    private int[][] gridInt;
    private int R;
    private int C;
    private static final int[][] dirs = {{-1,0}, {0,1}, {1,0}, {0,-1}};

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
        for(int i=0; i< R; i++){
            for (int j=0; j< C; j++){
                gridInt[i][j] = grid[i][j] - '0';
            }
        }

        visited = new boolean[R][C];
        int count =0;
        for(int i =0; i< R; i++){
            for(int j=0; j< C; j++){
                if(gridInt[i][j] == 1 && !visited[i][j]){
                    bfs(i,j);
                    count ++;
                }
            }
        }
        return count;
    }

    private void bfs(int x, int y){
        Queue<Integer> queue = new LinkedList<Integer>();
        int initialState = x * C + y;
        queue.add(initialState);
        visited[x][y] = true;

        while(!queue.isEmpty()){
            int cur = queue.remove();
            int curX = cur / C;
            int curY = cur % C;

            for(int d=0; d< dirs.length; d++){
                int nextX = curX + dirs[d][0];
                int nextY = curY + dirs[d][1];
                if(inArea(nextX,nextY) && !visited[nextX][nextY] && gridInt[nextX][nextY] == 1){
                    int next = nextX * C + nextY;
                    queue.add(next);
                    visited[nextX][nextY] = true;
                }
            }
        }
    }

    private boolean inArea(int x, int y){
        return x>=0 && x<R && y>=0 && y<C;
    }
}
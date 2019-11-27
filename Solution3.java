class Solution3 {

    class UF{
        private int[] parent;
        private int count;

        public UF(int n){
            this.count = n;
            parent = new int[n];
            for(int i=0; i< parent.length; i++){
                parent[i] = i;
            }
        }

        public int find(int p){
            int cur = p;
            while(parent[cur] != cur){
                cur = parent[cur];
            }
            return parent[cur];
        }

        public boolean isConnected(int p, int q){
            return find(p) == find(q);
        }

        public void unionElements(int p, int q){
            int pRoot = find(p);
            int qRoot = find(q);

            if(pRoot == qRoot){
                return;
            }

            parent[pRoot] = qRoot;
            count --;
        }

        public int getCount(){
            return count-1;
        }
    }


    private int[][] gridInt;
    private int R;
    private int C;
    private static final int[][] dir = {{0,1}, {1,0}};

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

        UF uf = new UF( R * C + 1);
        int dummy_tail = R * C;
        for(int v=0; v< R*C; v++){
            int x = v / C;
            int y = v % C;

            if(gridInt[x][y] == 0){
                uf.unionElements(v, dummy_tail);
            }

            if(gridInt[x][y] == 1){
                for(int d = 0; d< dir.length; d++){
                    int nextX = x + dir[d][0];
                    int nextY = y + dir[d][1];
                    if(inArea(nextX, nextY) && gridInt[nextX][nextY]==1){
                        int next = nextX * C + nextY;
                        uf.unionElements(v, next);
                    }
                }
            }
        }
        return uf.getCount();
    }

    private boolean inArea(int x, int y){
        return x>=0 && x<R && y>=0 && y<C;
    }

}
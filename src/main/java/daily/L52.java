package daily;

import java.util.Arrays;

public class L52 {

    private int N;
    private boolean[] cols;
    private int ans;
    public int totalNQueens(int n) {
        // every row will at least need one
        this.N = n;
        for (int i = 0; i < n; i++) {
            cols = new boolean[N];
            dfs(0, i, new boolean[n][n]);
        }
        return ans;
    }

    private void dfs(int r, int c, boolean[][] visited) {
        if (cols[c] || hasDiagonal(r, c, visited)) return;
        if (r ==  N - 1) {
//            visited[r][c] = true;
//            for (boolean[] row : visited) {
//                System.out.println(Arrays.toString(row));
//            }
            ans++;
//            System.out.println("\n");
//            visited[r][c] = false;
            return;
        }

        visited[r][c] = true;
        cols[c] = true;
        for (int i = 0; i < N; i++) {
            dfs(r + 1, i, visited);
        }
        visited[r][c] = false;
        cols[c] = false;
    }

    private boolean hasDiagonal(int r, int c, boolean[][] visited) {
        int tr = r;
        int tc = c;
        while(tr >= 0 && tc >= 0) {
            if (visited[tr--][tc--]) return true;
        }

        tr = r; tc = c;
        while(tr >= 0 && tc < N) {
            if (visited[tr--][tc++]) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        var ans = new L52();
//        System.out.println(ans.totalNQueens(4));
        System.out.println(ans.totalNQueens(9));
    }
}

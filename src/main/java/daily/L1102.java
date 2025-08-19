package daily;

import java.util.Arrays;
import java.util.PriorityQueue;

public class L1102 {

    private static final int[] dirs = new int[]{-1, 0, 1, 0, -1};
    public int maximumMinimumPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // use priority queue, rank with maximum minimum value so far
        var pq = new PriorityQueue<int[]>((a, b) -> b[2] - a[2]);
        int[][] scores= new int[m][n];
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(scores[i], -1);
        }

        pq.offer(new int[]{0, 0, 5});
        while(!pq.isEmpty()) {
            var cur = pq.poll();
            if (cur[0] == m - 1 && n - 1 == cur[1]) {
                return cur[2];
            }
            visited[cur[0]][cur[1]] = true;
            for (int d = 0; d < dirs.length - 1; d++) {
                int nx = cur[0] + dirs[d];
                int ny = cur[1] + dirs[d + 1];
                if (nx < 0 || ny < 0 || nx >= grid.length || ny >= grid[0].length || visited[nx][ny]) {
                    continue;
                }
                int nval= Math.min(cur[2], grid[nx][ny]);
                if (nval > scores[nx][ny]) {
                    scores[nx][ny] = nval;
                    pq.offer(new int[]{nx, ny, nval});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        var ans = new L1102();
        System.out.println(ans.maximumMinimumPath(new int[][]{
                new int[]{5, 4, 5}, new int[]{1, 2, 6}, new int[]{7, 4, 6}
        }));
    }
}

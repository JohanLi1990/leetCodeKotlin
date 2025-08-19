package daily;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class L1368 {
    private static final int[][] dirs = new int[][]{
            new int[]{0, 1}, new int[]{0, -1}, new int[]{1, 0}, new int[]{-1, 0}
    };
    
    public int minCost(int[][] grid) {
        // another Djkstra
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        int[][] cost = new int[m][n];
        for(int[] row : cost) {
            Arrays.fill(row, total);
        }
        var pq = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[]{0, 0, 0});
        boolean[][] visited = new boolean[m][n];

        while(!pq.isEmpty()) {
            var cur = pq.poll();
            if (cur[0] == m - 1 && cur[1] == n - 1) {
                return cur[2];
            }
            visited[cur[0]][cur[1]] = true;
            int curCost = cur[2];
            for (int d = 0; d < dirs.length; d++) {
                int nCost = curCost;
                int nx = cur[0] + dirs[d][0];
                int ny = cur[1] + dirs[d][1];
                if (d + 1 != grid[cur[0]][cur[1]]) {
                    nCost += 1;
                }
                if (nx >= 0 && ny >= 0 && nx < m && ny < n && !visited[nx][ny] && cost[nx][ny] > nCost){
                    cost[nx][ny] = nCost;
                    pq.offer(new int[]{nx, ny, nCost});
                }
            }
        }
        return m * n - 1;
    }

    public static void main(String[] args) {
        var ans = new L1368();
        System.out.println(ans.minCost(new int[][]{
                new int[]{1, 1, 1, 1},
                new int[]{2,2,2,2},
                new int[]{1,1,1,1},
                new int[]{2,2,2,2}
        }));
    }

}

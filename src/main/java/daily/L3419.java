package daily;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class L3419 {

    public int minMaxWeight(int n, int[][] edges, int threshold) {
        var graph = new ArrayList<List<int[]>>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        // reverse the graph so that question becomes finding all other edges from 0.
        for (int[] edge: edges) {
            graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }
        int[] cnt = new int[n];
        int maxScore = 0;
        var pq = new PriorityQueue<int[]>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{0, 0});
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int wt = cur[1];
            if (cnt[node] >= 1) {
                continue;
            }
            cnt[node] += 1;
            maxScore = Math.max(maxScore, wt);
            for (int[] child : graph.get(node)) {
                int next = child[0];
                if (cnt[next] >= 1) continue;
                pq.offer(child);
            }
        }
        for (int i = 0; i < n; i++) {
            if (cnt[i] == 0) {
                return -1;
            }
        }

        return maxScore;

    }
}

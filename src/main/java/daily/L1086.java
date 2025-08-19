package daily;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class L1086 {
    public int[][] highFive(int[][] items) {
        var map = new TreeMap<Integer, PriorityQueue<Integer>>();
        for (int[] item : items) {
            map.putIfAbsent(item[0], new PriorityQueue<>());
            var pq = map.get(item[0]);
            pq.offer(item[1]);
            if (pq.size() > 5) {
                pq.poll();
            }
            map.put(item[0], pq);
        }
        var ans = new int[map.keySet().size()][];
        int i = 0;
        for (var entry : map.entrySet()) {
            ans[i++] = new int[]{entry.getKey(), getAverage(entry.getValue())};
        }
        return ans;
    }

    private int getAverage(PriorityQueue<Integer> q) {
        int ans = 0;
        while (!q.isEmpty()) {
            ans += q.poll();
        }
        return ans / 5;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new L1086().highFive(new int[][]{
                new int[]{1, 91}, new int[]{1, 92}, new int[]{2, 93}, new int[]{2, 97}, new int[]{1, 60}, new int[]{2, 77}, new int[]{1, 65}, new int[]{1, 87}, new int[]{1, 100}, new int[]{2, 100}, new int[]{2, 76}
        })));
    }

}

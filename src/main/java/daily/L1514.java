package daily;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class L1514 {

    public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        // breadthfirst with sorting
        var pq = new PriorityQueue<Node>((a, b) -> Double.compare(b.prob, a.prob));
        var graph = new ArrayList<List<Node>>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int[] cur = edges[i];
            graph.get(cur[0]).add(new Node(cur[1], succProb[i]));
            graph.get(cur[1]).add(new Node(cur[0], succProb[i]));
        }

        var visited = new boolean[n];
        pq.offer(new Node(start_node, 1.0));
        double[] scores = new double[n];
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.index == end_node) {
                return cur.prob;
            }
            visited[cur.index] = true;
            for (Node child : graph.get(cur.index)) {
                if (visited[child.index]) {
                    continue;
                }
                double nprob = cur.prob * child.prob;
                if (Double.compare(nprob, scores[child.index]) > 0) {
                    scores[child.index] = nprob;
                    pq.offer(new Node(child.index, nprob));
                }
            }
        }
        return 0.0;

    }

    private class Node {
        int index;
        double prob;

        Node(int id, double prob) {
            this.index = id;
            this.prob = prob;
        }
    }
}



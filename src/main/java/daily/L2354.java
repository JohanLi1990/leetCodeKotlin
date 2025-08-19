package daily;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class L2354 {

    public int[] timeTaken(int[] arrival, int[] state) {
        Queue<Integer> enterQ = new LinkedList<>();
        Queue<Integer> exitQ = new LinkedList<>();
        int preState = 1;
//        int t = arrival[0] + arrival.length - 1;
        int j = 0;
        int[] ans = new int[arrival.length];
        for (int i = 0 ; i <= (int)1e6; i++) {
            boolean isDoorUsed = false;
            while(j < arrival.length && arrival[j] == i) {
                if (state[j] == 0) {
                    enterQ.offer(j);
                } else {
                    exitQ.offer(j);
                }
                j++;
            }

            if (preState == 0) {
                int cur = -1;
                if (!enterQ.isEmpty()) {
                    cur = enterQ.poll();
                } else if (!exitQ.isEmpty()) {
                    cur = exitQ.poll();
                    preState = 1 - preState;
                }
                if (cur != -1) {
                    isDoorUsed = true;
                    ans[cur] = i; // current time;
                }
            } else {
                int cur = -1;
                if (!exitQ.isEmpty()) {
                    cur = exitQ.poll();
                } else if (!enterQ.isEmpty()) {
                    cur = enterQ.poll();
                    preState = 1 - preState;
                }
                if (cur != -1) {
                    isDoorUsed = true;
                    ans[cur] = i; // current time;
                }
            }
            if (!isDoorUsed) {
                preState = 1;
            }

            // no more arrivals, no more exits and entering
            if (j >= arrival.length && exitQ.isEmpty() && enterQ.isEmpty()) {
                break;
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new L2354().timeTaken(
                new int[]{0, 1, 1, 2, 4}, new int[]{0, 1, 0, 0, 1}
        )));

        System.out.println(Arrays.toString(
                new L2354().timeTaken(
                        new int[]{0, 0, 0}, new int[]{1, 0, 1}
                )
        ));

        System.out.println(Arrays.toString(
                new L2354().timeTaken(
                        new int[]{0,5,6,6,7,9,9,9,10,10,10,10,10,15,16,17,17,17},
                        new int[]{1,1,1,1,0,0,0,1,1,1,1,1,0,1,1,0,1,0}
                )
        ));
    }
}

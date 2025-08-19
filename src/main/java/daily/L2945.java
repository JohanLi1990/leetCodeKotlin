package daily;

import java.util.*;

public class L2945 {

    public int findMaximumLength(int[] nums) {
        // dp[i] : maximum length after cuting and combining subarries until index i
        // suppose we have the last group is from nums[j]...nums[i]
        // and we need to select the next group to combine, then we need to have
        // nums[j] +...+ nums[i] <= nums[i + 1] +...+ nums[k] , let k be the index to select next
        // if we have a prefix sum array this will be
        // preSum[i] - preSum[j] <= preSum[k] - preSum[i]
        // 2 * preSum[i] - preSum[j] <= preSum[k]
        // therefore given an i, and j, we can determine k from preSum using binary search
        int N = nums.length;
        int[] dp = new int[N + 1];

        // first construct the prefix sum
        long[] preSum = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        int[] preIndex = new int[N + 2];
        for (int i = 1, j = 0; i <= N; i++) {
            // j is the last group
            j = Math.max(j, preIndex[i]);
            dp[i] = dp[j] + 1;
            int k = Arrays.binarySearch(preSum, 2 * preSum[i] - preSum[j]);
            if (k < 0) {
                k = -k - 1;
            }
            preIndex[k] = i;
        }
        return dp[N];
    }

    public static void main(String[] args) {
        var ans = new L2945();
        System.out.println(ans.findMaximumLength(new int[]{5, 2, 2}));
        System.out.println(ans.findMaximumLength(new int[]{1, 2, 3, 4 }));
        System.out.println(ans.findMaximumLength(new int[]{4, 3, 2, 6}));
    }
}

package daily;

import java.util.Arrays;

public class L2616 {
    public int minimizeMax(int[] nums, int p) {
        // The question is asking, select the pth smallest diff
        // among all pair-diff.

        // for example 1 i just select the smalles two pairs
        // then the answer willbe pairs[1]
        // question becomes, how do I find the pth smallest diff.
        // [1, 1, 2, 3, 7, 10], O(NlogN)
        // 1, [1, 2, 3 , 7, 10], smallest pair diff will happen between neighbours first
        // O (logN)
        // NOTE: P distinct pairs!!, one index can only be used one times!!!
        // That is why the greedy way works. because that is the best pairing scenarios!!!

        Arrays.sort(nums);
        int lo = 0, hi = nums[nums.length - 1] - nums[0] + 1; // half open
        // find out the
        while( lo < hi) {
            int mid = lo + (hi - lo)/2;
            int count = countPairsSmaller(nums, mid);
            if (count < p) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    private int countPairsSmaller(int[] A, int v) {
        int idx = 0, count = 0;

        while(idx < A.length - 1) {
            if (A[idx + 1] - A[idx] <= v) {
                count++;
                idx++;
            }
            idx++;
        }
        return count;
    }

    public static void main(String[] args) {
        var ans = new L2616();
        System.out.println(ans.minimizeMax(new int[]{10,1,2,7,1,3}, 2));
    }
}

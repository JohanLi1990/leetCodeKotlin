package daily;

import java.util.Arrays;

public class L498 {
    private final static int MOD = (int)1e9 + 7;
    public int numSubseq(int[] nums, int target) {
        // key is how many ways to fill up a subsequence ? with n slots? 2 ^ n;
        int n = nums.length;
        int[] power = new int[n];
        power[0] = 1;
        for (int i = 1; i < n; ++i) {
            power[i] = (power[i - 1] * 2) % MOD;
        }
        Arrays.sort(nums);
        int ans = 0;
        for (int l = 0; l < nums.length; l++) {
            if (nums[l] * 2 > target) {
                break;
            }
            int r = bSearch(nums, l, target);
            int numSlots = r - l;
            if (numSlots >= 0) {
                ans += power[numSlots];
                ans %= MOD;
            }
        }
        return ans;
    }

    private int bSearch(int[] A, int l, int target) {
        int leftVal = A[l];
        int left = l;
        int right = A.length - 1;
        while(left < right) {
            int mid = left + (right - left) / 2;
            if (A[mid] + leftVal <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        if (A[left] + leftVal > target) {
            left--;
        }
        return left;
    }

    public static void main(String[] args) {
        System.out.println(
                new L498().numSubseq(new int[]{7,10,7,3,7,5,4}, 12)
        );

//        System.out.println(
//                new L498().numSubseq(new int[]{3,5,6,7}, 10)
//        );
    }
}

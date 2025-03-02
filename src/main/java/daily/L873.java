package daily;

public class L873 {


    public int lenLongestFibSubseq(int[] arr) {
        int[][] dp = new int[arr.length][arr.length];

        int ans = 0;
        for (int i = 2; i < arr.length; i++) {
            for (int j = i - 1; j > 0; j--) {
                int diff = arr[i] - arr[j];
                if (diff >= arr[j]) continue;
                int pre = bsearch(arr, 0, j - 1, diff);
                if (pre == -1) continue;
                if (dp[pre][j] == 0) {
                    dp[j][i] = 3;
                } else {
                    dp[j][i] = dp[pre][j] + 1;
                }
                ans = Math.max(ans, dp[j][i]);
            }
        }
        return ans;
    }

    private int bsearch(int[] arr, int s, int e, int target) {
        while( s <= e) {
            int mid = s + (e - s) / 2;
            if (arr[mid] < target) {
                s = mid + 1;
            } else if (arr[mid] > target ) {
                e = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        var ans = new L873();
        System.out.println(ans.lenLongestFibSubseq(new int[]{1,2,3,4,5,6,7,8}));
        System.out.println(ans.lenLongestFibSubseq(new int[]{1,3,7,11,12,14,18}));
    }

}

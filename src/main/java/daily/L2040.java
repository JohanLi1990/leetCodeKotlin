package daily;

public class L2040 {
    // hard questions
    public long kthSmallestProduct(int[] nums1, int[] nums2, long k) {
        // the product is going to be between left, and right
        long left = -(long)1e10, right = (long)1e10 + 1;
        // use binary search to find out how many values are smaller than k
        while (left < right) {
            long count = 0;
            long mid = left + (right - left) / 2L;
            for (int i = 0; i < nums1.length; i++) {
                count += bSearch(nums2, nums1[i], mid);
            }
            if (count < k) { // v too small
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;

    }

    private int bSearch(int[] A, int cur, long v) {
        int lo = 0, hi = A.length;
        while(lo < hi) {
            int mid = lo + (hi - lo) / 2;
            long prod = (long)cur * A[mid];
            if (cur >= 0) {
                if (prod <= v) {
                    lo = mid + 1; // converge to first index that is larger than v
                } else {
                    hi = mid;
                }
            } else {
                if (prod > v) {
                    lo = mid + 1; // find first index where that is smaller than v;
                } else {
                    hi = mid;
                }
            }
        }
        if (cur < 0) return A.length - lo;
        return lo;
    }


    public static void main(String[] args) {
        var ans = new L2040();
//        System.out.println(ans.kthSmallestProduct(new int[]{2,5}, new int[]{3, 4}, 2));
//        System.out.println(ans.kthSmallestProduct(new int[]{-4, -2, 0, 3}, new int[]{2, 4}, 6));
        System.out.println(ans.kthSmallestProduct(new int[]{-2, -1, 0, 1, 2}, new int[]{-3, -1, 2, 4, 5}, 3));
    }
}

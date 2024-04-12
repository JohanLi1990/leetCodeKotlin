package daily

class L88MergeSortedArray {

    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var i = 0;
        var j = 0;
        val res = IntArray(m + n)
        var k = 0
        while(i < m && j < n ) {
            if (nums1[i] <= nums2[j]) {
                res[k++] = nums1[i++]
            } else {
                res[k++] = nums2[j++]
            }
        }
        while(i < m) {
            res[k++] = nums1[i++]
        }

        while(j < n) {
            res[k++] = nums2[j++];
        }

        for (a in 0..<(m + n)) {
            nums1[a] = res[a]
        }
    }

}

fun main() {
    val nums1 = intArrayOf(1,2,4,5,6,0)
    val nums2 = intArrayOf(3)
    val ans = L88MergeSortedArray();
    ans.merge(nums1, 5, nums2, 1)
    println(nums1.contentToString())
}
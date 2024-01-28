import java.util.PriorityQueue

class L4 {

    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        // two sorted array

        val q = PriorityQueue<Int>()

        var k1 = 0
        var k2 = 0
        var arr = if (nums1.isEmpty()) nums2[0] else nums1[0]
        val m = nums1.size
        val n = nums2.size
        while (k1 < m && k2 < n) {

            if (k1 + k2 > (m + n) / 2) {
                break
            }

            if (nums1[k1] < nums2[k2]) {
                q.offer(nums1[k1++])
            } else {
                q.offer(nums2[k2++])
            }
            if (q.size > 2) q.poll()
        }

        while(k1 + k2 <= (m + n) /2 ) {
            if (k1 < m) {
                q.offer(nums1[k1++])
            } else if (k2 < n) {
                q.offer(nums2[k2++])
            }

            if (q.size > 2) q.poll()
        }

        if ((m + n) % 2 == 0) {
            val first = q.poll().toDouble()
            val sec = q.poll().toDouble()
            return (first + sec) / 2
        }

        if (q.size < 2) return q.poll().toDouble()

        q.poll()
        return q.poll().toDouble()

    }

}

fun main() {
    val input1 = intArrayOf(1)
    val input2 = intArrayOf()

    val ans = L4()
    println(ans.findMedianSortedArrays(input1, input2))
}
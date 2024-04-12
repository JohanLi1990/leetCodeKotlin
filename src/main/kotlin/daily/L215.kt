package daily

import java.util.PriorityQueue

class L215 {

    fun findKthLargest(nums: IntArray, k: Int): Int {
        var m = k
        val q = PriorityQueue<Int>(Comparator.reverseOrder())
        for (num in nums) {
            q.offer(num)
        }

        while(!q.isEmpty() && m > 1) {
            q.poll()
            m--
        }
        return q.peek()
    }
}

fun main() {
    val input1 = intArrayOf(3,2,3,1,2,4,5,5,6)
    val ans = L215()
    println(ans.findKthLargest(input1, 4))
}
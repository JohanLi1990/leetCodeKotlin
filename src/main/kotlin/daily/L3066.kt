package daily

import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min

fun minOperations(nums: IntArray, k: Int): Int {
    val pq = PriorityQueue<Long>()

    nums.forEach { pq.add(it.toLong()) }

    var ans = 0
    while(pq.peek() < k) {
        val first = pq.poll().toLong()
        val second = pq.poll().toLong()

        pq.offer(min(first, second) * 2 + max(first, second))
        ans++
    }
    return ans
}

fun main() {
//    println(minOperations(intArrayOf(2,11,10,1,3), 10))
//    println(minOperations(intArrayOf(1,1,2,4,9), 20))
    println(minOperations(intArrayOf(999999999,999999999,999999999),1000000000 ))
}
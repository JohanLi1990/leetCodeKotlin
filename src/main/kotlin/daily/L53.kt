package daily

import kotlin.math.max

class L53 {
}

fun maxSubArray(nums: IntArray): Int {
    // Kadane's algorithm
    var res = Integer.MIN_VALUE
    var cur = 0
    for (num in nums) {
        cur = max(cur + num, num)
        res = max(res, cur)
    }
    return res
}

fun main() {
    val input = intArrayOf(-2,1,-3,4,-1,2,1,-5,4)
    println(maxSubArray(input))
}
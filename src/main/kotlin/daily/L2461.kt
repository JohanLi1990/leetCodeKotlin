package daily

import kotlin.math.max

// 2461. Maximum Sum of Distinct Subarrays With Length K
class L2461 {
    fun maximumSubarraySum(nums: IntArray, k: Int): Long {
        var i = 0
        val curMap = mutableMapOf<Int, Int>()
        val sumMap = LongArray(nums.size) { 0 }
        var maxSum: Long = 0

        nums.forEachIndexed { j, num ->
            if (num in curMap) {
                i = max(i, curMap.getValue(num) + 1)
            }
            sumMap[j] = (sumMap.getOrNull(j - 1) ?: 0) + num
            curMap[num] = j

            if (j - i + 1 >= k) {
                val pres = sumMap.getOrNull(j - k) ?: 0
                maxSum = max(maxSum, sumMap[j] - pres)
            }
        }
        return maxSum
    }
}

fun main() {
    val asn = L2461()
    println(asn.maximumSubarraySum(intArrayOf(18,9,9,12,12,18),5))
}


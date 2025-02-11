package daily

import kotlin.math.max

fun waysToSplitArray(nums: IntArray): Int {
    val sumArr = LongArray(nums.size)
    for (i in 0..<nums.size) {
        sumArr[i] = sumArr[max(0, i - 1)] + nums[i]
    }
    var ans = 0
    for (i in 0..<nums.size - 1) {
        if (sumArr[i] >= sumArr[nums.size - 1] - sumArr[i]) {
            ans++
        }
    }
    return ans
}

fun main() {
    println(waysToSplitArray(intArrayOf(10, 4, -8, 7))) // 2
    println(waysToSplitArray(intArrayOf(2, 3, 1, 0))) // 2
}
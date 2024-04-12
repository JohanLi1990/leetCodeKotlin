package daily

import java.util.TreeMap
import kotlin.math.min

fun minSubArrayLen(target: Int, nums: IntArray): Int {
    // minimum subarray length that is greater or equal to target

    // sliding window problem, use TreeMap to solve it,
    // use TreeMap to find the largest value i, that makes curSum - i >= target
    // since num is postive, the length of window is bound to be minimum
    val map = TreeMap<Int, Int>()
    var curSum = 0
    map[0] = -1
    var res = Int.MAX_VALUE
    for (i in nums.indices) {
        curSum += nums[i]
        val search = curSum - target
        if (search >= 0) {
           val floor = map.floorEntry(search)
            res = min(res, i - floor.value)
        }
        map[curSum] = i
        if (res == 1) return res
    }

    return if (res == Int.MAX_VALUE) 0 else res
}
fun main() {
    val arr = intArrayOf(1, 4, 4)
    println(minSubArrayLen(10, arr))
}
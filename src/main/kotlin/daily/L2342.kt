package daily

import kotlin.math.max

fun maximumSum(nums: IntArray): Int {
    val map = mutableMapOf<Int, IntArray>()
    fun _getSum(num: Int) : Int {
        var ans = 0
        var cur = num
        while(cur > 0) {
            ans += cur % 10
            cur /= 10
        }
        return ans
    }
    var ans = -1
    for (num in nums) {
        val curSum = _getSum(num)
        map[curSum] = map.getOrDefault(curSum, IntArray(2)).also {
            if (it[0] == 0) {
                it[0] = num
            } else if ( it[1] == 0 || num > it[1]) {
                it[1] = num
            }
            if (it[1] > it[0]) {
                it[1] += it[0]
                it[0] = it[1] - it[0]
                it[1] = it[1] - it[0]
            }
            if (it[0] != 0 && it[1] != 0) {
                ans = max(ans, it[0] + it[1])
            }
        }
    }
    return ans
}

fun main() {
//    println(maximumSum(intArrayOf(18,43,36,13,7))) // 54
//    println(maximumSum(intArrayOf(10,12,19,14)))
    println(maximumSum(intArrayOf(243,205,369,253,72,484,300,161,188,69,309,96,226,308,252))) // 495
}
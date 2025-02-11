package daily

import kotlin.math.max

// https://leetcode.com/problems/best-sightseeing-pair/
// kadanes algorithm
fun maxScoreSightseeingPair(values: IntArray): Int {
    val dp = IntArray(values.size)
    dp[0] = values[0]
    var max = 0
    for (i in 1..<values.size) {
        val cur = dp[i - 1] + values[i]  - 1
        max = max(max, cur)
        dp[i] = max(dp[i - 1] - 1, values[i])
    }

//    println(dp.contentToString())
    return max
}

fun main() {
    val ans = maxScoreSightseeingPair(intArrayOf(7,8,8,10))
    println(ans)
}
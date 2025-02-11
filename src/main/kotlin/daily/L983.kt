package daily

import kotlin.math.max
import kotlin.math.min

fun mincostTickets(days: IntArray, costs: IntArray): Int {
    var j = 0
    val dp = IntArray(366)
    for (i in 1..365) {
        if (i != days[j]) {
            dp[i] = dp[i - 1]
            continue
        }
        dp[i] = dp[i - 1] + costs[0]
        dp[i] = min(dp[max(i - 7, 0)] + costs[1], dp[i])
        dp[i] = min(dp[max(i - 30, 0)] + costs[2], dp[i])
        j++;
        if (j == days.size) break
    }
    return dp[days[j - 1]]
}

fun main() {
    println(mincostTickets(intArrayOf(1, 4, 6, 7, 8, 20), intArrayOf(2, 7, 15))) // 11
    println(mincostTickets(intArrayOf(1, 4, 6, 7, 8, 20), intArrayOf(7, 2, 15))) // 6
    println(mincostTickets(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 30, 31), intArrayOf(2, 7, 15))) // 17
}
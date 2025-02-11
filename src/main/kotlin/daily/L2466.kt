package daily

import kotlin.math.min


fun countGoodStrings(low: Int, high: Int, zero: Int, one: Int): Int {
    val mod = 1_000_000_007L
    val dp = LongArray(high + 1)
    dp[0] = 1
    var ans = 0L
    for (i in min(zero, one)..high) {
        dp[i] += if (i >= zero) dp[i - zero] else 0
        dp[i] += if (i >= one) dp[i - one] else 0
        dp[i] %= mod
        if (i >= low) {
            ans += dp[i]
            ans %= mod
        }
    }
    return ans.toInt()
}

fun main() {
    println(countGoodStrings(3, 3, 1, 1)) // 8
    println(countGoodStrings(2, 3, 1, 2)) //5
}
package daily

import kotlin.math.max


fun longestIdealString(s: String, k: Int): Int {
    // we only record the last seen position of the  alphabet
    // we maintain dp array

    val dp = IntArray(s.length)
    val map = HashMap<Char, Int>()
    var res = 1
    for (i in s.indices) {

        val c = s[i]
        dp[i] = 1
        if (map[c] != null) {
            dp[i] = max(dp[i], dp[map[c]!!] + 1)
        }
        for (j in 1..k) {
            val p1 = c - j
            val p2  = c + j
            if (p1 >= 'a' && map[p1] != null) {
                dp[i] = max(dp[i], dp[map[p1]!!] + 1)
            }

            if (p2 <= 'z' && map[p2] != null) {
                dp[i] = max(dp[i], dp[map[p2]!!] + 1)
            }
        }
        map[c] = i
        res = max(res, dp[i])
    }
    return res
}

fun main() {
    println(longestIdealString("acfgbd", 2)) //4
    println(longestIdealString("abcd", 3)) //4
}
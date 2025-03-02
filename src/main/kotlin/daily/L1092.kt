package daily

import kotlin.math.max

fun shortestCommonSupersequence(str1: String, str2: String): String {
    // find longest common subsequence
    val dp = Array(str1.length + 1){IntArray(str2.length + 1)}

    for (i in 1..str1.length) {
        for (j in 1..str2.length) {
            if (str1[i - 1] == str2[j - 1]) {
                dp[i][j] = dp[i - 1][j - 1] + 1;
            }
            dp[i][j] = max(dp[i][j], max(dp[i - 1][j], dp[i][j - 1]))
        }
    }

//    for (row in dp) println(row.contentToString())
    var x = str1.length
    var y = str2.length
    return buildString {
        while(x > 0 && y > 0) {
            if (str1[x - 1] == str2[y - 1]) {
                append(str1[x - 1])
                x--
                y--
                continue
            }

            if (dp[x - 1][y] > dp[x][y - 1] ) {
                append(str1[x - 1])
                x--
            } else {
                append(str2[y - 1])
                y--
            }
        }

        while(x-- > 0) {
            append(str1[x])
        }
        while(y-- > 0) {
            append(str2[y])
        }
    }.reversed()


}

fun main() {
    println(shortestCommonSupersequence("abac", "cab"))
    println(shortestCommonSupersequence("aaaaaaaa", "aaaaaaaa"))
    println(shortestCommonSupersequence("aabbbbbba", "aaaabbaa"))
    println(shortestCommonSupersequence("bbbaaaba", "bbababbb"))
}
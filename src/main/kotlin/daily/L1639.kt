package daily


// dp[i][len] is number of ways to get target[i:i +len] choosing from index i
// dp[i][len] = dp[1][len - 1] + dp[2][len - 2]
private lateinit var dp: Array<LongArray>
private const val mod = 1_000_000_007L
fun numWays(words: Array<String>, target: String): Int {
    val map = Array(words[0].length) { IntArray(26) }
    dp = Array(words[0].length + 1) { LongArray(target.length) }
    for (word in words) {
        for ((j, c) in word.withIndex()) {
            map[j][c - 'a']++
        }
    }


    fun _dfs(map: Array<IntArray>, index: Int, curTargetIndex: Int, target: String, N: Int): Long {
        if (curTargetIndex == target.length) {
            return 1L
        }
        if (index == N || N - index < (target.length - curTargetIndex)) return 0

        if (dp[index][curTargetIndex] != 0L) return dp[index][curTargetIndex]

        // two choices
        var ans = _dfs(map, index + 1, curTargetIndex, target, N) % mod

        val curChar = target[curTargetIndex]
        if (map[index][curChar - 'a'] > 0) {
            ans += map[index][curChar - 'a'] * _dfs(map, index + 1, curTargetIndex + 1, target, N)
            ans %= mod
        }
        dp[index][curTargetIndex] = ans
        return ans
    }

    val ans = _dfs(map, 0, 0, target, words[0].length)
    return ans.toInt()
}

fun main() {
    println(numWays(arrayOf("acca", "bbbb", "caca"), "aba"))
    println(numWays(arrayOf("abba", "baab"), "bab"))
    println(
        numWays(
            arrayOf(
                "cbabddddbc",
                "addbaacbbd",
                "cccbacdccd",
                "cdcaccacac",
                "dddbacabbd",
                "bdbdadbccb",
                "ddadbacddd",
                "bbccdddadd",
                "dcabaccbbd",
                "ddddcddadc",
                "bdcaaaabdd",
                "adacdcdcdd",
                "cbaaadbdbb",
                "bccbabcbab",
                "accbdccadd",
                "dcccaaddbc",
                "cccccacabd",
                "acacdbcbbc",
                "dbbdbaccca",
                "bdbddbddda",
                "daabadbacb",
                "baccdbaada",
                "ccbabaabcb",
                "dcaabccbbb",
                "bcadddaacc",
                "acddbbdccb",
                "adbddbadab",
                "dbbcdcbcdd",
                "ddbabbadbb",
                "bccbcbbbab",
                "dabbbdbbcb",
                "dacdabadbb",
                "addcbbabab",
                "bcbbccadda",
                "abbcacadac",
                "ccdadcaada",
                "bcacdbccdb"
            ), "bcbbcccc"
        )
    )
}
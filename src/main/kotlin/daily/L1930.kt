package daily

fun countPalindromicSubsequence(s: String): Int {
    // backtracking
    val preIndex = IntArray(26){-1}
    val uniquePalin = Array(26){ IntArray(26)}
    val count = IntArray(26)
    var ans = 0
    for ((j, c) in s.withIndex()) {
        val cur = uniquePalin[c - 'a']
        if (preIndex[c - 'a'] != -1 ) {
            for (i in preIndex.indices) {
                if (cur[i] == 0 && preIndex[i] > preIndex[c - 'a']) {
                    cur[i] = 1
                    ans++
                }
            }
        }
        preIndex[c - 'a'] = j
        count[c - 'a']++
        if (count[c - 'a'] == 3) {
            ans++
            cur[c - 'a'] = 1
        }
    }
    // debug
    return ans
}

fun main() {
    println(countPalindromicSubsequence("aabca")) // 3
    println(countPalindromicSubsequence("adc")) // 0
    println(countPalindromicSubsequence("bbcbaba"))// 4
}
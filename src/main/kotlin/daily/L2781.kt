package daily

import kotlin.math.max

fun longestValidSubstring(word: String, forbidden: List<String>): Int {

   // pay attention to constraints: max length of forbidden word is 10
    val set = mutableSetOf<String>().apply {
        addAll(forbidden)
    }
    var j = 0
    var res = 0
    for (i in word.indices) {
        for (k in max(i -9, max(0, j))..i) {
            val curString = word.substring(k, i + 1)
            if (set.contains(curString)) {
                j = k + 1
            }
        }
        res = max(res, i - j + 1)
    }
    return res
}

fun main() {
//    println(longestValidSubstring("cbaaaabc", listOf("aaa","cb"))) // 4
//    println(longestValidSubstring("leetcode", listOf("de","le","e"))) // 4
    println(longestValidSubstring("aaaabaaacc", listOf("bcca","aaa","aabaa","baaac"))) // 4
}
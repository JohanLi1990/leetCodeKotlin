package daily

import kotlin.math.max

// https://leetcode.com/problems/count-vowel-strings-in-ranges

fun vowelStrings(words: Array<String>, queries: Array<IntArray>): IntArray {
    // words length is over 100_000, so segment tree is not ideal, because it will take 200_000 size array
    val cumArray = IntArray(words.size)
    for ((i, word) in words.withIndex()) {
        if (word[0].isVowel() && word[word.length - 1].isVowel()) {
            cumArray[i] = cumArray[max(i - 1, 0)] + 1
        } else {
            cumArray[i] = cumArray[max(i - 1, 0)]
        }
    }
    val ans = IntArray(queries.size)
    for ((i, query) in queries.withIndex()) {
        ans[i] = cumArray[query[1]] - if (query[0] == 0) 0 else cumArray[query[0] - 1]
    }
    return ans

}

private fun Char.isVowel(): Boolean {
    return this == 'a' || this == 'e' || this == 'i' || this == 'o' || this == 'u'
}

fun main() {
    println(vowelStrings(arrayOf("aba","bcb","ece","aa","e"), arrayOf(
        intArrayOf(0,2), intArrayOf(1, 4), intArrayOf(1, 1)
    )).contentToString()) // [2,3,0]

    println(vowelStrings(arrayOf("a","e","i"), arrayOf(
        intArrayOf(0,2), intArrayOf(0, 1), intArrayOf(2, 2)
    )).contentToString()) // [3,2,1]
}
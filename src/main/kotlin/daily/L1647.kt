package daily

import kotlin.collections.Set as Set

// https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
// https://leetcode.com/problems/maximum-number-of-k-divisible-components/
fun minDeletions(s: String): Int {

    val wordCount = IntArray(26)

    for ( c in s) {
        wordCount[c - 'a']++
    }

    val set = mutableSetOf<Int>()
    var ans = 0
    for (i in 25 downTo 0) {
        var freq = wordCount[i]
        while (freq > 0 && set.contains(freq)) {
            freq--
            ans++
        }
        set.add(freq)
    }
    return ans
}

fun main() {
    val ans = minDeletions("bbcebab")
    println(ans)
}


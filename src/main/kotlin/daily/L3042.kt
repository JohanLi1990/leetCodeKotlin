package daily

fun countPrefixSuffixPairs(words: Array<String>): Int {
    var ans = 0
    for (i in words.indices) {
        val cur = words[i]
        for (j in i + 1 until words.size) {
            val w = words[j]
            if (w.startsWith(cur) && w.endsWith(cur)) {
               ans++
            }
        }
    }
    return ans
}


fun main() {
    println(countPrefixSuffixPairs(arrayOf("a","aba","ababa","aa")))
    println(countPrefixSuffixPairs(arrayOf("pa","papa","ma","mama")))
    println(countPrefixSuffixPairs(arrayOf("abab","ab")))
}
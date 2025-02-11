package daily

import kotlin.math.max

fun wordSubsets(words1: Array<String>, words2: Array<String>): List<String> {
    val map = IntArray(26)
    for (word in words2) {
        val curMap = IntArray(26)
        for (c in word) {
            curMap[c - 'a']++
            map[c - 'a'] = max(map[c - 'a'], curMap[c - 'a'])
        }
    }

    fun _isUniversal(cur:IntArray, uni:IntArray):Boolean {
        for (i in uni.indices) {
            if (cur[i] < uni[i]) {
                return false
            }
        }
        return true
    }

    val ans = mutableListOf<String>()
    for (word in words1) {
        val curMap = IntArray(26)
        for (c in word) {
            curMap[c - 'a']++
        }
        if (_isUniversal(curMap, map)){
            ans.add(word)
        }
    }
    return ans

}


fun main() {
    println(wordSubsets(arrayOf("amazon","apple","facebook","google","leetcode", ), arrayOf("ee","o")))
}
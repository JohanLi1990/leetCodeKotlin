package daily

import java.util.*


// referred to this soln here: https://leetcode.com/problems/remove-all-occurrences-of-a-substring/solutions/6405236/kmp-algorithm-o-n-m-c-python-java/?envType=daily-question&envId=2025-02-11
// Normal Stack soln and String Manipulation is boring
// KMP is something I am not familiar with
fun removeOccurrences(s: String, part: String): String {
    // first of all get the LPS array
    fun _getLPS(p:String) : IntArray{
        val ans = IntArray(p.length)
        var len = 0
        var i = 1
        while(i < p.length) {
            if (p[len] == p[i]) {
                ans[i++] = ++len
            } else if (len == 0){
                ans[i++] = 0
            } else {
                // backtracking
                len = ans[len - 1]
            }
        }
        return ans
    }

    val lps = _getLPS(part)
    val res = CharArray(s.length)
    var j = 0
    val state = Stack<Int>()
    for( i in s.indices) {
        res[j++] = s[i]
        var k = if (state.isEmpty()) 0 else state.peek()
        while( k > 0 && part[k] != s[i]) {
            k = lps[k - 1]
        }
        if (part[k] == s[i]) {
            k++
        }
        state.push(k)
        if (k == part.length) {
            j -= part.length
            for (temp in part.indices) {
                state.pop()
            }
        }
    }
    return String(res, 0, j)

}

fun removeOccurrencesII(s: String, part: String): String {
    val result = StringBuilder(s)

    while (true) {
        val index = result.indexOf(part)
        if (index == -1) break
        result.delete(index, index + part.length)
    }

    return result.toString()
}

fun main() {
    println(removeOccurrences("daabcbaabcbc", "abc")) // "dab"
    println(removeOccurrences("axxxxyyyyb", "xy")) // "ab"
}
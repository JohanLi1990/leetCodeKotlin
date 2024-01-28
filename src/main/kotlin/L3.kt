import kotlin.math.max

class L3 {

    fun lengthOfLongestSubstring(s: String): Int {
        val map = hashMapOf<Char, Int>()

        var i = 0
        var j = 0
        var res = 0
        while (j < s.length) {
            val c = s[j]
            if (map.containsKey(c)) {
                // contract window
                val limit = map[c]
                while(i <= limit!!) {
                    map.remove(s[i])
                    i++
                }
            }
            map[s[j]] = j
            res = max(res, j  - i + 1)
            j++
        }
        return res;
    }
}

fun main() {
    val input1 = ""

    val ans = L3()
    println(ans.lengthOfLongestSubstring(input1))
}

fun lengthOfLongestSubstring(s: String): Int {
    var left = 0
    var right = 0
    val dict = IntArray(256)
    var max = 0

    while(right < s.length) {
        dict[s[right].code]++

        while(left < right && dict[s[right].code] > 1) dict[s[left++].code]--

        max = max(max, right - left + 1)
        right++
    }

    return max
}
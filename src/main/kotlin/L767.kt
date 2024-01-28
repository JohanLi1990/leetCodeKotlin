import kotlin.math.max

class L767 {

    fun reorganizeString(s: String): String {
        val map = IntArray(26)
        var max = 0
        var maxChar = 'c'
        for (c in s) {
            map[c - 'a'] += 1
            if (map[c - 'a'] > max) {
                maxChar = c
            }
            max = max(max, map[c - 'a'])
        }
        if (max - 1 > s.length - max){
            return ""
        }

        val res = mutableListOf<StringBuilder>()
        for (i in 0..<max) res.add(StringBuilder().append(maxChar))
        var j = 0
        for (i in 0..<26) {
            if (i == maxChar - 'a') continue
            while (map[i] > 0) {
                res[j].append('a' + i)
                j++
                j %= res.size
                map[i]--
            }
        }

        val realRes = StringBuilder()
        for (sb in res) {
            realRes.append(sb.toString())
        }
        return realRes.toString()

    }
}

fun main() {
    val input = "aaablov"
    val asn = L767()
    println(asn.reorganizeString(input))
}
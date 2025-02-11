package daily

import kotlin.math.max
import kotlin.math.min

fun maxScore(s: String): Int {

    val left = IntArray(s.length)
    val right = IntArray(s.length)

    for (i in 0..<s.length) {
        if (s[i] == '0') {
            left[i] = left[max(i - 1, 0)] + 1
        } else {
            left[i] = left[max(i - 1, 0)]
        }
    }

    for (i in s.length - 1 downTo 0) {
        if (s[i] == '1') {
            right[i] = right[min(i + 1, s.length - 1)] + 1
        } else {
            right[i] = right[min(i + 1, s.length - 1)]
        }
    }

    var ans = 0
    for (i in 0..s.length - 2) {
        val sum = left[i] + right[i + 1]
        ans = max(sum, ans)
    }
    return ans
}

fun main() {
    println(maxScore("011101")) // 5
    println(maxScore("00111")) //5
    println(maxScore("1111")) // 3
}
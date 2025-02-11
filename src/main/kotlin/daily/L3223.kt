package daily

fun minimumLength(s: String): Int {
    val count = IntArray(26)
    var ans = s.length
    for (c in s) {
        count[c - 'a']++
        if (count[c - 'a'] == 3) {
            count[c - 'a'] -= 2
            ans -= 2
        }
    }
    return ans
}

fun main() {
    println(minimumLength("abaacbcbb")) // 5
    println(minimumLength("aa")) // 2
}
package daily

fun getHappyString(n: Int, k: Int): String {

    val map = mutableMapOf<Char, CharArray>().apply {
        put('a', charArrayOf('b', 'c'))
        put('b', charArrayOf('a', 'c'))
        put('c', charArrayOf('a', 'b'))
        put('z', charArrayOf('a', 'b', 'c'))
    }
    var total = 3 * (1 shl (n - 1))
    if (total < k) return ""
    val ans = StringBuilder()
    var temp = k - 1
    var lastChar = 'z'
    for (i in 0..<n) {
        var groupSize = if (i == 0) total / 3 else total / 2
        total = if (i == 0) total / 3 else  total / 2
        val group = temp/groupSize
        val cur = map[lastChar]!![group]
        lastChar = cur
        ans.append(cur)
        temp %= groupSize
    }
    return ans.toString()
}

fun main() {
    println(getHappyString(1, 3)) // c
    println(getHappyString(1, 4)) // ""
    println(getHappyString(3, 9)) // cab
}
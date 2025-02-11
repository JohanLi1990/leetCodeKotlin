package daily


fun shiftingLetters(s: String, shifts: Array<IntArray>): String {
    val delta = IntArray(s.length + 1)
    for (shift in shifts) {
        if (shift[2] == 0) {
            delta[shift[0]] -= 1
            delta[shift[1] + 1] += 1
        } else {
            delta[shift[0]] += 1
            delta[shift[1] + 1] -= 1
        }

//        delta[shift[0]] = (delta[shift[0]] + 26) % 26
//        delta[shift[1] + 1] = (delta[shift[1] + 1] + 26) % 26
    }
//    println(delta.contentToString())
    return buildString {
        var curChange = 0
        for ((i, c) in s.withIndex()) {
            curChange += delta[i]
            val newChar = (c.code - 'a'.code +  curChange).mod(26) + 'a'.code
//            println("$c, ${newChar.toChar()}, $curChange")
            append(newChar.toChar())
        }
    }
}

fun main() {
    println(
        shiftingLetters(
            "abc", arrayOf(
                intArrayOf(0, 1, 0), intArrayOf(1, 2, 1), intArrayOf(0, 2, 1)
            )
        )
    ) // ace

    println(
        shiftingLetters(
            "dztz", arrayOf(
                intArrayOf(0, 0, 0), intArrayOf(1, 1, 1)
            )
        )
    ) // ace


}


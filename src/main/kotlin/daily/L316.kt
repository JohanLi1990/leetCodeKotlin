package daily

import kotlin.text.StringBuilder


fun removeDuplicateLetters(s: String): String {
    // keep a stack and a map
    // when encounter new letter
    // check if previous letter can be popped,
    // - if only instance, cannot
    // - if before last instance and lexicographically larger than current then pop that

    val last = IntArray(26)
    val seen = mutableSetOf<Char>()
    for (i in s.indices) {
        last[s[i] - 'a'] = i
    }

    val stack = ArrayDeque<Char>()

    for (i in s.indices) {
        val c = s[i]
        if (c in seen) continue
        while (stack.isNotEmpty()) {
            val pre = stack.first()
            if (last[pre - 'a'] >= i && pre > c) {
                stack.removeFirst()
                seen.remove(pre)
            } else {
                break
            }
        }
        stack.addFirst(c)
        seen.add(c)
    }

    return buildString {
        stack.forEach {
            append(it)
        }
    }.reversed()
}

fun main() {
    println(removeDuplicateLetters("bcabc")) // == abc
    println(removeDuplicateLetters("cbacdcbc")) // == acdb
}
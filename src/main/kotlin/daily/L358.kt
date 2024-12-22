package daily

import java.util.*
import kotlin.collections.ArrayList

class CharIntPair (c : Char) {
    val char = c
    var freq = 0
}

fun rearrangeString(s: String, k: Int): String {
    if (k <= 1) return s
    val map = ArrayList<CharIntPair>().apply {
        for (i in 0..25) {
            add(CharIntPair('a' + i ))
        }
    }
    for (i in s.indices) {
        val cur = s[i]
        map[cur - 'a'].freq += 1

    }

    map.sortBy {
        -it.freq
    }

    val numKeys = map.filter { it.freq > 0}.size

    // not possible
    if ((map[0].freq - 1) * k + 1 > s.length
        || numKeys < k ) return ""

    val list = ArrayList<StringBuilder>().apply {
        for (i in 0..<map[0].freq) {
            add(StringBuilder().apply { append(map[0].char) })
        }
    }
    var ptr = 0
    for (i in 1..25) {
        if (map[i].freq == 0) break
        if (map[i].freq == map[0].freq) {
            for (j in 0..<map[i].freq) {
                list[j].append(map[i].char)
            }
        } else {
            for (j in 0..<map[i].freq) {
                list[ptr++].append(map[i].char)
                ptr %= map[0].freq - 1
            }

        }
    }

    val res = StringBuilder().apply {
        for ((i, sb) in list.withIndex()) {
            if (i != list.size - 1 && sb.length < k) return ""
            append(sb.toString())
        }
    }
    return res.toString()
}

fun main() {
//    println(rearrangeString("abeabac",       3))
//    println(rearrangeString("aabbcc",       3))
//    println(rearrangeString("aaabc",       3))
//    println(rearrangeString("abeabac",       3))
//    println(rearrangeString("aaadbbcc",       2))
////
//    println(rearrangeString("a",       0))
//    println(rearrangeString("aabbcc",       4))
//    println(rearrangeString("abb",       2))
//    println(rearrangeString("bbabcaccaaabababbaaaaccbbcbacbacacccbbcaabcbcacaaccbabbbbbcacccaccbabaccbacabcabcacbbbabbbccbbccbccca"
//       , 2))
//    println(rearrangeString("abcabcabcd",       4)) //"abcabcabcd" 4
    println(rearrangeString("cihefgcceegaaebajgcfchhegfcehjbchfbjdjgdcedd",6))


}




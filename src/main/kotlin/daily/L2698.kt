package daily

import java.util.LinkedList

val set = mutableSetOf<Int>().apply {
    add(1)
    add(9)
    add(36)
}
fun punishmentNumber(n: Int): Int {
    var ans = 0
    for (i in 1..n) {
        if (set.contains(i) || isGoodNumber(i)) {
            ans += i * i
            set.add(i)
        }
    }
    return ans
}

fun backTrack(list : List<Int>, idx : Int,  cur : Int, target : Int) : Boolean {
    if (idx == list.size && cur == target) {
        return true
    }

    if (idx >= list.size || cur > target) return false

    // backtrack
    var curNum = 0
    for (i in idx..<list.size) {
        curNum = curNum * 10 + list[i]
        if (backTrack(list, i + 1, cur + curNum, target)) {
            return true
        }
    }
    return false
}

fun isGoodNumber(i : Int) : Boolean{
    var sq = i * i
    val list = ArrayDeque<Int>()
    while(sq > 0) {
        list.addFirst(sq % 10)
        sq /= 10
    }
    return backTrack(list, 0, 0, i);
}

fun main() {
    println(punishmentNumber(10)) // 182
    println(punishmentNumber(37)) // 1478
}
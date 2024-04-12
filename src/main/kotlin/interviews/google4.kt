package interviews

import kotlin.math.max

fun totalFruit(fruits: IntArray): Int {
// longest subarray that have only two unique elements
    // use sliding windows

    var res = Int.MIN_VALUE
    var j = 0
    val unique = HashMap<Int, Int>()
    for (i in fruits.indices) {
        unique[fruits[i]] = unique.getOrDefault(fruits[i], 0) + 1
        while(unique.size > 2) {
            // shrink the window
            unique[fruits[j]] = unique[fruits[j]]!! - 1
            if (unique[fruits[j]] == 0) {
                unique.remove(fruits[j])
            }
            j++
        }
        res = max(res, i - j + 1)
    }
    return res

}

fun main() {
    println(totalFruit(intArrayOf(1,2,3,2,2)))
    println(totalFruit(intArrayOf(0,1,2,2)))
    println(totalFruit(intArrayOf(1, 2, 1)))
}
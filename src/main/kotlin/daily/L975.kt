package daily

import java.util.TreeMap

fun oddEvenJumps(arr: IntArray): Int {

    // this is DP
    val dpEven = BooleanArray(arr.size)
    val dpOdd = BooleanArray(arr.size)
    dpEven[arr.size - 1] = true
    dpOdd[arr.size - 1] = true
    val map = TreeMap<Int, Int>().apply {
        put(arr[arr.size - 1], arr.size - 1)
    }
    var res = 1
    for (i in arr.size - 2 downTo 0) {
        var key = map.ceilingKey(arr[i])
        if (key != null) {
//            println("$key, ${map[key]}")
            dpOdd[i] = dpEven[map[key]!!]
        }

        key = map.floorKey(arr[i])
        if (key != null) {
//            println("$key, ${map[key]}")
            dpEven[i] = dpOdd[map[key]!!]
        }

        map[arr[i]] = i
        if (dpOdd[i]) res++
    }

    return res

}

fun main() {

    println(
        oddEvenJumps(
            intArrayOf(10, 13, 12, 14, 15)
        )
    ) //2

    println(oddEvenJumps(intArrayOf(2,3,1,1,4))) // 3
    println(oddEvenJumps(intArrayOf(5,1,3,4,2))) // 3


}
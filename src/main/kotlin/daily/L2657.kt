package daily

import kotlin.math.max

fun findThePrefixCommonArray(A: IntArray, B: IntArray): IntArray {
    val mapA:MutableMap<Int, Int> = mutableMapOf()
    val mapB:MutableMap<Int, Int> = mutableMapOf()
    for (i in A.indices) {
        mapA.put(A[i], i)
        mapB.put(B[i], i)
    }
    val ans = IntArray(A.size)
    for (i in 1..A.size) {
        val indA = mapA[i]
        val indB = mapB[i]
        ans[max(indA!!, indB!!)]++
    }

    for (i in ans.indices) {
        ans[i] += if (i - 1 >= 0) ans[i - 1] else 0
    }
    return ans

}

fun main() {
    println(findThePrefixCommonArray(intArrayOf(1,3,2,4), intArrayOf(3,1,2,4)).contentToString())
    println(findThePrefixCommonArray(intArrayOf(2, 3, 1), intArrayOf(3, 1, 2)).contentToString())
}
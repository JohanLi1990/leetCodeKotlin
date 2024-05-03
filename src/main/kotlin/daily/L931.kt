package daily

import kotlin.math.min

fun minFallingPathSum(matrix: Array<IntArray>): Int {

    var pre = IntArray(matrix[0].size)
    var res = Int.MAX_VALUE
    for (i in matrix.indices) {
        val cur = IntArray(matrix[0].size)
        for (j in 0..<matrix[0].size) {
            val leftUp = if (j - 1 >= 0) pre[j - 1] else Int.MAX_VALUE
            val up = pre[j]
            val rightUp = if (j + 1 < pre.size) pre[j + 1] else Int.MAX_VALUE

            cur[j] = min(leftUp, min(up, rightUp)) + matrix[i][j]
            if (i == matrix.size - 1) {
                res = min(res, cur[j])
            }
        }

        pre = cur
    }
    return res

}

fun main() {
    println(minFallingPathSum(arrayOf(
        intArrayOf(2, 1, 3),
        intArrayOf(6, 5, 4),
        intArrayOf(7, 8, 9)
    ))) // 13

    println(minFallingPathSum(arrayOf(
        intArrayOf(-19, 57),
        intArrayOf(-40 ,-5)
    ))) // -59


}
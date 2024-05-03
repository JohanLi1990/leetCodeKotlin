package daily

import kotlin.math.max
import kotlin.math.min

fun maximalRectangle(matrix: Array<CharArray>): Int {

    val pre = IntArray(matrix[0].size)
    var res = 0
    for (i in matrix.indices) {

        for (j in matrix[0].indices) {
            if (matrix[i][j] == '1') {
                pre[j] = pre[j] + 1
            } else {
                pre[j] = 0
            }
        }

        for (j in matrix[0].indices) {
            res = max(res, findMax(j, pre))

        }
    }
    return res

}

fun findMax(j: Int, pre: IntArray): Int {
    var min = pre[j]
    var k = j
    var res = 0
    while(k < pre.size) {
        min = min(pre[k], min)
        if (min == 0) break
        res = max(res, min * (k++ - j + 1))
    }
    return res
}

fun main() {

    val res = maximalRectangle(arrayOf(
        charArrayOf('1','0','1','0','0'),
        charArrayOf('1','0','1','1','1'),
        charArrayOf('1','1','1','1','1'),
        charArrayOf('1','0','0','1','0')
    ))
    println(res) // 6
}
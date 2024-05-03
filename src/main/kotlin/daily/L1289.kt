package daily

import kotlin.math.min

class L1289 {
    fun minFallingPathSum(grid: Array<IntArray>): Int {
        if (grid.size == 1) return grid[0][0]

        val numRows = grid.size
        val numCols = grid[0].size
        var res = Int.MAX_VALUE

        val prevLeft = IntArray(numCols)
        val prevRight = IntArray(numCols)

        for (i in 0..<numRows) {
            val curSum = IntArray(numCols)

            for (j in 0..<numCols) {
                val left = if (j - 1 >= 0) prevLeft[j - 1] else Int.MAX_VALUE
                val right = if (j + 1 < numCols) prevRight[j + 1] else Int.MAX_VALUE
                curSum[j] = min(left, right) + grid[i][j]
                if (i == numRows - 1) {
                    res = min(res, curSum[j])
                }
            }

            for (k in 0..<numCols) {
                prevLeft[k] = if (k - 1 >= 0) min(prevLeft[k - 1], curSum[k]) else curSum[k]
            }

            for (k in numCols - 1 downTo 0) {
                prevRight[k] = if (k + 1 < numCols) min(prevRight[k + 1], curSum[k]) else curSum[k]
            }
        }

        return res
    }
}


fun main() {
    val sol = L1289()
    println(sol.minFallingPathSum(arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    ))) // 13

    println(sol.minFallingPathSum(arrayOf(
        intArrayOf(17)
    ))) // 17


}
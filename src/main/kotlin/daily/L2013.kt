package daily

import kotlin.math.abs

class L2013 {
    private val mapByCol = HashMap<Int, HashSet<Int>>()
    private val pointCount = HashMap<Int, Int>()
    fun add(point: IntArray) {
        mapByCol[point[0]] = mapByCol.getOrDefault(point[0], HashSet()).apply {
            add(point[1])
        }
        val hash = point[0] * 1001 + point[1]
        pointCount[hash] = pointCount.getOrDefault(hash, 0) + 1
    }

    fun count(point: IntArray): Int {
        var res = 0
        for (col in mapByCol.keys) {
            if (col == point[0]) continue
            if (!mapByCol[col]!!.contains(point[1])
                || !mapByCol.containsKey(point[0])) {
                continue
            }

            val len = abs(col - point[0])
            res += countOtherCorners(len, col, point)
            res += countOtherCorners(-len, col, point)
        }
        return res
    }


    private fun countOtherCorners(len: Int, curCol:Int, point: IntArray): Int {
        val up = point[1] + len
        if (!mapByCol[point[0]]!!.contains(up)
            || !mapByCol[curCol]!!.contains(up)) {
            return 0
        }

        val upDiagonal = up + 1001 * curCol
        val upUp = up + 1001 * point[0]
        val curPHash = point[1]  + 1001 * point[0]
        val horizontal = curCol * 1001 + point[1]
        return pointCount.getOrDefault(upDiagonal, 0) *
                pointCount.getOrDefault(upUp, 0) *
                pointCount.getOrDefault(horizontal, 0)
    }


}

fun main() {
    L2013().run {
        add(intArrayOf(3, 10))
        add(intArrayOf(11, 2))
        add(intArrayOf(3, 2))
        println(count(intArrayOf(11, 10)))
        println(count(intArrayOf(14, 8)))
        add(intArrayOf(11, 2))
        println(count(intArrayOf(11, 10)))
    }
}
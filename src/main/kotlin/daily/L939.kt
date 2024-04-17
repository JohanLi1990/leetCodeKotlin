package daily

import java.util.TreeMap
import kotlin.math.abs
import kotlin.math.min

fun minAreaRect(points: Array<IntArray>): Int {

    // find the minimum rectangle
    val columnmap = TreeMap<Int, MutableSet<Int>>()

    for (point in points) {
        val i = point[0]
        columnmap[i] = columnmap.getOrDefault(i, HashSet()).apply {
            add(point[1])
        }

    }
    var minArea = Int.MAX_VALUE
    for (i in points.indices) {
        for (j in i + 1..<points.size) {
            val p1 = points[i]
            val p2 = points[j]
            if (p1[0] == p2[0] || p1[1] == p2[1]) continue

            // check coordinates of p3 p4
            if (columnmap[p1[0]]!!.contains(p2[1]) && columnmap[p2[0]]!!.contains(p1[1])) {
                minArea = min(abs(p1[0] - p2[0]) * abs(p1[1] - p2[1]), minArea)
            }
        }
    }
    return if (minArea == Int.MAX_VALUE) 0 else minArea

}

fun main() {
    println(minAreaRect(arrayOf(intArrayOf(1,1),
        intArrayOf(1,3), intArrayOf(3, 1), intArrayOf(3, 3), intArrayOf(2, 2))))
        // 4

    println(minAreaRect(arrayOf(intArrayOf(1,1),
        intArrayOf(1,3), intArrayOf(3, 1), intArrayOf(3, 3), intArrayOf(4, 1),
        intArrayOf(4, 3))))
}
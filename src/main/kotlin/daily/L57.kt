package daily

import kotlin.math.max
import kotlin.math.min

fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
    // ordered and non-overlapping
    if (intervals.isEmpty()) return arrayOf(newInterval)

    if (newInterval[0] > intervals[intervals.size - 1][1]) {
        return ArrayList<IntArray>().apply {
            intervals.forEach {
                add(it)
            }
            add(newInterval)
        }.toTypedArray()
    }

    if (newInterval[1] < intervals[0][0]) {
        return ArrayList<IntArray>().apply {
            add(newInterval)
            intervals.forEach {
                add(it)
            }
        }.toTypedArray()
    }
    var left = 0
    var right = intervals.size - 1
    while( left < right) {
        val mid = left + (right - left) / 2
        if (intervals[mid][1] < newInterval[0]) {
            left = mid + 1
        } else {
            right = mid
        }
    }

    while(right < intervals.size && newInterval[1] >= intervals[right][0]) {
        right++
    }
    val res = ArrayList<IntArray>()
    for (i in 0..<left) {
        res.add(intervals[i])
    }
    res.add(intArrayOf(min(intervals[left][0], newInterval[0]),
        max(intervals[right -1][1], newInterval[1] )))
    for (i in right..<intervals.size) {
        res.add(intervals[i])
    }

    return res.toTypedArray()


}

fun main() {
//    println(insert(arrayOf(intArrayOf(1,5)),
//        intArrayOf(6, 8)).joinToString {
//            "[${it.joinToString(",")}]"
//    }) // [1, 5], [6, 9]

    println(insert(arrayOf(intArrayOf(2,4),
        intArrayOf(5,7),
        intArrayOf(8,10),
        intArrayOf(11,13)), intArrayOf(3, 6)).joinToString {
        "[${it.joinToString(",")}]"
    })

//    println(insert(arrayOf<IntArray>(),
//        intArrayOf(2, 5)).joinToString {
//        "[${it.joinToString(",")}]"
//    })

}


package daily

import java.util.PriorityQueue
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sign

// https://leetcode.com/problems/find-building-where-alice-and-bob-can-meet
/**
 * First approach Priority Queue
 *
 */
fun leftmostBuildingQueries(heights: IntArray, queries: Array<IntArray>): IntArray {
    //[index, value]
    val pq = PriorityQueue<IntArray> { a, b -> a[0] - b[0] }
    val ans = IntArray(queries.size) { -1 }
    val queryMap = mutableMapOf<Int, MutableList<IntArray>>().apply {
        for (i in heights.indices) {
            put(i, mutableListOf())
        }
    }
    for ((i, arr) in queries.withIndex()) {
        val (a, b) = arr
        if (a == b || (a < b && heights[a] < heights[b]) || (a > b && heights[a] > heights[b])) {
            ans[i] = max(a, b)
            continue
        }
        val maxIndex = max(a, b)
        val maxVal = max(heights[a], heights[b])
        queryMap[maxIndex]!!.add(intArrayOf(maxVal, i))
    }

    for ((i, h) in heights.withIndex()) {
        while (!pq.isEmpty() && h > pq.peek()[0]) {
            val cur = pq.poll()
            ans[cur[1]] = i
        }
        for (arr in queryMap[i]!!) {
            pq.offer(arr)
        }
    }

    return ans

}

class SGTree(n : Int) {
    val seg = IntArray(4 * n + 1)

    fun build(idx : Int, low:Int, high: Int, arr:IntArray) {
        if (low == high) {
            seg[idx] = arr[low]
            return
        }

        var mid = low + (high - low) / 2
        build(2 * idx + 1, low, mid, arr)
        build(2 * idx + 2, mid + 1, high, arr);
        seg[idx] = max(seg[2 * idx + 1], seg[2 * idx + 2])
    }

    fun printArray() {
        println(seg.contentToString())
    }

    fun query(idx : Int, low : Int, high: Int, l : Int, r : Int) : Int {
        if (high < l || low > r) {
            return -1
        }

        // complete overlap
        if (low >= l && high <= r) {
            return seg[idx]
        }

        val mid = low + (high - low) / 2
        val left = query(2 * idx + 1, low, mid, l, r)
        val right = query(2 * idx + 2, mid + 1, high, l, r)
        return max(left, right)

    }

}

// segment tree approach
fun leftmostBuildingQueriesII(heights: IntArray, queries: Array<IntArray>): IntArray {
    val q = queries.size
    val n = heights.size
    val segTree = SGTree(n)

    segTree.build(0, 0, n - 1, heights)
    val ans = IntArray(queries.size)
    for ((i, query) in queries.withIndex()) {
        val (a, b) = query
        if (a == b || heights[max(a, b)] > heights[min(a, b)]){
            ans[i] = max(a, b)
            continue
        }
        val maxHeight = max(heights[a], heights[b])
        var l = max(a, b) + 1
        var r = n - 1
        var curr = -1
        while( l  <= r) {
            val mid = (l + r) /2
            val max = segTree.query(0, 0, n - 1, l, mid)
            if (max > maxHeight) {
                curr = mid
                r = mid - 1
            } else {
                l = mid + 1
            }
        }
        ans[i] = curr
    }

    return ans
}

fun main() {
    val ans = leftmostBuildingQueriesII(
        heights = intArrayOf(6,4,8,5,2,7),
        queries = arrayOf(
            intArrayOf(0, 1), intArrayOf(0, 3), intArrayOf(2, 4), intArrayOf(3, 4), intArrayOf(2, 2)
        )
    )


    println(ans.contentToString())


    val ans1 = leftmostBuildingQueriesII(
        heights = intArrayOf(5,3,8,2,6,1,4,6),
        queries = arrayOf(
            intArrayOf(0, 7), intArrayOf(3, 5), intArrayOf(5, 2), intArrayOf(3, 0), intArrayOf(1, 6)
        )
    )

    println(ans1.contentToString())

//    [4,2,1,3]
//    [[0,0],[0,1],[0,2],[0,3],[1,0],[1,1],[1,2],[1,3],[2,0],[2,1],[2,2],[2,3],[3,0],[3,1],[3,2],[3,3]]
    val ans2 = leftmostBuildingQueriesII(
        heights = intArrayOf(4, 2, 1, 3),
        queries = arrayOf(
            intArrayOf(0, 0), intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(0, 3),
            intArrayOf(1, 0), intArrayOf(1, 1), intArrayOf(1, 2), intArrayOf(1, 3),
            intArrayOf(2, 0), intArrayOf(2, 1), intArrayOf(2, 2), intArrayOf(2, 3),
            intArrayOf(3, 0), intArrayOf(3, 1), intArrayOf(3, 2), intArrayOf(3, 3)
        )
    )

    println(ans2.contentToString())
}
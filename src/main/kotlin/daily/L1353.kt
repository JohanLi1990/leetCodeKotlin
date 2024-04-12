package daily

import java.util.PriorityQueue


fun maxEvents(events: Array<IntArray>): Int {
    // should put array in a minHeap, which ever comes first should
    // be considered first

    val heap = PriorityQueue<Int>()
    events.sortWith {
        a, b -> a[0] - b[0]
    }
    var i = 0
    var res = 0
    for (d in 1..100000) {
        while(heap.isNotEmpty() && heap.peek() < d) {
            heap.poll()
        }
        while(i < events.size && events[i][0] == d) {
            heap.offer(events[i++][1])
        }
        if (heap.isNotEmpty()) {
            res++
            heap.poll()
        }
    }


    return res

}

fun main() {
    val events = arrayOf(
        intArrayOf(1, 2), intArrayOf(2, 3), intArrayOf(3, 4)
    )
    val events1 = arrayOf(
        intArrayOf(2, 2), intArrayOf(2, 2)
    )

    val events2 = arrayOf(
        intArrayOf(1, 4),
        intArrayOf(4, 4),
        intArrayOf(2, 2),
        intArrayOf(3, 4),
        intArrayOf(1, 1)
    )

    //[[1,5],[1,5],[1,5],[2,3],[2,3]]
    val events3 = arrayOf(
        intArrayOf(1, 5),
        intArrayOf(1, 5),
        intArrayOf(1, 5),
        intArrayOf(2, 3),
        intArrayOf(2, 3)
    )

    val events4 = arrayOf(
//        [[1,2],[1,2],[1,6],[1,2],[1,2]]
        intArrayOf(1, 2),
        intArrayOf(1, 2),
        intArrayOf(1, 6),
        intArrayOf(1, 2),
        intArrayOf(1, 2)
    )
    println(maxEvents(events2)) // 4

}
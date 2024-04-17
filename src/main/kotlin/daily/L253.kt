package daily

import java.util.PriorityQueue


fun minMeetingRooms(intervals: Array<IntArray>): Int {

    intervals.sortBy { it[0] }
    val queue = PriorityQueue<Int>()
    for (meet in intervals) {
        if (queue.isEmpty()) {
            queue.offer(meet[1])
        } else {
            val earliest = queue.peek()
            if (earliest <= meet[0]) {
                queue.poll()
            }
            queue.offer(meet[1])
        }
    }
    return queue.size
}

fun main() {
    println(
        minMeetingRooms(
            arrayOf(
                intArrayOf(0, 30),
                intArrayOf(5, 10), intArrayOf(15, 20)
            )
        )
    ) // false

    println(
        minMeetingRooms(
            arrayOf(
                intArrayOf(7, 10),
                intArrayOf(2, 4)
            )
        )
    ) // true
}
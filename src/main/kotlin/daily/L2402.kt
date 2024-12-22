package daily

import java.util.*
import kotlin.math.min

class L2402 {
    fun mostBooked(n: Int, meetings: Array<IntArray>): Int {
        meetings.sortBy { it[0] }
        val q = PriorityQueue<Room>{
            a, b -> if (a.earliest == b.earliest) a.id - b.id else a.earliest - b.earliest
        }
        val available = PriorityQueue<Room>(){a, b -> a.id - b.id}.apply {
            for (i in 0..<n) {
                offer(Room(i))
            }
        }
        val res = arrayOf(n + 1, 0)
        for (meet in meetings) {
            // when we haven't used all rooms we can allocate new one
            if (q.isEmpty() || (q.peek().earliest > meet[0] && available.isNotEmpty())) {
                val curRoom = available.poll().also {
                    it.earliest = meet[1]
                    it.num++
                    if (it.num > res[1]) {
                        res[1] = it.num
                        res[0] = it.id
                    } else if (it.num == res[1]) {
                        res[0] = min(res[0], it.id)
                    }
                }
                q.offer(curRoom)
                continue
            }

            while (q.isNotEmpty() && q.peek().earliest <= meet[0]) {
                available.offer(q.poll())
            }

            if (available.isEmpty()) {
                // no room is free
                val curRoom = q.poll().also {
                    it.earliest += meet[1] - meet[0]
                    it.num++
                    if (it.num > res[1]) {
                        res[1] = it.num
                        res[0] = it.id
                    } else if (it.num == res[1]) {
                        res[0] = min(res[0], it.id)
                    }
                }
                q.offer(curRoom)
            } else {

                val curRoom = available.poll().also {
                    it.earliest = meet[1]
                    it.num++
                    if (it.num > res[1]) {
                        res[1] = it.num
                        res[0] = it.id
                    } else if (it.num == res[1]) {
                        res[0] = min(res[0], it.id)
                    }
                }
                q.offer(curRoom)
            }

        }
        return res[0]

    }



    inner class Room(val id:Int){
        var num = 0
        var earliest = 0
    }

}

fun main() {
    val sol = L2402()
    println(sol.mostBooked(2, arrayOf(
        intArrayOf(0, 10),
        intArrayOf(1, 5),
        intArrayOf(2, 7),
        intArrayOf(3, 4)
    ))) // 0

    println(sol.mostBooked(3, arrayOf(
        intArrayOf(1, 20),
        intArrayOf(2, 10),
        intArrayOf(3, 5),
        intArrayOf(4, 9),
        intArrayOf(6, 8)
    ))) // 0

    println(sol.mostBooked(4, arrayOf(
        intArrayOf(18, 19),
        intArrayOf(3, 12),
        intArrayOf(17, 19),
        intArrayOf(2, 13),
        intArrayOf(7, 10)
    ))) // 0
}

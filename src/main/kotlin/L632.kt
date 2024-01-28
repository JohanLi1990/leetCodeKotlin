import java.util.PriorityQueue
import kotlin.math.max

class L632 {
}

fun smallestRange(nums: List<List<Int>>): IntArray {
    val res = intArrayOf(-100_000, 100_000)
    val pq = PriorityQueue<IntArray>(){
        a,b -> a[0] - b[0]
    }
    var max = Int.MIN_VALUE
    val k = nums.size

    for (i in 0..<k) {
        val minElem = nums[i][0]
        val arr = intArrayOf(minElem, 0, i )
        max = max(max, minElem)
        pq.offer(arr)
    }

    do {
        val min = pq.poll()
        if (res[1] - res[0] > max - min[0]) {
            res[0] = min[0]
            res[1] = max
        }
        min[1]++
        val cur = nums[min[2]]
        if (min[1] == cur.size){
            break
        }

        min[0] = cur[min[1]]
        max = max(max, min[0])
        pq.offer(min)
    } while (true)
    return res
}

fun main() {
    val input = listOf(
        intArrayOf(4,10,15,24,26).toList(),
        intArrayOf(0,9,12,20).toList(), intArrayOf(5,18,22,30).toList()
    )

    println( smallestRange(input).contentToString())
}
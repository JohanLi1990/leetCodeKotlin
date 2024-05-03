package daily

import java.util.LinkedList
import java.util.TreeMap
import kotlin.math.max
import kotlin.math.min

class MKAverage(m: Int, k: Int) {
    private val map = TreeMap<Int, Int>()
    private val q = LinkedList<Int>()
    private val cap = m
    private val lastK = k
    private var sum = 0
    fun addElement(num: Int) {
        q.offer(num)
        map[num] = map.getOrDefault(num, 0) + 1
        sum += num
        if (q.size > cap) {
            val last = q.poll()
            map[last] = map[last]!! - 1
            if (map[last]!! == 0) {
                map.remove(last)
            }
            sum -= last
        }
    }

    fun calculateMKAverage(): Int {
        if (q.size < cap) return -1
        var toRemove = lastK
        var curSum = sum
        var curKey = map.firstKey() - 1
        while(toRemove > 0) {
            curKey = map.ceilingKey(curKey)
            curSum -= min(map[curKey]!!, toRemove) * curKey
            toRemove -= map[curKey]!!
            curKey += 1
        }
        toRemove = lastK
        curKey = map.lastKey() + 1
        while(toRemove > 0) {
            curKey =map.floorKey(curKey)
            curSum -= min(map[curKey]!!, toRemove) * curKey
            toRemove -= map[curKey]!!
            curKey -= 1
        }

        return curSum / (cap - 2 * lastK)
    }

}

fun main() {
    val sol = MKAverage(3, 1)

    sol.let {
        it.addElement(3)
        it.addElement(1)
        println(it.calculateMKAverage())
        it.addElement(10)
        println(it.calculateMKAverage())
        it.addElement(5)
        it.addElement(5)
        it.addElement(5)
        println(it.calculateMKAverage())
    }
}
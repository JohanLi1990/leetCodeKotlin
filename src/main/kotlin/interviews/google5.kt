package interviews

import java.util.LinkedList
import java.util.Queue

fun anagramMappings(nums1: IntArray, nums2: IntArray): IntArray {
    // may contain duplicates
    val map = HashMap<Int, Queue<Int>>()
    for (num in nums2.withIndex()) {
        if (map[num.value] == null) {
            map[num.value] = LinkedList()
        }
        map[num.value]!!.offer(num.index)
    }

    val res = IntArray(nums1.size)
    for (i in nums1.indices) {
        val j = map[nums1[i]]!!.poll()
        res[i] = j
    }
    return res


}

fun main() {
    println(anagramMappings(intArrayOf(12,28,46,32,50),
        intArrayOf(50,12,32,46,28)).print()) // [1, 4, 3, 2, 0]

    println(anagramMappings(intArrayOf(84, 46), intArrayOf(84, 46)).print())
}

fun IntArray.print() : String {
    val res = StringBuilder()

    forEach {
        res.append("$it,")
    }

    return res.toString()
}
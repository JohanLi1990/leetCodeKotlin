package daily

import kotlin.math.max

//https://leetcode.com/problems/max-chunks-to-make-sorted/
//https://leetcode.com/problems/max-chunks-to-make-sorted-ii/
fun maxChunksToSorted(arr: IntArray): Int {
    // monotonic stack or comparing of sequence
    val maxStack = ArrayDeque<Int>()
    for (v in arr) {
        if (maxStack.isEmpty()) {
            maxStack.addLast(v)
            continue
        }
        val curMax = maxStack.last()
        while(!maxStack.isEmpty() && v < maxStack.last()) {
            maxStack.removeLast()
        }
        maxStack.addLast(max(curMax, v))
//        println(maxStack)
    }
    return maxStack.size
}

fun main() {
    val ans1 = maxChunksToSorted(intArrayOf(1,0,2,3,4))
    println(ans1)

    val ans2 = maxChunksToSorted(intArrayOf(4,3,2,1,0))
    println(ans2)

    val ans3 = maxChunksToSorted(intArrayOf(0,2,1))
    println(ans3)

    val ans4 = maxChunksToSorted(intArrayOf(0,4,5,2,1,3))
    println(ans4)
    // 1, 2, 0, 4
    // 1 ==> [1],0      [1],0
    // 2 ==> [2],1      [1, 2],0
    // 0 ==> [2, 0],1   [0],1
    // 4 ==> [4],2      [0,4],1

    // 3, 0, 2, 1, 4

}
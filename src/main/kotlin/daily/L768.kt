package daily

import kotlin.math.max

// https://leetcode.com/problems/max-chunks-to-make-sorted/
// https://leetcode.com/problems/max-chunks-to-make-sorted-ii/
// https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
//TODO: Next
fun maxChunksToSortedII(arr: IntArray): Int {
    // monotonic stack or comparing of sequence
    // for each chunk i in arr[chunk_i, chunk_i1, chunk_i2...], we need to make sure
    // max(chunk_i) < min(chunk_i1)
    // we can put local max in a monotonic max stack, and find local min dynamically by traversing the arr
    // at the end of the traversal, number of max remaining in stack will be the number of chunks

    val stack = ArrayDeque<Int>()
    for (v in arr) {
        var curMax = if (stack.isEmpty()) 0 else stack.last()
        while (!stack.isEmpty() && v < stack.last()) {
            stack.removeLast()
        }
        stack.add(max(curMax, v))
    }
    return stack.size
}

fun main() {
    val ans4 = maxChunksToSortedII(intArrayOf(0, 4, 5, 2, 1, 3))
    println(ans4)

    val ans5 = maxChunksToSortedII(intArrayOf(5,4,3,2,1))
    println(ans5)

}
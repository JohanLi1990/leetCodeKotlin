package daily

import kotlin.math.max
import kotlin.math.min

fun minOperations(boxes: String): IntArray {
    // o(n) * 2, two traversal
    val left = IntArray(boxes.length)
    val right = IntArray(boxes.length)
    var oneSoFar = 0
    for ((i, c) in boxes.withIndex()) {
        if (oneSoFar > 0) {
            left[i] = left[max(i - 1, 0)] + oneSoFar
        }
        oneSoFar += if (c == '1') 1 else 0
    }
    oneSoFar = 0
    for (i in boxes.length - 1 downTo 0) {
        if (oneSoFar > 0) {
            right[i] = right[min(i + 1, boxes.length - 1)] + oneSoFar
        }
        oneSoFar += if (boxes[i] == '1') 1 else 0
    }

    val ans = IntArray(boxes.length)
    for (i in 0..<boxes.length) {
        ans[i] = left[i] + right[i]
    }
    return ans
}

fun main() {
    println(minOperations("110").contentToString()) // [1,1,3]
    println(minOperations("001011").contentToString()) // [11,8,5,4,3,4]
}
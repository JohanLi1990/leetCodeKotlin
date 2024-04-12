package interviews

import kotlin.math.max

/**
 * 388. Longest Absolute File Path
 */
fun lengthLongestPath(input: String): Int {

    var maxLen = 0
    val stack = ArrayDeque<Int>()
    stack.addFirst(0) // push
    for ( str in input.split("\n")) {

        val level = str.lastIndexOf("\t") + 1
        while(stack.size > level + 1) {
            stack.removeFirst()
        }

        val curLen = stack.first()  + str.length - level + 1 // remove \t, leave with one \
        if (str.contains(".")) {
            maxLen = max(maxLen, curLen - 1)
        } else {
            stack.addFirst(curLen)
        }
    }

    return maxLen

}

fun main() {
    // should be 20
    println(lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"))
    // should be 32
    println(lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"))
}
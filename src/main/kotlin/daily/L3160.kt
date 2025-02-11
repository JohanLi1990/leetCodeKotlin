package daily

fun queryResults(limit: Int, queries: Array<IntArray>): IntArray {
    val colorMap = mutableMapOf<Int, Int>()
    val ballColor = mutableMapOf<Int, Int>()
    val ans = IntArray(queries.size)
    for ((i, q) in queries.withIndex()) {
        if (ballColor.containsKey(q[0])) {
            val oldColor = ballColor[q[0]]
            colorMap[oldColor!!] = colorMap[oldColor]!! - 1
            if (colorMap[oldColor] == 0) {
                colorMap.remove(oldColor)
            }
        }
        ballColor[q[0]] = q[1]
        colorMap[q[1]] = colorMap.getOrDefault(q[1], 0) + 1
        ans[i] = colorMap.keys.size
    }
    return ans
}

fun main() {
    println(queryResults(4, arrayOf(
        intArrayOf(1, 4),
        intArrayOf(2, 5),
        intArrayOf(1, 3),
        intArrayOf(3, 4)
        )).contentToString()) // [1,2,2,3]

    //limit = 4, queries = [[0,1],[1,2],[2,2],[3,4],[4,5]]
    println(queryResults(4, arrayOf(
        intArrayOf(0, 1),
        intArrayOf(1, 2),
        intArrayOf(2, 2),
        intArrayOf(3, 4),
        intArrayOf(4, 5)
    )).contentToString()) // [1,2,2,3,4]

}
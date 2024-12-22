package daily

// https://leetcode.com/problems/maximum-number-of-k-divisible-components/


fun maxKDivisibleComponents(n: Int, edges: Array<IntArray>, values: IntArray, k: Int): Int {
    fun _dfs(
        curPoint: Int,
        weight: IntArray,
        graph: MutableList<MutableList<Int>>,
        visited: BooleanArray,
        numOfComponents: IntArray,
        k: Int
    ): Long {
        if (graph[curPoint].isEmpty()) {
            if (weight[curPoint] % k == 0) {
                numOfComponents[0]++
                return 0
            }
            return weight[curPoint].toLong()
        }
        visited[curPoint] = true
        var res = weight[curPoint].toLong()
        for (child in graph[curPoint]) {
            if (!visited[child]) {
                res += _dfs(child, weight, graph, visited, numOfComponents, k).toLong()
            }
        }
        if (res % k == 0L) {
            numOfComponents[0]++
            return 0L
        }
        return res

    }

    val graph = mutableListOf<MutableList<Int>>().apply {
        for (i in 0..<n) {
            add(mutableListOf())
        }
    }
    for (arr in edges) {
        val a = arr[0]
        val b = arr[1]
        graph[a].add(b)
        graph[b].add(a)
    }

    // dfs the graph
    val visited = BooleanArray(n)
    val ans = intArrayOf(0)
    _dfs(0, values, graph, visited, ans, k)
    return ans[0]
}

fun main() {
    val ans = maxKDivisibleComponents(
        5, arrayOf(
            intArrayOf(0, 2), intArrayOf(1, 2), intArrayOf(1, 3), intArrayOf(2, 4)
        ), intArrayOf(1, 8, 1, 4, 4), 6)
    println(ans)

    val ans1 = maxKDivisibleComponents(
        7, arrayOf(
            intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(1, 3), intArrayOf(1, 4), intArrayOf(2, 5),
            intArrayOf(2, 6)
        ), intArrayOf(1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000, 1000000000), 7)
    println(ans1)
}
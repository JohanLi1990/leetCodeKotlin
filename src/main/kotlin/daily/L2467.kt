package daily

import java.util.*
import kotlin.math.max

fun mostProfitablePath(edges: Array<IntArray>, bob: Int, amount: IntArray): Int {
    val graph = mutableMapOf<Int, MutableList<Int>>()
    for ((u, v) in edges) {
        graph.computeIfAbsent(u) { mutableListOf() }.add(v)
        graph.computeIfAbsent(v) { mutableListOf() }.add(u)
    }

    val bobPath = mutableMapOf<Int, Int>()
    val visited = BooleanArray(amount.size)

    fun findBobPath(bobCur: Int, seq: Int): Boolean {
        if (bobCur == 0) return true
        for (child in graph[bobCur] ?: emptyList()) {
            if (!visited[child]) {
                bobPath[child] = seq
                visited[child] = true
                if (findBobPath(child, seq + 1)) return true
                bobPath.remove(child)
                visited[child] = false
            }
        }
        return false
    }

    bobPath[bob] = 0
    visited[bob] = true
    findBobPath(bob, 1)

    var maxProfit = Int.MIN_VALUE
    val queue: Queue<Pair<Int, Int>> = LinkedList()
    val visitedAlice = BooleanArray(amount.size)
    queue.offer(0 to amount[0])
    var round = 1

    while (queue.isNotEmpty()) {
        repeat(queue.size) {
            val (node, profit) = queue.poll()
            visitedAlice[node] = true
            var isLeaf = true

            for (child in graph[node] ?: emptyList()) {
                if (!visitedAlice[child]) {
                    isLeaf = false
                    var nextProfit = profit
                    bobPath[child]?.let { bobRound ->
                        nextProfit += when {
                            bobRound > round -> amount[child]
                            bobRound == round -> amount[child] / 2
                            else -> 0
                        }
                    } ?: run {
                        nextProfit += amount[child]
                    }
                    queue.offer(child to nextProfit)
                }
            }

            if (isLeaf) {
                maxProfit = max(maxProfit, profit)
            }
        }
        round++
    }
    return maxProfit
}

fun main() {
    val testCases = listOf(
        Triple(arrayOf(intArrayOf(0, 1), intArrayOf(1, 2), intArrayOf(1, 3), intArrayOf(3, 4)), 3, intArrayOf(-2, 4, 2, -4, 6)),
        Triple(arrayOf(intArrayOf(0, 1)), 1, intArrayOf(-7280, 2350))
    )

    for ((edges, bob, amount) in testCases) {
        println(mostProfitablePath(edges, bob, amount))
    }
}

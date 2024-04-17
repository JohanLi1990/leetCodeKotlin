package daily

import java.util.LinkedList

fun minReorder(n: Int, connections: Array<IntArray>): Int {

    val graph = mutableMapOf<Int, MutableList<Neighbour>>()

    for (conn in connections) {
        val from = conn[0]
        graph[from] = graph.getOrDefault(from, ArrayList()).apply {
            add(Neighbour(conn[1], true))
        }

        val to = conn[1]
        graph[to] = graph.getOrDefault(to, ArrayList()).apply {
            add(Neighbour(conn[0], false))
        }

    }

    // bfs
    var needReverse = 0
    val visited = HashSet<Int>().apply { add(0) }
    val q = LinkedList<Int>()
    q.offer(0)

    while(q.isNotEmpty()) {
        val next = q.poll()
        for (nei in graph[next]!!) {
            if (visited.contains(nei.id )) continue
            if (nei.isFrom) {
                needReverse++
            }
            visited.add(nei.id)
            if (visited.size == n) return needReverse
            q.offer(nei.id)
        }
    }
    return needReverse

}

data class Neighbour(val id : Int, val isFrom: Boolean)

fun main() {
    println(minReorder(6, arrayOf(intArrayOf(0,1),
        intArrayOf(1, 3), intArrayOf(2, 3),
        intArrayOf(4, 0), intArrayOf(4, 5))))

    println(minReorder(5, arrayOf(intArrayOf(1,0),
        intArrayOf(1, 2), intArrayOf(3, 2),
        intArrayOf(3, 4))))

    println(minReorder(3, arrayOf(intArrayOf(1, 0), intArrayOf(2, 0))))
}
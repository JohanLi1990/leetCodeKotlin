package daily

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

fun minimumSemesters(n: Int, relations: Array<IntArray>): Int {
    // bfs
    val deps = HashMap<Int, Int>().apply {
        for (i in 1..n) {
            this[i] = 0
        }
    }
    val graph = HashMap<Int, ArrayList<Int>>().apply {
        for (i in 1..n) {
            this[i] = ArrayList()
        }
    }



    for (edge in relations) {
        graph.getOrDefault(edge[0], ArrayList()).add(edge[1])
        deps[edge[1]] = deps.getOrDefault(edge[1], 0) + 1
    }

    val q = LinkedList<Int>()
    for (i in 1..n) {
        if (deps[i] == 0) {
            q.offer(i)
        }
    }
    var res = 0
    var courses = 0
    while(q.isNotEmpty()) {
        var size = q.size
        while(size-- > 0) {
            val cur = q.poll()
            courses++
            for (child in graph[cur]!!) {
                deps[child] = deps[child]!! - 1
                if (deps[child] == 0) {
                    q.offer(child)
                }
            }
        }
        res++
    }
    return if (courses == n) res else -1

}


fun main() {
    println(minimumSemesters(3, arrayOf(
        intArrayOf(1, 3),
        intArrayOf(2, 3)
    ))) // 2

    println(minimumSemesters(3, arrayOf(
        intArrayOf(1, 2),
        intArrayOf(2, 3),
        intArrayOf(3, 1)
    ))) // -1
}
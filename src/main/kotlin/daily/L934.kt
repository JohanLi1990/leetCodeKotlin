package daily

class L934 {

    private val dirs = intArrayOf(-1, 0, 1, 0, -1)
    fun shortestBridge(grid: Array<IntArray>): Int {
        // start from one island
        // find the first 1, and then flood the island, add all the coordinates to the queue
        // use bfs to find other islands
        val q = ArrayDeque<IntArray>()
        val visited = Array(grid.size) { BooleanArray(grid[0].size) }
        outerLoop@ for (i in grid.indices){
            for (j in 0..< grid[0].size) {
                if (grid[i][j] == 1) {
                    flood(grid, i, j)
                    break@outerLoop
                }
            }
        }

        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == -1) {
                    q.addFirst(intArrayOf(i, j))
                    visited[i][j] = true
                }
            }
        }

        var step = 0
        while(!q.isEmpty()) {
            val size = q.size
            for (i in 0..<size) {
                val cur = q.removeLast()
                for (j in 0..dirs.size - 2) {
                    val nx = cur[0] + dirs[j]
                    val ny = cur[1] + dirs[j + 1]

                    if (nx < 0 || ny < 0 || nx >= grid.size || ny >= grid[0].size
                        || visited[nx][ny]) {
                        continue
                    }
                    visited[nx][ny] = true
                    if (grid[nx][ny] == 1) return step
                    q.addFirst(intArrayOf(nx, ny))
                }
            }
            step++
        }
        return -1

    }

    private fun flood(grid: Array<IntArray>, i: Int, j: Int) {
        if (i <  0 || j < 0 || i >= grid.size || j >= grid[0].size || grid[i][j] != 1) {
            return
        }
        grid[i][j] = -1
        flood(grid, i - 1, j)
        flood(grid, i + 1, j)
        flood(grid, i, j - 1)
        flood(grid, i, j + 1)
    }

}

fun main() {
    val input = arrayOf(
        intArrayOf(1,1,1,1,1),
        intArrayOf(1,0,0,0,1),
        intArrayOf(1,0,1,0,1),
        intArrayOf(1,0,0,0,1),
        intArrayOf(1,1,1,1,1)
    )

    val ans = L934()
    println(ans.shortestBridge(input))
}
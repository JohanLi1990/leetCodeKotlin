class L200 {

    fun numIslands(grid: Array<CharArray>): Int {
        // for each 1, dfs, convert them to -1
        var res = 0
        for (i in grid.indices) {
            for (j in grid[0].indices) {
                if (grid[i][j] == '1') {
                    flood(grid, i, j)
                    res++
                }
            }
        }
        return res

    }

    private fun flood(grid: Array<CharArray>, i: Int, j: Int) {
        if (i < 0 || j < 0 || i >= grid.size || j >= grid[0].size || grid[i][j] != '1'){
            return
        }
        grid[i][j] = '2'
        flood(grid, i + 1, j)
        flood(grid, i - 1, j)
        flood(grid, i, j + 1)
        flood(grid, i, j - 1)

    }


}

fun main() {
    val input = arrayOf(
        charArrayOf('1', '1', '1', '1', '0'),
        charArrayOf('1', '1', '0', '1', '0'),
        charArrayOf('1', '1', '0', '0', '0'),
        charArrayOf('0', '0', '0', '0', '0')
    )
    val ans = L200()
    println(ans.numIslands(input))
}
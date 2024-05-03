package daily


class L1101 {

    fun earliestAcq(logs: Array<IntArray>, n: Int): Int {
        // union find, earliest time that group becomes 1
        val ufsol = DJS(n)
        logs.sortBy {
            it[0]
        }

        for (log in logs) {
            if (ufsol.union(log[1], log[2]) == 1) {
                return log[0]
            }
        }

        return -1
    }

    private inner class DJS(val size: Int) {

        private var group = size
        private val parent = IntArray(size).apply {
            for(i in this.indices) {
                this[i] = i
            }
        }

       private val rank = IntArray(size).apply {
            for(i in this.indices) {
                this[i] = 1
            }
        }

        fun find(x : Int) : Int {
            val curP = parent[x]
            if (x != curP) {
                parent[x] = find(curP)
            }
            return parent[x]
        }

        fun union(x: Int, y:Int) : Int {
            // return current group
            val px = find(x)
            val py = find(y)
            if (px == py) {
                return group
            }
            group--
            if (rank[px] >= rank[py]) {
                parent[py] = px
                rank[px] += rank[py]
            } else {
                parent[px] = py
                rank[py] += rank[px]
            }
            return group
        }
    }
}

fun main() {

//    [20190107,2,3],[20190211,1,5],[20190224,2,4],[20190301,0,3],[20190312,1,2],[20190322,4,5]], n = 6
    val input = arrayOf(
        intArrayOf(20190101,0,1),
        intArrayOf(20190104,3,4),
        intArrayOf(20190107,2,3),
        intArrayOf(20190211,1,5),
        intArrayOf(20190224,2,4),
        intArrayOf(20190301,0,3),
        intArrayOf(20190312,1,2),
        intArrayOf(20190322,4,5)
    )

    val ans =L1101()
    println(ans.earliestAcq(input, 6))
}
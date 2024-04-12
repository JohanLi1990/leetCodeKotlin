package daily

import util.Node1

class L1650 {
//    var res:Node1? = null
    val visited = mutableSetOf<Int>()
    fun lowestCommonAncestor(p: Node1?, q: Node1?): Node1? {
        if (p == null || q == null) return null
        if (p.`val` == q.`val`) return p

        if (dfs(p.left, q.`val`) != null || dfs(p.right, q.`val`) != null) {
            return p
        }
        visited.add(p.`val`)
        return lowestCommonAncestor(p.parent, q)

    }
    private fun dfs(cur : Node1?, target:Int) : Node1? {
        if (cur == null || visited.contains(cur.`val`)) return null
        if (cur.`val` == target) return cur
        val res = dfs(cur.left, target) ?: dfs(cur.right, target)
        return if (res != null)  cur else null
    }

}










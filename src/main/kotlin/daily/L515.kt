package daily

import util.TreeNode
import kotlin.math.max

fun largestValues(root: TreeNode?): List<Int> {
    //bfs
    if (root == null) return emptyList()
    val q = ArrayDeque<TreeNode>()

    var cur = root
    q.add(cur)
    val ans = mutableListOf<Int>()
    while(!q.isEmpty()) {
        var size = q.size
        var max = Int.MIN_VALUE
        while(size-- > 0) {
            cur = q.removeFirst()
            if (cur.left != null) {
                q.add(cur.left!!)
            }
            if (cur.right != null) {
                q.add(cur.right!!)
            }
            max = max(max, cur.`val`)
        }
        ans.add(max)
    }
    return ans
}

private lateinit var ans : MutableList<Int>
fun largestValuesII(root: TreeNode?) : List<Int> {
    fun _dfs(cur: TreeNode?, level: Int) {
        if (cur == null) return
        if (level == ans.size) {
            ans.add(cur.`val`)
        } else {
            ans[level] = max(ans[level], cur.`val`)
        }
        _dfs(cur.left, level +1)
        _dfs(cur.right, level + 1)

    }
    if (root == null) return emptyList()
    ans = mutableListOf()
    _dfs(root, 0)
    return ans

}


fun main() {
    val root = TreeNode(1).apply {
        this.left = TreeNode(3).apply {
            this.left = TreeNode(5)
            this.right = TreeNode(3)
        }
        this.right = TreeNode(2).apply {
            this.right = TreeNode(9)
        }
    }
    val ans = largestValuesII(root)
    println(ans.toString())
}
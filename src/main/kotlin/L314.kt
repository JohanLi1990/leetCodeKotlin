import kotlin.math.max
import kotlin.math.min

class L314 {
    fun verticalOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return mutableListOf()
        val map = hashMapOf<Int, MutableList<Int>>()

        // use bfs
        val q = ArrayDeque<TreeNode>()
        val qcol = ArrayDeque<Int>()
        var left = 0
        var right = 0
        q.addFirst(root)
        qcol.addFirst(0)

        while(!q.isEmpty()) {
            var size = q.size
            while (size > 0) {
                size--
                val curNode = q.removeLast()
                val curCol = qcol.removeLast()
                map.putIfAbsent(curCol, mutableListOf())
                map[curCol]!!.add(curNode.`val`)
                left = min(left, curCol)
                right = max(right, curCol)
                if (curNode.left != null) {
                    q.addFirst(curNode.left!!)
                    qcol.addFirst(curCol - 1)
                }

                if (curNode.right != null) {
                    q.addFirst(curNode.right!!)
                    qcol.addFirst(curCol + 1)
                }
            }
        }

        val res = mutableListOf<List<Int>>()
        for (i in left..right) {
            res.add(map[i]!!)
        }
        return res

    }
}

/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */

class TreeNode(var `val` : Int) {
    var left : TreeNode? = null
    var right: TreeNode? = null
}
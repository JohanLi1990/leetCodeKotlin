package daily

import util.TreeNode
import java.util.LinkedList
import kotlin.math.pow


class L655 {
    fun printTree(root: TreeNode?): List<List<String>> {
        if (root == null) return ArrayList()
        val height = determineHeight(root)

        val res = ArrayList<ArrayList<String>>().apply {
            for (i in 0..height) {
                add(ArrayList<String>().apply {
                    val cols = 2.0.pow(height.toDouble() + 1.0).toInt() - 1
                    for (j in 0..<cols) {
                        add("")
                    }
                })
            }
        }

        val initRo = 0
        val initCol = (res[0].size - 1) / 2

        dfs(root, initRo, initCol, res);
        return res
    }

    private fun dfs(root: TreeNode?, ro: Int, col: Int, res: ArrayList<ArrayList<String>>) {
        if (root == null) return

        res[ro][col] = root.`val`.toString()
        val height = res.size - 1
        dfs(root.left, ro + 1, col - 2.0.pow((height - ro - 1).toDouble()).toInt(), res)
        dfs(root.right, ro + 1, col + 2.0.pow((height - ro - 1).toDouble()).toInt(), res)
    }

    private fun determineHeight(root: TreeNode): Int {
        var res = 0
        val q = LinkedList<TreeNode>().apply { offer(root) }

        while (q.isNotEmpty()) {
            var size = q.size
            while (size-- > 0) {
                val cur = q.poll()
                cur.left?.let {
                    q.offer(cur.left)
                }
                cur.right?.let {
                    q.offer(cur.right)
                }
            }
            res++
        }
        return res - 1
    }
}




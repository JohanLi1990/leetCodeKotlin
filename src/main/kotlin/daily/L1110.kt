package daily

import util.TreeNode



class L1110 {

    private val forests = mutableListOf<TreeNode>()
    fun delNodes(root: TreeNode?, to_delete: IntArray): List<TreeNode?> {
        val toDelete = hashSetOf<Int>().apply {
            to_delete.forEach {
                add(it)
            }
        }

        if (dfs(root, toDelete)) {
            forests.add(root!!)
        }

        return forests
    }

    private fun dfs(cur:TreeNode?, toDelete: Set<Int>) : Boolean{
        if (cur == null) return true

        val left = dfs(cur.left, toDelete)
        val right = dfs(cur.right, toDelete)

        if (cur.`val` in toDelete) {
            if (left && cur.left != null) {
                forests.add(cur.left!!)
            }

            if (right && cur.right != null) {
                forests.add(cur.right!!)
            }
            return false
        }
        if (!left) {
            cur.left = null
        }

        if (!right) {
            cur.right = null
        }
        return true


    }

}
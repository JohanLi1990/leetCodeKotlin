package daily

import util.TreeNode


var res:TreeNode? = null
fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (root == null || p == null || q == null) return null

    dfs(root, p.`val`, q.`val`)
    return res
}

fun dfs(cur: TreeNode?, pval: Int, qval: Int): Boolean {
    if (cur == null) return false

    if (cur.`val` == pval ){
        if (dfs(cur.left, pval, qval) || dfs(cur.right, pval, qval)) {
            res = cur
        }
        return true
    }

    if (cur.`val` == qval) {
        if (dfs(cur.left, pval, qval) || dfs(cur.right, pval, qval)) {
            res = cur
        }
        return true
    }

    val leftFound = dfs(cur.left, pval, qval)
    val rightFound = dfs(cur.right, pval, qval)
    if (res == null && leftFound && rightFound) {
        res = cur
        return true
    }
    return leftFound || rightFound


}

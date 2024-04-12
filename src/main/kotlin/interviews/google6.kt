package interviews

import com.sun.source.tree.Tree
import util.TreeNode
import kotlin.math.max

var cursum = 0
fun deepestLeavesSum(root: TreeNode?): Int {
    val height = findHeight(root, 0)
    findSum(root, height, 0)
    return cursum
}

fun findSum(cur: TreeNode?, height: Int, level:Int) {
    if (cur == null) return
    findSum(cur.left, height, level + 1)
    findSum(cur.right, height, level + 1)

    if (level == height) cursum += cur.`val`
}

fun findHeight(cur:TreeNode?, level:Int) : Int {
    if (cur == null) return -1
    val left = findHeight(cur.left, level + 1)
    val right = findHeight(cur.right, level + 1)
    if (left == -1 && right == -1) return level

    return max(left, right)

}
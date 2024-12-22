package daily

import util.TreeNode

//https://leetcode.com/problems/reverse-odd-levels-of-binary-tree/

fun reverseOddLevels(root: TreeNode?): TreeNode? {

    fun reverseValues(list : List<TreeNode>) {
        for (i in 0..<list.size / 2) {
            val left = list[i]
            val right = list[list.size - 1 - i]
            left.`val` += right.`val`
            right.`val` = left.`val` - right.`val`
            left.`val` -= right.`val`
        }
    }


    // it is a perfect binary tree
    if (root == null) return null
    val queue = ArrayDeque<TreeNode>()

    var curNode = root
    queue.add(curNode)
    var level = 0
    val curList = mutableListOf<TreeNode>()
    while(!queue.isEmpty()) {
        var size = queue.size
        curList.clear()
        while(size-- > 0) {
            curNode = queue.removeFirst()
            if (curNode.left !== null) {
                queue.add(curNode.left!!)
            }

            if (curNode.right != null) {
                queue.add(curNode.right!!)
            }
            curList.add(curNode)
        }
        if (level % 2 == 1) {
            reverseValues(curList)
        }
        level++
    }
    return root
}

fun reverseOddLevelsRecurseve(root:TreeNode?) : TreeNode? {
    fun rec(a:TreeNode?, b:TreeNode?, level:Int) {
        if (a == null || b == null) return
        if (level % 2 == 1) {
            // swap
            a.`val` = b.`val`.also { b.`val` = a.`val` }
        }
        rec(a.left, b.right,level + 1)
        rec(a.right, b.left, level + 1)
    }
    if (root == null) return null
    rec(root.left, root.right, 1)
    return root
}

fun main() {
    val tree = TreeNode(2).apply {
        this.left = TreeNode(3).apply {
            this.left = TreeNode(8)
            this.right = TreeNode(13)
        }
        this.right = TreeNode(5).apply {
            this.left = TreeNode(21)
            this.right = TreeNode(34)
        }
    }
    println(tree)
    println(reverseOddLevelsRecurseve(tree))


}
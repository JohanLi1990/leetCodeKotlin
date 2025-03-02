package daily

import util.TreeNode

fun constructFromPrePost(preorder: IntArray, postorder: IntArray): TreeNode? {
    fun _dfs(preStart: Int, preEnd: Int, postStart: Int, postEnd: Int): TreeNode {
        if (preStart >= preEnd) return TreeNode(preorder[preStart])
        val cur = TreeNode(preorder[preStart])
        val left = preorder[preStart + 1]
        val right = postorder[postEnd - 1]

        if (left == right) {
            cur.left = _dfs(preStart + 1, preEnd, postStart, postEnd - 1)
            return cur
        }
        var preNext = preStart
        for (i in preStart..preEnd) {
            if (preorder[i] == right) {
                break
            }
            preNext = i
        }

        var postNext = postEnd
        for (i in postEnd downTo postStart) {
            if (postorder[i] == left) {
                break
            }
            postNext = i
        }

        cur.left = _dfs(preStart + 1, preNext, postStart, postNext - 1)
        cur.right = _dfs(preNext + 1, preEnd, postNext, postEnd - 1)
        return cur
    }

    return _dfs(0, preorder.size - 1, 0, postorder.size - 1)
}

fun main() {
//    println(constructFromPrePost(intArrayOf(1,2,4,5,3,6,7), intArrayOf(4,5,2,6,7,3,1)))
//    println(constructFromPrePost(intArrayOf(1), intArrayOf(1)))
    println(constructFromPrePost(intArrayOf(2, 1), intArrayOf(1, 2)))
}
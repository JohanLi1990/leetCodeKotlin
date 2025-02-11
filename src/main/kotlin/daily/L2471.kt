package daily

import util.TreeNode

// https://leetcode.com/problems/minimum-number-of-operations-to-sort-a-binary-tree-by-level/
fun minimumOperations(root: TreeNode?): Int {
    fun _minSwaps(arrs: MutableList<Int>) : Int {
        val sort = mutableListOf<IntArray>()
        for ((i, v) in arrs.withIndex()) {
            sort.add(intArrayOf(v, i))
        }
        sort.sortWith { a, b -> a[0] - b[0] }
        val visited = BooleanArray(sort.size)
        var ans = 0
        for ((i, arr) in sort.withIndex()) {

            if (visited[i] || arr[1] == i) continue
            var cur = arr
            var index = i
            var groupSize = 0
            while( !visited[index] && cur[1] != index) {
                visited[index] = true
                index = cur[1]
                cur = sort[index]
                groupSize++
            }
            ans += groupSize - 1
        }
        return ans
    }

    // travel by level for each level do sorting
    if (root == null) return 0
    val q = ArrayDeque<TreeNode>()
    q.add(root)
    var ans = 0
    while(!q.isEmpty()) {
        var size = q.size
        val curArr = mutableListOf<Int>()
        while(size-- > 0) {
            val curNode = q.removeFirst()
            if (curNode.left != null) {
                q.add(curNode.left!!)
            }

            if (curNode.right != null) {
                q.add(curNode.right!!)
            }
            curArr.add(curNode.`val`)
        }

        ans += _minSwaps(curArr)
    }
    return ans

}

fun main() {
    val tree = TreeNode(1).apply {
        this.left = TreeNode(4).apply {
            this.left = TreeNode(7)
            this.right = TreeNode(6)
        }
        this.right = TreeNode(3).apply {
            this.left = TreeNode(8).apply {
                this.left = TreeNode(9)
            }
            this.right = TreeNode(5).apply {
                this.left = TreeNode(10)
            }
        }
    }
    val ans = minimumOperations(tree)
    println(ans)
}
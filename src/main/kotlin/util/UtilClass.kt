package util

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

    override fun toString(): String {
        return "Node:$`val` [ ${left.toString()} ${right.toString()} }"
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

class Node{
    var pre : Node? = null
    var next : Node? = null
    var key = -1
    var value = -1
    constructor(k : Int, v : Int) {
        key = k
        value = v
    }

    constructor(p : Node, n : Node) {
        pre = p
        next = n
    }
}

class Node1(var `val`: Int) {
    var left: Node1? = null
    var right:Node1? =  null
    var parent: Node1? = null
}
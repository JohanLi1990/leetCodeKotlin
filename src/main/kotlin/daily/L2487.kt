package daily

import util.ListNode

fun removeNodes(head: ListNode?): ListNode? {

    val stack = ArrayDeque<ListNode>()

    val pre = ListNode(1e6.toInt() + 1)
    pre.next = head
    stack.addFirst(pre)

    var ptr = head
    while(ptr != null) {
        while(stack.isNotEmpty() && ptr.`val` > stack.first().`val`) {
            stack.removeFirst()
            stack.first().next = ptr
        }
        stack.addFirst(ptr)
        ptr = ptr.next
    }
    return pre.next
}

fun main() {
    val input = ListNode(5).also {
        it.next = ListNode(2).also {
            it.next = ListNode(13).also {
                it.next = ListNode(3).also {
                    it.next = ListNode(8)
                }
            }
        }
    }

    val ret = removeNodes(input)
}

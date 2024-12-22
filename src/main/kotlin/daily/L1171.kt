package daily

import util.ListNode

class L1171 {
    fun removeZeroSumSublists(head: ListNode?): ListNode? {
        val headpoint = ListNode(-1).apply {
            next = head
        }
        // [1, 2, -3, 3, -2]

        // at every node need to consider all elements up to the last elements
        do {
            var map = mutableMapOf<Int, ArrayDeque<ListNode>>().apply {
                put(0, ArrayDeque<ListNode>().apply {
                    add(headpoint)
                })
            }
            var sum = 0
            var cur = headpoint.next

            while (cur != null) {
                sum += cur.`val`
                if (map.containsKey(sum)) {
                    val firstSeq = map[sum]!!.removeFirst()
                    firstSeq.next = cur.next
                    break
                } else {
                    map[sum] = ArrayDeque<ListNode>().apply {
                        add(cur!!)
                    }
                }
                cur = cur.next
            }

            if (cur == null) break
        } while (true)


        return headpoint.next
    }
}


fun main() {
    val head = ListNode(1)
    head.next = ListNode(2)
    head.next!!.next = ListNode(-3)
    head.next!!.next!!.next = ListNode(3)
    head.next!!.next!!.next!!.next = ListNode(1)
    val soln = L1171()
    var ans = soln.removeZeroSumSublists(head)
    while (ans != null) {
        println(ans.`val`)
        ans = ans.next
    }
}



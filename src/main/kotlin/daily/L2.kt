package daily

import util.ListNode

/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */

fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
   // number stored in reverse order
    var carry = 0

    var l1ptr = l1
    var l2ptr = l2

    val res = ListNode(-1)
    var ptr = res
    while (l1ptr != null && l2ptr != null) {
        var cursum = l1ptr.`val` + l2ptr.`val` + carry
        carry = cursum / 10
        cursum %= 10
        ptr.next = ListNode(cursum)
        ptr = ptr.next!!
        l1ptr = l1ptr.next
        l2ptr = l2ptr.next
    }

    while (l1ptr != null) {
        var cursum = l1ptr.`val` + carry
        carry = cursum / 10
        cursum %= 10
        ptr.next = ListNode(cursum)
        ptr = ptr.next!!
        l1ptr = l1ptr.next
    }

    while(l2ptr != null) {
        var cursum = l2ptr.`val` + carry
        carry = cursum / 10
        cursum %= 10
        ptr.next = ListNode(cursum)
        ptr = ptr.next!!
        l2ptr = l2ptr.next
    }
    if (carry > 0) {
        ptr.next = ListNode(carry)
    }
    return res.next
}

fun main() {
    val l1 = ListNode(9).apply {
        next = ListNode(9).apply {
            next = ListNode(9)
        }
    }

    val l2 = ListNode(9).apply {
        next = ListNode(9).apply {
            next = ListNode(9).apply {
                next = ListNode(9)
            }
        }
    }

    var res = addTwoNumbers(l1, l2)
    while(res != null) {
        println(res.`val`)
        res = res.next
    }

}



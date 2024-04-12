package daily

import util.Node

class L146 {
}

class LRUCache(capacity: Int) {
    private var map = HashMap<Int, Node>()
    var cap = capacity
    private var head = Node(-1, -1)
    private var tail = Node(-1, -1)

    init {
        head.next = tail
        tail.pre = head
    }


    fun get(key: Int): Int {
        if (!map.containsKey(key)) {
            return -1
        }
        // take out the node
        var cur = map[key]!!
        takeoutNode(cur)
        // insert it in the front
        insertFront(cur)

        return cur.value
    }

    private fun takeoutNode(cur: Node) {
        cur.pre!!.next = cur.next
        cur.next!!.pre = cur.pre
    }

    fun put(key: Int, value: Int) {
        if (map.containsKey(key)) {
            map[key]!!.value = value
            takeoutNode(map[key]!!)
            insertFront(map[key]!!)
            return
        }

        // insert new value first
        val cur = Node(key, value)
        map[key] = cur

        insertFront(cur)

        if (map.size > cap) {
            var curLast = tail.pre!!
            curLast.pre!!.next = tail
            tail.pre = curLast.pre
            map.remove(curLast.key)
        }
    }

    private fun insertFront(cur: Node) {
        cur.next = head.next
        head.next = cur
        cur.pre = head
        cur.next!!.pre = cur
    }

}


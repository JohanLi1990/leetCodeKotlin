package daily

import java.util.*

class L715 {
}

class RangeModule() {
    val map = TreeMap<Int, Int>()

    fun addRange(left: Int, right: Int) {
        val l = map.floorEntry(left)
        val r = map.floorEntry(right)

        var realLeft = left
        var realright = right
        if (l != null && l.value >= left) {
            realLeft = l.key
        }

        if (r != null && r.value >= right) {
            realright = r.value
        }

        map.subMap(realLeft, realright).clear()
        map[realLeft] = realright
    }

    fun queryRange(left: Int, right: Int): Boolean {
        val cur = map.floorEntry(left)
        return cur != null && cur.value >= right
    }

    fun removeRange(left: Int, right: Int) {
        val L = map.floorEntry(left)
        val R = map.floorEntry(right)

        if (L != null && L.value > left) {
            map[L.key] = left
        }

        if (R != null && R.value > right) {
            map[right] =  R.value
        }

        map.subMap(left, right).clear()
    }

}

/**
 * Your RangeModule object will be instantiated and called as such:
 * var obj = RangeModule()
 * obj.addRange(left,right)
 * var param_2 = obj.queryRange(left,right)
 * obj.removeRange(left,right)
 */
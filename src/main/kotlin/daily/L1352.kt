package daily

import kotlin.math.max


class ProductOfNumbers {
    val map = mutableMapOf<Int, Long>().also {
        it[0] = 1L
    }
    var size = 0
    var latestZeroLoc = -1
    fun add(num: Int) {
        size++
        if (num == 0) {
            map[size] = map[size - 1]!!
            latestZeroLoc = max(latestZeroLoc, size - 1)
        } else {
            map[size] = map[size - 1]!! * num.toLong()
        }

    }

    fun getProduct(k: Int): Int {
        if (size - k <= latestZeroLoc) return 0
        return (map[size]!! / map[size - k]!!).toInt()
    }

}

class ProductOfNumbersII {
    val list = mutableListOf<Int>()
    fun add(num: Int) {
        list.add(num)
    }

    fun getProduct(k: Int): Int {
        var ans = 1
        var curPointer = list.size - 1
        var copy = k
        while(copy-- > 0) {
            if (list[curPointer] == 0) {
                return 0
            }
            ans *= list[curPointer--]
        }
        return ans
    }

}

package daily

import kotlin.math.max
import kotlin.math.min

class L56 {
    fun merge(arr: Array<IntArray>): Array<IntArray> {
        arr.sortWith{ a, b ->
          if (a[0] == b[0]) {
              a[1] - b[1]
          } else {
              a[0] - b[0]
          }
        }

        var i = 0
        var j = 1
        val list = mutableListOf<IntArray>()
        while(j < arr.size) {
            val a = arr[i]
            val b = arr[j]
            if (a[0] <= b[1] && b[0] <= a[1]) {
                a[0] = min(a[0], b[0])
                a[1] = max(a[1], b[1])
                j++
            } else {
                list.add(a)
                i = j
                j++
            }
        }
        list.add(arr[i])
        return list.toTypedArray()
    }
}

fun main() {
    val array2D = arrayOf(
        intArrayOf(1, 3),
        intArrayOf(2, 6),
        intArrayOf(8, 10),
        intArrayOf(15, 18)
    )

    val input2 = arrayOf(
        intArrayOf(2,3), intArrayOf(4,5),
        intArrayOf(6,7), intArrayOf(8,9),
        intArrayOf(1,10)
    )
    val ans = L56()
    println(ans.merge(array2D).contentDeepToString())
    println(ans.merge(input2).contentDeepToString())
}
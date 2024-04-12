package daily

class L1356 {

    fun sortByBits(arr: IntArray): IntArray {
        val res = arr.sortedWith { a, b ->
            val a1 = a.countOneBits()
            val b1 = b.countOneBits()
            if (a1 != b1) {
               a1 - b1
            } else {
                a - b
            }

        }
        return res.toIntArray()
    }
}

fun main() {
    val arr = intArrayOf(1024,512,256,128,64,32,16,8,4,2,1)
    val ans = L1356()
    println(ans.sortByBits(arr).contentToString())
}
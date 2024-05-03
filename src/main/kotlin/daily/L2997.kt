package daily

import kotlin.math.max

class L2997 {
    fun minOperations(nums: IntArray, k: Int): Int {
        val bitMap = HashMap<Int, Int>()
        var maxBit = 1
        for (num in nums) {

            var cur = 1
            var bitPos = 1
            while (cur <= num) {
                if (num and cur == cur) {
                    bitMap[bitPos] = bitMap.getOrDefault(bitPos, 0) + 1
                }
                cur = cur shl 1
                bitPos++
            }
            maxBit = max(maxBit, bitPos - 1)
        }

        var kBit = 1
        var temp = k
        while (temp > 0) {
            temp = temp shr 1
            kBit++
        }
        kBit--

        var ops = 0
        for (i in 1..max(maxBit, kBit)) {
            if (!bitMap.containsKey(i)) {
                bitMap[i] = 0
            }

            val curBit = k shr (i - 1)
            if (curBit and 1 == 1 && bitMap[i]!! % 2 == 0) {
                ops++
            } else if (curBit and 1 == 0 && bitMap[i]!! % 2 == 1) {
                ops++
            }

        }
        return ops
    }

}

fun main() {
    val ans = L2997()
    println(
        ans.minOperations(
            intArrayOf(3, 5, 1, 1), 19
        )
    ) // 2 expected 3
}
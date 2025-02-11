package daily

import javax.swing.text.html.HTML.Attribute.N
import kotlin.math.max


fun minimizeXor(num1: Int, num2: Int): Int {

    fun _padBits(bitsLeft:Int, num:Int) :Int {
        var ans = num
        var shift = 0
        var bits = bitsLeft
        while(bits > 0) {
            if (num.shr(shift) and 1 == 0) {
                ans += (1 shl shift)
                bits--
            }
            shift++
        }
        return ans

    }

    fun _padLeft(bitB: Int, num1: Int): Int {
        var pos = 0
        var n = num1
        while( n != 0) {
            n = n shr 1
            pos++
        }
        var bits = bitB
        pos--
        var ans = 0
        while(bits > 0) {
            // starting from MSB
            val curBit = num1 and (1 shl pos)
            if (curBit > 0) {
                ans += (1 shl pos)
                bits--
            }
            pos--
        }

        return ans
    }



    val bitA = num1.countOneBits()
    val bitB = num2.countOneBits()
    if (bitB > bitA) {
        return _padBits(bitB - bitA, num1)
    } else if (bitB == bitA) {
        return num1
    } else {
        return _padLeft(bitB, num1)
    }

}



fun main() {
    println(minimizeXor(3, 5)) // 3
    println(minimizeXor(1, 12)) // 3
}
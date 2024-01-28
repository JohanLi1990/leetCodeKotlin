import kotlin.math.max

class L11 {
    // Container with most Water
    fun maxArea(height: IntArray): Int {

        var l = 0
        var r = height.size - 1
        var res = Int.MIN_VALUE
        while(l < r) {
            if (height[l] < height[r]) {
                res = max(res, height[l] *  (r - l))
                l++
            } else {
                res = max(res, height[r] * (r - l))
                r--
            }
        }
        return res
    }
}

fun main() {
    val input = intArrayOf(1,8,6,2,5,4,8,3,7)
    val input1 = intArrayOf(1,1)
    val ans = L11()
    println(ans.maxArea(input1));
}
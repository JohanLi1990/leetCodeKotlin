package daily


/**
 * https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/
 */
fun finalPrices(prices: IntArray): IntArray {
    val stack = ArrayDeque<Int>()
    val ans = prices.copyOf()
    for ((i, v) in prices.withIndex()) {
        while (!stack.isEmpty() && v <= prices[stack.last()]) {
            val idx = stack.removeLast()
            ans[idx] = prices[idx] - v
        }
        stack.addLast(i)
    }
    return ans
}

fun main() {
//    val ans = finalPrices(intArrayOf(8, 4, 6, 2, 3))
//    println(ans.contentToString())
//
//    val ans1 = finalPrices(intArrayOf(10, 1, 1, 6))
//    println(ans1.contentToString())

    val ans2 = finalPrices(intArrayOf(8, 7, 4, 2, 8, 1, 7, 7, 10, 1))
    println(ans2.contentToString())
}
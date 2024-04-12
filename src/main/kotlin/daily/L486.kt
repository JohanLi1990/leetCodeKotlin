package daily

import kotlin.math.max

class L486 {

    lateinit var dp : Array<Array<Int>>;
    fun predictTheWinner(nums: IntArray): Boolean {
        dp = Array(nums.size){ Array(nums.size){0} }
        val res =  dfs(nums, 0, nums.size - 1)
        return res >= 0;
    }

    private fun dfs(nums: IntArray, i: Int, j: Int): Int {
        if (i == j) return nums[i];

        if (dp[i][j] != 0) {
            return dp[i][j]
        }

        val left = nums[i] - dfs(nums, i + 1, j)
        val right = nums[j] - dfs(nums, i, j - 1);
        dp[i][j] = max(left, right)
        return dp[i][j]
    }
}

fun main() {
    val input1 = intArrayOf(1,2,99)
    val ans = L486()

    println(ans.predictTheWinner(input1))
}

//class Solution {
//    private lateinit var table : Array<IntArray>
//    private fun getWinner(nums: IntArray, start: Int, end: Int): Int {
//        if(start == end) return nums[start]
//        if (table[start][end] != -1) return table[start][end]
//        val left = nums[start] - getWinner(nums, start+1, end)
//        val right = nums[end] - getWinner(nums, start, end-1)
//        table[start][end] = Math.max(left, right)
//        return table[start][end]
//    }
//    fun predictTheWinner(nums: IntArray): Boolean {
//        table = Array(nums.size+1) { IntArray(nums.size+1) { -1 } }
//        return getWinner(nums,0,nums.size-1) >=0
//    }
//}
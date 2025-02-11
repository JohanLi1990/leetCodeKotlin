package daily

import interviews.cursum

//https://leetcode.com/problems/target-sum/

/**
 * use 2d arraySize
 * dp[index][curSum] = dp[index - 1][cursum - nums[index]] + dp[index - 1][cursum + nums[index]]
 * since only depends on previous row, we only have to store 1d array at a time
 */

private lateinit var dpArray: Array<IntArray>
fun findTargetSumWays(nums: IntArray, target: Int): Int {
    val sum = nums.sum()
    dpArray = Array(nums.size + 1){ IntArray(sum * 2 + 1){-1} }

    fun _dfs(nums: IntArray, curIndex: Int, target: Int, cursum : Int) : Int {
        if (curIndex >= nums.size ) {
            return if (target == 0) 1 else 0
        }
        if (target > cursum || target < -cursum) return 0
        if (dpArray[curIndex][target + sum] != -1) {
            return dpArray[curIndex][target + sum]
        }
        if (nums[curIndex] == 0) {
            return 2 * _dfs(nums, curIndex + 1, target, cursum)
        }
        val minus = _dfs(nums, curIndex + 1, target + nums[curIndex], sum - nums[curIndex])
        val plus = _dfs(nums, curIndex + 1, target - nums[curIndex], sum - nums[curIndex])
        dpArray[curIndex][target + sum] = minus + plus
        return dpArray[curIndex][target + sum]
    }
    return _dfs(nums, 0, target, sum)
}

fun main() {
    val sum = findTargetSumWays(intArrayOf(1,1,1,1,1), 3)
    println(sum)
    dpArray.forEach {
        println(it.contentToString())
    }

    val sum1 = findTargetSumWays(intArrayOf(1), 1)
    println(sum1)
    dpArray.forEach {
        println(it.contentToString())
    }

    val sum2 = findTargetSumWays(intArrayOf(1, 0), 1)
    println(sum2)
    dpArray.forEach {
        println(it.contentToString())
    }
}
package daily


//
// dp[n][m] : maximum sum of n subarray chosen from 0..to m
// dp[n][m] = max(dp[n - 1][m - k] + sum[m - k..m], dp[n][m - 1])
//
private lateinit var dp: Array<Array<SumAndIndex>>
private lateinit var winSum: IntArray
fun maxSumOfThreeSubarrays(nums: IntArray, k: Int): IntArray {
    dp = Array(4) { Array(nums.size + 1) { SumAndIndex(0) } }
    winSum = IntArray(nums.size)
    var i = 0
    var j = 0
    var curSum = 0
    while (j < nums.size) {
        curSum += nums[j]
        if (j - i + 1 == k) {
            winSum[j] = curSum
            curSum -= nums[i++]
        }
        j++
    }

//    println(winSum.contentToString())
//    dp[1][k - 1].curSum = winSum[k - 1].toLong()
//    dp[1][k - 1].list.add(0)

    for (numChosen in 1..3) {
        val leftMin = numChosen * k
        for (len in leftMin..nums.size) {
            val cur = dp[numChosen - 1][len - k].curSum + winSum[len - 1].toLong()
            if (cur > dp[numChosen][len - 1].curSum) {
                dp[numChosen][len].curSum = cur
                dp[numChosen][len].list.apply {
                    addAll(dp[numChosen - 1][len - k].list)
                    add(len - k)
                }
            } else {
                dp[numChosen][len].curSum = dp[numChosen][len - 1].curSum
                dp[numChosen][len].list.addAll(dp[numChosen][len - 1].list)
            }
        }
    }
    return dp[3][nums.size].list.toIntArray()
}

data class SumAndIndex(var curSum: Long = 0L, var list: MutableList<Int> = mutableListOf())

// better approaches
// leftBest[l] + winSum[l::r] + rightBest[r]
fun maxSumOfThreeSubarraysII(nums: IntArray, k: Int): IntArray {
    val winSum =  Array(2){IntArray(nums.size)}
    var l = 0
    var r = 0
    var cur = 0
    while (r < nums.size) {
        cur += nums[r]
        if (r - l + 1 == k) {
            winSum[0][r] = cur
            winSum[1][l] = cur
            cur -= nums[l++]
        }
        r++
    }

    val leftBest = IntArray(nums.size )
    val rightBest = IntArray(nums.size){nums.size - 1}
    leftBest[k - 1] = k - 1
    rightBest[nums.size - k] = nums.size - k
    for (i in k   ..<nums.size) {
        leftBest[i] = if (winSum[0][i] > winSum[0][leftBest[i - 1]]) {
            i
        } else {
            leftBest[i - 1]
        }
    }

    for (i in (nums.size - k - 1) downTo 0) {
        rightBest[i] = if (winSum[1][i] >= winSum[1][rightBest[i + 1]]) {
            i
        } else {
            rightBest[i + 1]
        }
    }
    // now we can do the leftBest[l] + winSum[l + r] + rightBest[r]
    val ans = IntArray(3)
    var max = 0
    for (i in k - 1 ..(nums.size - 2*k - 1 )) {
        val curMax = winSum[0][leftBest[i]] + winSum[0][i + k] + winSum[1][rightBest[i + k + 1]]
        if (curMax > max) {
            max = curMax
            ans[0] = leftBest[i] - k + 1
            ans[1] = i + 1
            ans[2] = rightBest[i + k + 1]
        }
    }
    return ans

}


fun main() {
    var ans = IntArray(0)
    ans = maxSumOfThreeSubarraysII(intArrayOf(1, 2, 1, 2, 6, 7, 5, 1), 2)
    println(ans.contentToString()) // 0, 3, 5

    ans = maxSumOfThreeSubarraysII(intArrayOf(1, 2, 1, 2, 1, 2, 1, 2, 1), 2)
    println(ans.contentToString()) // 0, 2, 4

    ans = maxSumOfThreeSubarraysII(intArrayOf(4, 3, 2, 1), 1)
    println(ans.contentToString()) // 0, 1, 2

    ans = maxSumOfThreeSubarraysII(intArrayOf(4,5,10,6,11,17,4,11,1,3), 1)
    println(ans.contentToString()) // 4, 5, 7
}
package daily

class L1671 {

    fun minimumMountainRemovals(nums: IntArray): Int {
        // find longest increasing subsequence from left
        // find longest increasing subsequence from right at each position
        val dpLeft = IntArray(nums.size)
        val dpRight = IntArray(nums.size)
        findLIS(nums, nums.indices) {
            i, list -> dpLeft[i] = list.size
        }

        findLIS(nums, (nums.size - 1) downTo 0) {
            i, list -> dpRight[i] = list.size
        }
        println(dpLeft.contentToString())
        println(dpRight.contentToString())
        var max = 0
        for (i in 1..<nums.size - 1) {
            if (dpLeft[i] == 1 || dpRight[i] == 1) continue
            max = Math.max(max, dpLeft[i] + dpRight[i] - 1)
        }
        return nums.size - max

    }

    fun findLIS(nums: IntArray, range: IntProgression, dpfunc: (Int, ArrayList<Int>) -> Unit) {
        val lis = ArrayList<Int>()
        for (i in range) {
            val pos = - lis.binarySearch(nums[i]) - 1
            if (pos >= 0) {
                if (pos == lis.size) {
                    lis.add(nums[i])
                } else {
                    lis[pos] = nums[i]
                }
            }

            dpfunc(i, lis)
        }
//        println(lis.toString())
    }

}



fun main() {

    val ans = L1671()
//    println(ans.minimumMountainRemovals(intArrayOf(2,1,1,5,6,2,3,1)))
//    println(ans.minimumMountainRemovals(intArrayOf(1,2,3,4,4,3,2,1)))
    println(ans.minimumMountainRemovals(intArrayOf(100,92,89,77,74,66,64,66,64)))
    println(ans.minimumMountainRemovals(intArrayOf(100,92,89,77,74,66,64,66,64).reversedArray()))
}
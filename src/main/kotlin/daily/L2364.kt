package daily

fun countBadPairs(nums: IntArray): Long {
    val map = mutableMapOf<Int, Long>()
    var ans = 0L
    for (i in nums.indices) {
        if (!map.containsKey(nums[i] - i)) {
            ans += i.toLong()
        } else {
            ans += i.toLong() - map[nums[i] - i]!!
        }

        map[nums[i] - i] = map.getOrDefault(nums[i] - i, 0) + 1L
    }
    return ans
}

fun main() {
    println(countBadPairs(intArrayOf(4,1,3,3)))
    println(countBadPairs(intArrayOf(1,2,3,4,5)))
    println(countBadPairs(intArrayOf(1, 2, 2)))
}
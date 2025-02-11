package daily

fun tupleSameProduct(nums: IntArray): Int {
    if (nums.size < 4) return 0
    nums.sort()
    var ans = 0
    for (i in 0..nums.size - 3) {
        for (j in nums.size - 1 downTo i + 3) {
            val curProduct = nums[i] * nums[j]
            var l = i + 1
            var r = j - 1
            while(l < r) {
                val curInner = nums[l] * nums[r]
                if (curInner < curProduct) {
                    l++
                } else if (curInner > curProduct) {
                    r--
                } else {
                    ans += 8
                    l++
                    r--
                }
            }
        }
    }
    return ans
}

fun main() {
    println(tupleSameProduct(intArrayOf(2,3,4,6))) // 8
    println(tupleSameProduct(intArrayOf(1,2,4,5,10))) // 16
}
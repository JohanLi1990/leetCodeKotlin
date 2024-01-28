class L15 {
    fun threeSum(nums: IntArray): List<List<Int>> {
        val res = mutableListOf<List<Int>>()
        nums.sort()
        for (i in 0..<nums.size - 2) {
            if (i > 0 && nums[i] == nums[i - 1]) continue
            var j = i + 1
            var k = nums.size - 1

            while(j < k) {
                if (nums[j] + nums[k] + nums[i] < 0) {
                    j++
                } else if (nums[j] + nums[k] + nums[i] > 0) {
                    k--
                } else {
                    val curList = mutableListOf(nums[i], nums[j], nums[k])
                    res.add(curList)
                    while(j + 1 < nums.size && nums[j + 1] == nums[j]){
                        j++
                    }
                    j++
                }
            }
        }
        return res
    }
}

fun main() {
    val input = intArrayOf(-1,0,1,2,-1,-4)
    val input1 = intArrayOf(0,1,1)
    val input2 = intArrayOf(0,0,0,0)
    val ans = L15()
    ans.threeSum(input2).forEach(){
        k -> println(k.joinToString(","))
    }
}
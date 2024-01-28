class L287 {

    fun findDuplicate(nums: IntArray): Int {
        // cannot modify nums,
        // Tortoise and Hare

        var slow = 0
        var fast = 0

        do {
            slow = nums[slow]
            fast = nums[nums[fast]]
        } while(slow != fast)

        slow = 0;
        while(nums[slow] != nums[fast]) {
            slow = nums[slow]
            fast = nums[fast]
        }
        return nums[slow]
    }
}

fun main() {
    val input = intArrayOf(2,5,9,6,9,3,8,9,7,1)
    val input1 = intArrayOf(1,3,4,2,2)
    val input2 = intArrayOf(3,3,4,1,2)
    val ans = L287()
    println(ans.findDuplicate(input))
    println(ans.findDuplicate(input1))
    println(ans.findDuplicate(input2))
}
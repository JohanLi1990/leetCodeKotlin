package daily

fun removeDuplicates(nums: IntArray): Int {

    val list = mutableListOf<Int>()
    val map = HashSet<Int>()

    for (num in nums) {
        if (!map.contains(num)) {
            list.add(num)
            map.add(num)
        }
    }

    for (i in 0..<list.size) {
        nums[i] = list[i]
    }

    return list.size

}

fun main() {
    println(removeDuplicates(intArrayOf(1,1,2)))
}
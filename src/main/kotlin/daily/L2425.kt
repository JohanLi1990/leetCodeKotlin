package daily

//https://leetcode.com/problems/bitwise-xor-of-all-pairings


fun xorAllNums(nums1: IntArray, nums2: IntArray): Int {

    if (nums1.size % 2 == 0 && nums2.size % 2 == 0) {
        return 0
    } else if (nums1.size % 2 == 1 && nums2.size % 2 == 1) {
        return  nums1.reduce { acc, i ->
            acc xor i
        } xor nums2.reduce { acc, i ->
            acc xor i
        }
    } else if (nums1.size % 2 == 1) {
        return nums2.reduce { acc, i ->
            acc xor i
        }
    }

    return nums1.reduce { acc, i ->
        acc xor i
    }
}

fun main() {
    println(xorAllNums(intArrayOf(2,1,3), intArrayOf(10,2,5,0))) // 13
    println(xorAllNums(intArrayOf(1,2), intArrayOf(3,4))) // 0
}
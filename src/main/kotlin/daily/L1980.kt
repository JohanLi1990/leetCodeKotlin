package daily

fun findDifferentBinaryString(nums: Array<String>): String {
    nums.sort()
//    println(nums.contentToString())
    var cur = 0
    for (num in nums) {
        val str = cur.toString(2).padStart(nums.size, '0')
//        println(str)
        if (!str.equals(num)) {
            return str
        }
        cur += 1
    }
    return cur.toString(2).padStart(nums.size, '0')
}

fun main() {
    println(findDifferentBinaryString(arrayOf("01","10")))
    println(findDifferentBinaryString(arrayOf("00","01")))
    println(findDifferentBinaryString(arrayOf("111","011","001")))
    println(findDifferentBinaryString(arrayOf("000","001","110")))
}
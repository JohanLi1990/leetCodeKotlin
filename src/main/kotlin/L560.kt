class L560 {

    fun subarraySum(nums: IntArray, k: Int): Int {
        val map = hashMapOf<Int, Int>()
        map[0] = 1
        var cur = 0
        var res = 0
        for (num in nums) {
            cur += num
            if (map.containsKey(cur - k)) {
                res += map[cur - k]!!
            }
            map[cur] = map.getOrDefault(cur, 0) + 1
        }
        return res
    }
}

fun main() {
    val input = intArrayOf(1,1,1)
    val ans = L560()
    print(ans.subarraySum(input, 2))

}
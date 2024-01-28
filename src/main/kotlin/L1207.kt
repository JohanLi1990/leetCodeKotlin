class L1207 {
    fun uniqueOccurrences(arr: IntArray): Boolean {
        val map = hashMapOf<Int, Int>()
        for (num in arr) {
            map[num] = map.getOrDefault(num, 0) + 1
        }
        val set = hashSetOf<Int>()
        for ((_, v) in map) {
            if (set.contains(v)) {
                return false
            }
            set.add(v)
        }
        return true
    }
}

fun main() {
    val input = intArrayOf(1,2,2,1,1,3)
    val ans = L1207()
    println(ans.uniqueOccurrences(input))
}
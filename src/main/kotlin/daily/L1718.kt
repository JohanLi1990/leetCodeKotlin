package daily

fun constructDistancedSequence(n: Int): IntArray {
    val ans = IntArray(2 * n - 1) { 0 }
    val avail = BooleanArray(n + 1) { true }

    fun backtracking(idx: Int): Boolean {
        if (idx == ans.size) return true
        if (ans[idx] != 0) return backtracking(idx + 1)

        for (i in n downTo 2) {
            val nextIdx = idx + i
            if (avail[i] && nextIdx < ans.size && ans[nextIdx] == 0) {
                ans[idx] = i
                ans[nextIdx] = i
                avail[i] = false

                if (backtracking(idx + 1)) return true

                ans[idx] = 0
                ans[nextIdx] = 0
                avail[i] = true
            }
        }

        if (avail[1]) {
            ans[idx] = 1
            avail[1] = false

            if (backtracking(idx + 1)) return true

            ans[idx] = 0
            avail[1] = true
        }

        return false
    }

    backtracking(0)
    return ans
}


fun main() {
    println(constructDistancedSequence(3).contentToString()) // [3,1,2,3,2]
    println(constructDistancedSequence(5).contentToString()) // [5,3,1,4,3,5,2,4,2]
}
package daily

fun smallestNumber(pattern: String): String {
    val avail = BooleanArray(10){true}
    fun dfs(cur:MutableList<Int>, idx:Int) : Boolean{
        if (idx == pattern.length) return true
        val char = pattern[idx]
        val start = if (char == 'I') cur[cur.size - 1] + 1 else 1
        val end = if (char == 'D') cur[cur.size - 1] - 1 else 9
        for (i in start..end) {
            if (!avail[i]) continue
            cur.add(i)
            avail[i] = false
            if (dfs(cur, idx + 1)) {
                return true
            }
            avail[i] = true
            cur.removeLast()
        }
        return false
    }
    for (i in 1..9) {
        val cur = mutableListOf(i)
        avail[i] = false
        if (dfs(cur, 0)) {
            return buildString {
                cur.forEach { append(it) }
            }
        }
        avail[i] = true
    }
    return ""
}

fun main() {
    println(smallestNumber("IIIDIDDD")) // 123549876
    println(smallestNumber("DDD")) // 4321
}
package daily

import kotlin.concurrent.timerTask

fun numTilePossibilities(tiles: String): Int {

    val avail = BooleanArray(tiles.length){true}
    // backtracking
    val ans = mutableSetOf<String>()
    fun backtrack(cur:String) {
        if (cur.isNotEmpty()) {
            ans.add(cur)
        }
        if (cur.length >= tiles.length) return
        for (i in tiles.indices) {
            if (!avail[i]) continue
            avail[i] = false
            backtrack(cur + tiles[i])
            avail[i] = true
        }
    }

    for (i in tiles.indices) {
        if (i > 0 && tiles[i] == tiles[i - 1]) continue
        avail[i] = false
        backtrack("" + tiles[i])
        avail[i] = true
    }
    return ans.size

}

fun main() {
    println(numTilePossibilities("AAB")) // 8
    println(numTilePossibilities("AAABBC")) // 188
    println(numTilePossibilities("V")) // 1
}
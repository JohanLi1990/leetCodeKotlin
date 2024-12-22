package daily

class L292 {
    // only need to store the last three digit

    fun canWinNim(n: Int) : Boolean {
//        val map = arrayOf(true, true, true)
//        if (n <= 3) return true
//        for (i in 4..n) {
////            println(map.joinToString(","))
//            val cur = !map[0] || !map[1] || !map[2]
//            map[0] = map[1]
//            map[1] = map[2]
//            map[2] = cur
//        }
//        return map[2]
        if (n < 4) return true
        return (n - 4) % 4 == 0
    }
}

fun main() {
    val ans = L292()
    for (i in 4..14) {
        println("Gordon will win? ${ans.canWinNim(i)} at $i ")
    }
//    println("Gordon will ${ans.canWinNim(8)} ")
}
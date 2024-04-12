package daily

class L1535 {
}

fun getWinner(arr: IntArray, k: Int): Int {

    // if k >= arr.size return max from arr
    if (k >= arr.size) {
        return arr.max()
    }

    val deq = ArrayDeque<Int>()
    deq.addAll(arr.asList())
    var winner = deq.removeFirst()
    var curCount = 0
    do {
        if (winner > deq.first()) {
            deq.addLast(deq.removeFirst())
            curCount++
        } else {
            curCount = 1
            deq.addLast(winner)
            winner = deq.removeFirst()
        }
        if (curCount == k) return winner
    } while (true)
    return -1
}

fun main() {
    val arr = intArrayOf(2,1,3,5,4,6,7)
    println(getWinner(arr, 2))

    println(getWinner(intArrayOf(3,2,1), 10))
}


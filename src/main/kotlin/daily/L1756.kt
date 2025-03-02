package daily

// 1756. Design Most Recently Used Queue

class MRUQueue(n: Int) {

    var left = ArrayDeque<Int>().apply {
        for (i in 1..n){
            add(i)
        }
    }
    var right = ArrayDeque<Int>()
    fun fetch(k: Int): Int {
        var item = -1
        if (k <= left.size) {
            item = left.removeAt(k - 1)
            right.addLast(item)
        } else {
            item = right.removeAt(k - left.size - 1)
            right.addLast(item)
        }
        if (left.isEmpty()) {
            val temp = right
            right = left
            left = temp
        }
        return item
    }
}

fun main() {
    val ans = MRUQueue(8)
    println(ans.fetch(3))
    println(ans.fetch(5))
    println(ans.fetch(2))
    println(ans.fetch(8))
}
package daily
fun removeKdigits(num: String, k: Int): String {
        // we should maintain the order
    if (num == "0" || k == 0) return num
    if (k == num.length) return "0"
    val stack = ArrayDeque<Int>()
    val kept = num.length - k
    for (i in num.indices) {
        val curInt = num[i] - '0'
        while(stack.isNotEmpty() && curInt < stack.first() && (num.length - i + stack.size - 1 >= kept)) {
            stack.removeFirst()
        }

        if (stack.size < kept) {
            stack.addFirst(curInt)
        }

    }

    val res = buildString {
        stack.forEach {
            append(it)
        }
    }.reversed()

    if (res.length <= 1) return res

    var i = 0
    while( i < res.length && res[i] == '0') {
        i++
    }
    return  if (i == res.length) "0" else res.substring(i)
}

fun main() {
    println(removeKdigits("1432219", 3)) // 1219
    println(removeKdigits("10200", 1)) // 200
    println(removeKdigits("112", 1)) //11
}
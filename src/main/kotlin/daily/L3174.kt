package daily

fun clearDigits(s: String): String {
    return buildString {
        val stack = ArrayDeque<Char>()
        for (i in s.indices) {
            if (s[i].isDigit()) {
                if (stack.isNotEmpty()) stack.removeLast()
            } else {
                stack.addLast(s[i])
            }
        }
        while (stack.isNotEmpty()) {
            append(stack.removeFirst())
        }
    }

}

fun clearDigitsII(s: String): String {
    val arr = CharArray(s.length) { ' ' }
    var cursor = 0;

    s.forEach{
        if(it < '0' || it > '9') {
            arr[cursor++] = it;
        } else {
            arr[--cursor] = ' '
        }
    }

    val result = StringBuilder()
    arr.forEach{
        if(it == ' ') return result.toString();
        result.append(it)
    }

    return result.toString()
}

fun main() {
//    println(clearDigits("abc"))
//    println(clearDigits("cb34"))

    println(clearDigitsII("34ko"))

}
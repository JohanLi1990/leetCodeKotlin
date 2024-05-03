package daily

fun reversePrefix(word: String, ch: Char): String {

    val first = word.indexOf(ch)
    if (first == -1) return word

    val sb = StringBuilder()
    sb.append(word.substring(0, first + 1).reversed())
    sb.append(word.substring(first + 1))
    return sb.toString()

}

fun main() {

    println(reversePrefix("abcdefg", 'd'))
    println(reversePrefix("xyxzxe", 'z'))
}
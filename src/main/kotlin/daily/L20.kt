package daily

class L20 {

    fun isValid(s: String): Boolean {
        val stack = ArrayDeque<Char>()
        for (c in s) {
            if (c == '(' || c == '[' || c == '{') {
                stack.addFirst(c);
            } else {
                if (stack.isEmpty()) return false
                val top = stack.first()
                if (!isPair(top, c)) {
                    return false
                }
                stack.removeFirst()
            }
        }
        return stack.isEmpty()
    }

    private fun isPair(top: Char, c: Char): Boolean {
        return (top == '(' && c == ')') || (top == '[' && c == ']')
                || (top == '{' && c == '}')
    }


}

fun main() {
    val input = "[()]"
    val ans = L20()
    print(ans.isValid(input))
}
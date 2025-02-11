package daily

fun doesValidArrayExist(derived: IntArray): Boolean {
    fun _dfs(opt: IntArray) : Boolean{
        // first element is defined
        // if A ^ B = C, B = A ^ C
        for (i in 0..opt.size - 2) {
            opt[i + 1] = opt[i] xor derived[i]
        }
        return opt[0] == (opt[opt.size - 1] xor derived[opt.size - 1])
    }

    val opt1 = IntArray(derived.size){ -1 }.also {
        it[0] = 0
    }
    val opt2 = IntArray(derived.size){ -1 }.also {
        it[0] = 1
    }

    return _dfs(opt1) || _dfs(opt2)


}

fun main() {
    println(doesValidArrayExist(intArrayOf(1, 1, 0))) // true
    println(doesValidArrayExist(intArrayOf(1, 1))) // true
    println(doesValidArrayExist(intArrayOf(1, 0))) // false

}
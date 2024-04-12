package daily


fun validWordAbbreviation(word: String, abbr: String): Boolean {

    // if leading zero return false
    // if adjacent return false
    var i = 0
    var j = 0
    while( i < abbr.length) {
        if (j >= word.length) return false

        val c = abbr[i]
        if (c in 'a'..'z') {
            if (c != word[j]) return false
        } else {
            if (c == '0') return false // leading zero
            var offset = 0
            while(i < abbr.length && abbr[i] in '0'..'9') {
                offset *= 10
                offset += abbr[i] - '0'
                i++
            }
            i--
            j += offset - 1
        }
        i++
        j++
    }
//    println("i = $i : j = $j")
    return j == word.length

}

fun main() {
    println(validWordAbbreviation("internationalization", "i12iz4n"))
    println(validWordAbbreviation("apple", "a2e"))
    println(validWordAbbreviation("internationalization", "i5a11o1"))
    println(validWordAbbreviation("a", "2"))
    println(validWordAbbreviation("a", "01"))

}
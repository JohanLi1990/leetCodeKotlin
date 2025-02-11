package daily


fun stringMatching(words: Array<String>): List<String> {
    class Trie {
        val children = Array<Trie?>(26) { null }
    }

    val head = Trie()

    val sorted = words.sortedWith { a, b ->
        b.length - a.length

    }

    fun _isSubString(w: String): Boolean {
        var cur = head
        for (c in w) {
            if (cur.children[c - 'a'] == null) {
                return false
            }
            cur = cur.children[c - 'a']!!
        }
        return true
    }

    fun _insert(w: String) {
        for (i in w.indices) {
            var cur = head
            for (j in i..<w.length) {
                if (cur.children[w[j] - 'a'] == null) {
                    cur.children[w[j] - 'a'] = Trie()
                }
                cur = cur.children[w[j] - 'a']!!
            }
        }
    }

    val ans = mutableListOf<String>()
    for (word in sorted) {
        if (_isSubString(word)) {
            ans.add(word)
            continue
        }
        // insert
        _insert(word)
    }
    return ans

}


fun main() {
    println(stringMatching(arrayOf("mass", "as", "hero", "superhero"))) // ["as","hero"]
    println(stringMatching(arrayOf("leetcode", "et", "code"))) // ["et","code"]
}
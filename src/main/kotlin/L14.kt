class L14 {
    fun longestCommonPrefix(strs: Array<String>): String {
        val head = Trie()
        for (s in strs) {
            insert(head, s)
        }

        val res = StringBuilder("")
        dfs(head, res, strs.size);
        return res.toString()
    }

    private fun dfs(head: Trie, res: StringBuilder, size:Int) {
        for ((id, child) in head.children.withIndex()) {
            if (child != null && child.num == size) {
                res.append('a' + id)
                return dfs(child, res, size)
            }
        }
    }

    private fun insert(head:Trie, s:String) {
        var cur = head

        for (c in s) {
            if(cur.children[c - 'a'] == null) {
                cur.children[c - 'a'] = Trie()
            }
            cur = cur.children[c - 'a']!!
            cur.num++
        }
    }

}

class Trie {
    val children = Array<Trie?>(26){null}
    var num = 0
}

fun main() {
    val input = arrayOf("flower","flow","see")
    val ans = L14()
    println(ans.longestCommonPrefix(input))
}


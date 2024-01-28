fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val map = hashMapOf<Int, MutableList<String>>()
    for (str in strs) {
        val curKey = IntArray(26)
        for (c in str) {
            curKey[c - 'a']++
        }
        val curHash = curKey.contentHashCode()
        if (!map.containsKey(curHash)) {
            map[curHash] = mutableListOf()
        }
        map[curHash]!!.add(str)

    }

    return map.values.toList()
}

fun main() {
    val input1 = arrayOf("eat","tea","tan","ate","nat","bat")
    val ans = groupAnagrams(input1)
    for (list in ans) {
        println(list.joinToString(","))
    }
}
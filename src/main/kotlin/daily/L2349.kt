package daily

import java.util.*

class L2349 {

    private val indexMap = mutableMapOf<Int, Int>()
    private val numberMap = mutableMapOf<Int, TreeSet<Int>>()

    fun change(index: Int, number: Int) {
        if (indexMap.containsKey(index)) {
            val oldValue = indexMap[index]
            numberMap[oldValue]!!.remove(index)
            if (numberMap[oldValue]!!.isEmpty()) {
                numberMap.remove(oldValue)
            }
        }
        indexMap[index] = number
        numberMap[number] = numberMap.getOrDefault(number, TreeSet()).apply{ add(index) }
    }

    fun find(number: Int): Int {
        return numberMap[number]?.first()?:-1
    }

}
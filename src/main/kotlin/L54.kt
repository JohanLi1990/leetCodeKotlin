class L54 {


    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val res = mutableListOf<Int>()

        var roRight = matrix.size
        var coRight = matrix[0].size
        var roLeft = 0
        var coLeft = 0


        do {
            var i = roLeft
            var j = coLeft
            while (j < coRight) {
                res.add(matrix[i][j])
                j++
            }
            j--
            i++
            if (i >= roRight) break

            while (i < roRight) {
                res.add(matrix[i][j])
                i++
            }
            i--
            j--

            if (j < coLeft) break
            roRight--
            coRight--


            while(j >= coLeft) {
                res.add(matrix[i][j])
                j--
            }
            j++
            i--
            if (i <= roLeft) break
            while(i > roLeft) {
                res.add(matrix[i][j])
                i--
            }

            coLeft++
            roLeft++
        } while(coLeft < coRight && roLeft < roRight)
        return res
    }

}

fun main() {
    val input1 = arrayOf(
        intArrayOf(1,   2,  3, 10),
        intArrayOf(4,   5,  6, 11),
        intArrayOf(7,   8,  9, 12),
        intArrayOf(13, 14, 15, 16)
    )

    val input2 = arrayOf(
        intArrayOf(3),
        intArrayOf(2)
    )

    val input3 = arrayOf(
        intArrayOf(6,9,7)
    )

    val input4 = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(3, 4)
    )

    val input5 = arrayOf(
//        [1,11],[2,12],[3,13],[4,14],[5,15],[6,16],[7,17],[8,18],[9,19],[10,20]
        intArrayOf(1 , 11),
        intArrayOf(2 , 12),
        intArrayOf(3 , 13),
        intArrayOf(4 , 14),
        intArrayOf(5 , 15),
        intArrayOf(6 , 16),
        intArrayOf(7 , 17),
        intArrayOf(8 , 18),
        intArrayOf(9 , 19),
        intArrayOf(10, 20)
    )

    val input6 = arrayOf(
        intArrayOf(1,   2,  3),
        intArrayOf(4,   5,  6),
        intArrayOf(7,   8,  9)
    )

    val ans = L54()
    println(ans.spiralOrder(input1).joinToString(", "))
    println(ans.spiralOrder(input2).joinToString(", "))
    println(ans.spiralOrder(input3).joinToString(", "))
    println(ans.spiralOrder(input4).joinToString(", "))
    println(ans.spiralOrder(input5).joinToString(", "))
    println(ans.spiralOrder(input6).joinToString(", "))

}

class Solution {
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        var rows = matrix.size
        var cols = matrix[0].size

        var r = 0
        var c = -1
        var direction = 1

        val result = mutableListOf<Int>()

        while(rows > 0 && cols > 0) {
            for(i in 0..<cols) {
                c += direction
                result.add(matrix[r][c])
            }
            rows--

            for(j in 0..<rows) {
                r += direction
                result.add(matrix[r][c])
            }
            cols--

            direction *= -1
        }

        return result
    }
}


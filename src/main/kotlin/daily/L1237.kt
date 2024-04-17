package daily


// "bad" question: english not good
fun findSolution(customfunction:(Int, Int) -> Int, z:Int):List<List<Int>> {

    var x = 1
    var y = 1000
    val res = mutableListOf<MutableList<Int>>()
    while( x <= 1000 && y > 0) {
        val curSum =customfunction(x, y)
        if (curSum < z) {
            x++
        } else if (curSum > z) {
            y--
        } else {
            res.add(mutableListOf(x, y))
        }
    }
    return res
}




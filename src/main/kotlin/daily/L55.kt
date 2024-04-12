package daily
var arr: IntArray? = null
fun canJump(nums: IntArray): Boolean {
    arr = IntArray(nums.size)
    return dfs(nums, 0)
}

private fun dfs(nums: IntArray, index: Int) : Boolean {
    if (arr!![index] != 0) {
        return arr!![index] == 1
    }

    if (index + nums[index] >= nums.size - 1) return true

    for (i in 1..nums[index]) {
        if (dfs(nums, index + i )) {
            arr!![index] = 1
            return true
        }
    }
    arr!![index] = -1
    return false
}



fun main() {
    var arr = intArrayOf(2,3,1,1,4)
    println(canJump(arr))
    arr = intArrayOf(3,2,1,0,4)
    println(canJump(arr))

//    println("Trying out something atrocious")
//    for (i in 1..0) {
//        println("trying out somehitinin")
//    }
}
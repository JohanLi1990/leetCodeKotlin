package daily
fun canCompleteCircuitTLE(gas: IntArray, cost: IntArray): Int {
    // if total cost > total gas then it is definetly not possible
    if (gas.sum() < cost.sum()) return -1
    for (i in gas.indices) {
        if (dfs(gas, cost, i, 0, 0)) {
            return i
        }
    }
    return -1
}

fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
    var totalGas = 0
    var curGas = 0
    var startIdx = 0
    for (i in gas.indices) {
        val diff = gas[i] - cost[i];
        curGas += diff
        totalGas += diff

        if (curGas < 0) {
            startIdx = i + 1
            curGas = 0
        }
    }
    return if (totalGas < 0) -1 else startIdx

}

private fun dfs(gas: IntArray, cost: IntArray, index: Int, num: Int, curTank: Int) : Boolean {
    if (num == gas.size) {
        return true
    }

    if (gas[index] + curTank < cost[index]) return false

    val next = curTank +  gas[index] - cost[index]
    return dfs(gas, cost, (index + 1) % gas.size, num + 1, next)

}

fun main() {
    val gas = intArrayOf(1,2,3,4,5)
    val cost = intArrayOf(3,4,5,1,2)

    println(canCompleteCircuit(gas, cost))

}
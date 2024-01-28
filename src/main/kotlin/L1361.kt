
class L1361 {
}

fun validateBinaryTreeNodes(n: Int, leftChild: IntArray, rightChild: IntArray): Boolean {

    val parent = IntArray(n)
    for (i in 0..<n) {
        if (leftChild[i] == i || rightChild[i] == i) return false
        if (leftChild[i] != -1) {
            parent[leftChild[i]]++
        }
        if (rightChild[i] != -1) {
            parent[rightChild[i]]++
        }
    }

    var foundRoot = false
    var root = 0
    for (i in 0..<n) {
        if (foundRoot && parent[i] == 0) return false
        if (parent[i] == 0){
            foundRoot = true
            root = i
        }
        if (parent[i] > 1) return false
    }

    val size = mutableListOf<Int>()
    val visited = BooleanArray(n)
    size.add(0)
    dfs(root, leftChild, rightChild, size, visited)
    return size[0] == n
}

fun dfs(root: Int, leftChild: IntArray, rightChild: IntArray, size: MutableList<Int>, visited: BooleanArray) {
    if (visited[root]) {
        size[0] += 10_001
        return
    }

    visited[root] = true
    size[0] += 1
    if(leftChild[root] != -1) {
        dfs(leftChild[root], leftChild, rightChild, size, visited)
    }

    if (rightChild[root] != -1) {
        dfs(rightChild[root], leftChild, rightChild, size, visited)
    }
}


fun main() {
    val left = intArrayOf(1,0,3,-1)
    val right = intArrayOf(-1,-1,-1,-1)
    val n = 4
    println(validateBinaryTreeNodes(n, left, right))
}


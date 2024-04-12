package daily

class L42 {
}

fun trap(A: IntArray): Int {

    var lo = 0
    var hi = A.size - 1

    while(lo + 1 < A.size && A[lo] <= A[lo + 1]) {
        lo++
    }

    while(hi - 1 >= 0 && A[hi] <= A[hi - 1]) {
        hi--
    }

    if (lo >= hi) return 0

    var res = 0
    while(lo < hi) {
        val left = A[lo]
        val right  = A[hi]
        while(lo < hi && A[lo] <= A[hi] && A[lo] <= left) {
            res += left - A[lo]
            lo++
        }

        while(lo < hi && A[hi] < A[lo] && A[hi] <= right) {
            res += right - A[hi]
            hi--
        }
    }
    return res

}

fun main() {
    val input = intArrayOf(2,0,2)
    println(trap(input))
}
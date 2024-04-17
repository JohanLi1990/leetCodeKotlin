package daily

fun canAttendMeetings(intervals: Array<IntArray>): Boolean {

    // can attend meeting if there is no overlap
    intervals.sortBy{it[0]}
    var i = 0
    while(i < intervals.size - 1) {
        val cur = intervals[i]
        val next = intervals[i + 1]
        if (next[0] < cur[1]) {
            return false
        }
        i++
    }
    return true



}

fun main() {
    println(canAttendMeetings(arrayOf(intArrayOf(0, 30),
        intArrayOf(5, 10), intArrayOf(15, 20)))) // false

    println(canAttendMeetings(arrayOf(
        intArrayOf(7, 10),
        intArrayOf(2, 4)
    ))) // true
}
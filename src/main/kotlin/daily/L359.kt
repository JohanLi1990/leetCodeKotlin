package daily


class Logger() {

    private val map = HashMap<String, Int>()
    fun shouldPrintMessage(timestamp: Int, message: String): Boolean {
        if (!map.containsKey(message)) {
            map[message] = timestamp
            return true
        }
        if (timestamp - map[message]!! >= 10) {
            map[message] = timestamp
            return true
        }
        return false
    }
}
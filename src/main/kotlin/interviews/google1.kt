package interviews

/**
 * 482. License Key Formatting
 */
fun licenseKeyFormatting(s: String, k: Int): String {
    // rebuild the license key
    // we can build from the back
    // each time take k chars until I am unable to take

    var len = s.length

    val curGroup = StringBuilder()
    val res = StringBuilder()
    while( len-- > 0) {
        val c = s[len]
        if (c == '-') continue
        curGroup.append(c)
        if (curGroup.length == k) {
            res.append(curGroup.toString())
            res.append('-')
            curGroup.clear()
        }
    }

    res.append(curGroup.toString())

    if (res.isNotEmpty() && res[res.length - 1] == '-') {
        res.setLength(res.length - 1)
    }
    return res.reverse().toString().uppercase()
}

fun main() {
    println(licenseKeyFormatting("5F3Z-2e-9-w", 4))
    println(licenseKeyFormatting("---", 3))
}

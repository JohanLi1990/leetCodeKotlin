package interviews


fun numUniqueEmails(emails: Array<String>): Int {
    // for every email, process its string
    // add to a set
    val set = HashSet<String>()

    for (i in emails.indices) {
        val curString = StringBuilder()
        val before = emails[i].split("@")
        val local = before[0]
        val domain = before[1]
        for (j in local.indices) {
            if (local[j] == '.') continue
            if (local[j] == '+') {
                // ignore the rest of localname
                break
            }
            curString.append(local[j])
        }

        curString.append("@$domain")
        set.add(curString.toString())
    }
    return set.size
}

fun main() {
    println(numUniqueEmails(arrayOf("test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com")))
    println(numUniqueEmails(arrayOf("a@leetcode.com","b@leetcode.com","c@leetcode.com")))

    println(numUniqueEmails(arrayOf("test.email+alex@leetcode.com","test.email.leet+alex@code.com")))

}
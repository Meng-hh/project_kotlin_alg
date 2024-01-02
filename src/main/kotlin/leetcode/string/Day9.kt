package leetcode.string


fun main() {
    reverseWords("a good   example")
}

fun reverseWords(s: String): String {
    val strings = s.trim().split(" ").filter { it.isNotBlank() }.toMutableList()
    var left = 0
    var right = strings.size - 1

    while (right > left) {
        var temp = strings[left].trim()
        strings[left] = strings[right].trim()
        strings[right] = temp
        left++
        right--
    }
    return strings.joinToString(" ")
}
//kmp算法（不会）
fun strStr(haystack: String, needle: String): Int {
   return haystack.indexOf(needle)
}
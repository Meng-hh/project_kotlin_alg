package leetcode.string

fun main() {
   // reverseString("hello".toCharArray())
    reverseStr("abcdefg",4)
}

fun reverseString(s: CharArray): Unit {
    var left = 0
    var right = s.size - 1
    while (left < right) {
        val temp = s[left]
        s[left] = s[right]
        s[right] = temp
        left++
        right--
    }
    println(s)
}

fun reverseStr(s: String, k: Int): String {
    val ch = s.toCharArray()
    var i = 0
    while (i < ch.size ) {
        var start = i
        var end = (start + k - 1).coerceAtMost(ch.size - 1)
        while (start < end) {
            swapChar(ch,start,end)
            start++
            end --
        }
        i += 2*k
    }
    return ch.joinToString("")
}

private fun swapChar(charArray: CharArray, left: Int, right: Int) {
    val temp = charArray[left]
    charArray[left] = charArray[right]
    charArray[right] = temp
}
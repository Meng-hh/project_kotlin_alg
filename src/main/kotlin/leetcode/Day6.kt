package leetcode


fun main() {
    val n = 19
    println(isHappy(2))
}

fun isAnagram(s: String, t: String): Boolean {
    val map = mutableMapOf<Char, Int>()
    s.forEach { char ->
        if (map.keys.contains(char))
            map[char] = map[char]!! + 1
        else
            map[char] = 1
    }

    t.forEach { char ->
        if (map.keys.contains(char))
            map[char] = map[char]!! - 1
        else
            map[char] = Int.MAX_VALUE
    }
    return map.values.all { it == 0 }
}

fun intersection(nums1: IntArray, nums2: IntArray): IntArray {
    val list = mutableListOf<Int>()
    for (num in nums1.toSet()) {
        if (nums2.contains(num)) {
            list.add(num)
        }
    }
    return list.toIntArray()
}

fun isHappy(n: Int): Boolean {
    val map = mutableMapOf<MutableList<Int>, Int>()
    var i = n
    val list = mutableListOf<Int>()
    if (n<10) list.add(n)
    while (i >= 10) {
        list.add(i % 10)
        i /= 10
        if (i < 10) list.add(i)
    }
    map[list] = 0
    var count = 0
    while (count<100) {
        val res = list.fold(0) { acc, it ->
            acc + it * it
        }
        list.clear()
        if (res == 1) return true
        var j = res
        while (j >= 10) {
            list.add(j % 10)
            j /= 10
            if (j < 10) list.add(j)
        }
        if (res<10) list.add(res)
        if (map.contains(list)) return false
        map[list] = 0
        count++
    }
    return false
}

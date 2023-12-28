package leetcode


//也可以使用数组作为哈希索引
fun canConstruct(ransomNote: String, magazine: String): Boolean {
    val map = mutableMapOf<Char, Int>()
    magazine.forEach { char->
        map[char] = map.getOrDefault(char,0)+1
    }
    ransomNote.forEach { char ->
        if (map[char] !=null && map[char]!!-1 != -1) {
            map[char] = map[char]!! -1
        }else return false
    }
    return true
}

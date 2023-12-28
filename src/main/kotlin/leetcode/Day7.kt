package leetcode


fun main() {
    val intArray = IntArray(2)
    intArray[0] = 3
    intArray[1] = 3
    twoSum(intArray, 6)
}

//两数之和
fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    val intArray = IntArray(2)

    nums.withIndex().forEach { (index, num) ->
        val res = target - num

        if (map[res] != null) {
            intArray[0] = index
            intArray[1] = map[res]!!
        } else {
            map[num] = index
        }
    }
    return intArray
}

fun fourSumCount(nums1: IntArray, nums2: IntArray, nums3: IntArray, nums4: IntArray): Int {
    val map = mutableMapOf<Int, Int>()
    var res = 0
    for (i in nums1.indices) {
        for (j in nums2.indices) {
            val sum = nums1[i] + nums2[j]
            map[sum] = map.getOrDefault(sum, 0) + 1
        }

        for (k in nums3.indices) {
            for (l in nums4.indices) {
                val sum = nums3[k] + nums4[l]
                res += map.getOrDefault(0 - nums3[k] - nums4[l], 0)
            }
        }
    }
    return res
}
package leetcode

import kotlin.math.min

fun main() {
    val intArray = intArrayOf(1,2,3,4,5)
    val res = minSubArrayLen(11, intArray)
    println(res)

}

//寻找最短连续的且加起来等于目标值的子数组
fun minSubArrayLen(target: Int, nums: IntArray): Int {
    var slowIndex = 0
    var fastIndex = 0
    var minStep = 0

    while (slowIndex < nums.size) {
        val currStep = fastIndex - slowIndex + 1
        val currSum = nums.slice(slowIndex..fastIndex).sum()
        when {
            currSum >= target-> {
                minStep = if (minStep == 0 || currStep < minStep) currStep else minStep
                slowIndex++
            }
            currSum < target && fastIndex<nums.size -1 -> fastIndex++
            else -> break
        }
        //if ((slowIndex == fastIndex && slowIndex == nums.size - 1)||fastIndex>nums.size -1) break
    }
    return minStep
}

fun minSubArrayLen2(target: Int, nums: IntArray): Int {
    var slowIndex = 0
    var fastIndex = 0
    var minStep = 0
    var currSum = 0
    while (fastIndex < nums.size) {
        currSum += nums[fastIndex++]
        while (currSum >= target) {
            val temp = fastIndex - slowIndex
            minStep = if (minStep == 0 ||  temp < minStep) temp else minStep
            currSum -= nums[slowIndex ++]
        }
    }
    return minStep
}
import java.io.File
import kotlin.math.pow
import kotlin.system.measureTimeMillis


fun main() {
    val lines = File("/Users/coding_lyl/Desktop/day4.txt").readLines()
    val digits = mutableMapOf<String, MutableList<Int>>()
    val time = measureTimeMillis {
        lines.forEach { line ->
            val list = mutableListOf<Int>()
             val strings = line.split("|")
            val luckyStrs = strings[0].split(":")[1]
            strings[1].split(" ").filterNot { it.isBlank() }.forEach { d->
                list.add(d.trim().toInt())
            }
            digits[luckyStrs]=list
        }
        val sum = partTwo(digits)
        println(sum)
    }
    println("---- time taken $time ms ----")
}

private fun partOne(digits:MutableMap<String,MutableList<Int>>): Int {
    var totalScore = 0
    digits.forEach { (luckyStr,digitLine)->
        val luckList = mutableListOf<Int>()
        luckyStr.split(" ").filterNot { it.isBlank() }.forEach { luckList.add(it.toInt()) }
        val count = digitLine.count { it in luckList }
        totalScore += 2.0.pow(count.toDouble()).toInt()
    }
    return totalScore
}

private fun partTwo(digits:MutableMap<String,MutableList<Int>>): Int {
    val arrayList = (1..211).map { 1 }.toCollection(ArrayList(211))
    var n =1
    digits.forEach { (luckyStr, digitLine) ->
        val luckList = mutableListOf<Int>()
        luckyStr.split(" ").filterNot { it.isBlank() }.forEach { luckList.add(it.toInt()) }
        val count = digitLine.count { it in luckList }
        for (i in n..count) {
            if (i<arrayList.size){
                arrayList[i] = arrayList[i]+ arrayList[n]
            }
        }
        n++
    }
    return arrayList.sum() + digits.size
}


import java.io.File
import java.io.FileNotFoundException
import java.util.*

class Solution {

    data class Entity(val first: Int, var end: Int)

    private fun getDataByFile(): List<String> {
        val data = mutableListOf<String>()
        try {
            val file = File("/Users/coding_lyl/Desktop/input.txt")
            val reader = Scanner(file)
            while (reader.hasNextLine()) {
                val line = reader.nextLine()
                data.add(line)
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
            println("read data error!")
        }
        return data
    }

    fun solution(): Int {
//        val entities = parse()
//        return summary(entities)
        return partTwo()
    }


    private fun parse(): List<Entity> {
        val data = getDataByFile()
        val list = mutableListOf<Entity>()
        data.forEach { line ->
            var isFirst = true
            line.toCharArray().forEach { char ->
                if (Character.isDigit(char) && isFirst) {
                    isFirst = false
                    list.add(Entity(char.toString().toInt(), char.toString().toInt()))
                }
                if (Character.isDigit(char) && !isFirst) {
                    list.last().end = char.toString().toInt()
                }
            }
        }
        return list
    }

    private fun summary(data: List<Entity>): Int {
        var sum = 0
        data.forEach { entity ->
            val v = entity.first * 10 + entity.end
            sum += v
        }
        println("result is $sum")
        return sum
    }


    private fun partTwo(): Int {
        val data = getDataByFile()
        val res = mutableListOf<Entity>()
        val map = mutableMapOf<String, Int>()
        map["one"] = 1
        map["two"] = 2
        map["three"] = 3
        map["four"] = 4
        map["five"] = 5
        map["six"] = 6
        map["seven"] = 7
        map["eight"] = 8
        map["nine"] = 9
        data.forEach { line ->
            val first = line.find { it.isDigit() }?.let {
                val index = line.toCharArray().indexOf(it)
                val digitAndStrMap = getIndexByStr(map, line)
                val minIndex = digitAndStrMap.keys.minOrNull() ?: Int.MAX_VALUE
                if (minIndex < index) {
                    map[digitAndStrMap[minIndex]]
                } else
                    line[index].toString().toInt()
            } ?: getIndexByStr(map, line).keys.minOrNull() ?: throw RuntimeException("error")

            val end = line.findLast { it.isDigit() }?.let {
                val index = line.toCharArray().indexOfLast { foo -> foo == it }
                val digitAndStrMap = getIndexByStr(map, line)
                val maxIndex = digitAndStrMap.keys.maxOrNull() ?: -1
                if (maxIndex > index)
                    map[digitAndStrMap[maxIndex]]
                else
                    line[index].toString().toInt()
            } ?: getIndexByStr(map, line).keys.maxOrNull() ?: throw RuntimeException("error")

            res.add(Entity(first,end))
        }
       return summary(res)
    }

    private fun getIndexByStr(map: MutableMap<String, Int>, line: String): MutableMap<Int, String> {
        val digitAndStrMap = mutableMapOf<Int, String>()
        map.keys.forEach { digitStr ->
            val regex = Regex(digitStr)
            val strIndex = line.indexOf(digitStr)
            if (strIndex!= -1) {
                digitAndStrMap[strIndex] = digitStr
                if (regex.findAll(line).count()>1) {
                    val lastIndexOf = line.lastIndexOf(digitStr)
                    digitAndStrMap[lastIndexOf] = digitStr
                }
            }
        }
        return digitAndStrMap
    }

}
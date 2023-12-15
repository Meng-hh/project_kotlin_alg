import java.io.File

private val symbols by lazy {
    return@lazy listOf("+", "-", "*", "/", "@", "#", "$", "%", "&", "=")
}
private val digits by lazy {
    return@lazy listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0")
}

//每行都为140个字符，判定是否为partNumber的条件是看周围有无符号
fun main() {
    val lines = File("/Users/coding_lyl/Desktop/day3.txt").readLines()
    val data = mutableListOf<String>()
    lines.forEach { line ->
        data.add(line)
    }
    val res = parse2(data)
    partTwo(data)
    val sum = res.sum()
    println("res is $sum")
}

private fun parse2(data: List<String>): MutableList<Int> {
    val res = mutableListOf<Int>()
    data.withIndex().forEach { (externalIndex, chars) ->
        chars.withIndex().forEach { (internalIndex, char) ->
            val flag = chars.findLastAnyOf(digits, internalIndex - 1)?.let { internalIndex - it.first >= 2 } ?: true
            if (char.isDigit() && flag) {
                val isTwoLayer =
                    chars.findAnyOf(digits, internalIndex + 1)?.let { it.first - internalIndex <= 1 } ?: false
                if (isTwoLayer) {
                    val isThreeLayer =
                        chars.findAnyOf(digits, internalIndex + 2)?.let { it.first - internalIndex <= 2 } ?: false
                    if (!isThreeLayer) {
                        val r = "${chars[internalIndex]}${chars[internalIndex + 1]}".toInt()
                        val isPartNumber = judgeLeftAndRight(chars, internalIndex, 2)
                        if (isPartNumber)
                            res.add(r)
                        if (!isPartNumber && judgeTopAndBottom(externalIndex, data, internalIndex, 2))
                            res.add(r)
                    } else {
                        val r3 = "${chars[internalIndex]}${chars[internalIndex + 1]}${chars[internalIndex + 2]}".toInt()
                        val isPartNumber = judgeLeftAndRight(chars, internalIndex, 3)
                        if (isPartNumber)
                            res.add(r3)
                        if (!isPartNumber && judgeTopAndBottom(externalIndex, data, internalIndex, 3))
                            res.add(r3)
                    }
                } else {
                    val r = char.toInt1()
                    val isPartNumber = judgeLeftAndRight(chars, internalIndex, 1)
                    if (isPartNumber)
                        res.add(r)
                    if (!isPartNumber && judgeTopAndBottom(externalIndex, data, internalIndex, 1))
                        res.add(r)
                }
            }
        }
    }
    return res
}

private fun partTwo(data: List<String>): Int {
    val digitList = mutableListOf<Int>()
    val res = mutableListOf<Int>()
    data.withIndex().forEach { (externalIndex, chars) ->
        chars.withIndex().forEach { (internalIndex, char) ->
            if (char == '*') {
                val rightFlag =
                    chars.findAnyOf(digits, internalIndex + 1)?.let { it.first - internalIndex == 1 } ?: false
                val leftFlag =
                    chars.findLastAnyOf(digits, internalIndex - 1)?.let { internalIndex - it.first == 1 } ?: false
                val topAndRightFlag =
                    if (externalIndex > 0) data[externalIndex - 1].findAnyOf(digits, internalIndex + 1)
                        ?.let { it.first - internalIndex == 1 } ?: false
                    else false
                val topAndLeftFlag =
                    if (externalIndex > 0) data[externalIndex - 1].findLastAnyOf(digits, internalIndex - 1)
                        ?.let { internalIndex - it.first == 1 } ?: false
                    else false
                val bottomAndRightFlag =
                    if (externalIndex < data.size - 1) data[externalIndex + 1].findAnyOf(digits, internalIndex + 1)
                        ?.let { it.first - internalIndex == 1 } ?: false
                    else false
                val bottomAndLeftFlag =
                    if (externalIndex < data.size - 1) data[externalIndex + 1].findLastAnyOf(digits, internalIndex - 1)
                        ?.let { internalIndex - it.first == 1 } ?: false
                    else false
                val booleans = mapOf(
                    Pair("rightFlag", rightFlag),
                    Pair("leftFlag", leftFlag),
                    Pair("topAndLeftFlag", topAndLeftFlag),
                    Pair("topAndRightFlag", topAndRightFlag),
                    Pair("bottomAndLeftFlag", bottomAndLeftFlag),
                    Pair("bottomAndRightFlag", bottomAndRightFlag)
                )
                val trues = booleans.filter { it.value }
                if (trues.size >= 2) {
                    trues.forEach { (k, v) ->
                        when {
                            k == "leftFlag" && v ->{
                                val d = findDigit(data, externalIndex, internalIndex - 1)
                                if (trues.size == 3 && digitList.isNotEmpty() && digitList.last() == d)
                                    digitList[digitList.size-1] =d
                                else
                                    digitList.add(d)
                            }
                            k == "rightFlag" && v-> {
                                val d = findDigit(data, externalIndex, internalIndex + 1)
                                if (trues.size == 3 && digitList.isNotEmpty() && digitList.last() == d)
                                    digitList[digitList.size-1] =d
                                else
                                    digitList.add(d)
                            }
                            k == "topAndLeftFlag"&& v ->  {
                                val d = findDigit(data, externalIndex - 1, internalIndex - 1)
                                if (trues.size == 3 && digitList.isNotEmpty() && digitList.last() == d)
                                    digitList[digitList.size-1] =d
                                else
                                    digitList.add(d)
                            }
                            k == "topAndRightFlag"&& v -> {
                                val d = findDigit(data, externalIndex - 1, internalIndex + 1)
                                if (trues.size == 3 && digitList.isNotEmpty() && digitList.last() == d)
                                    digitList[digitList.size-1] =d
                                else
                                    digitList.add(d)
                            }
                            k =="bottomAndLeftFlag" && v->{
                                val d = findDigit(data, externalIndex + 1, internalIndex - 1)
                                if (trues.size == 3 && digitList.isNotEmpty() && digitList.last() == d)
                                    digitList[digitList.size-1] =d
                                else
                                    digitList.add(d)
                            }
                            k == "bottomAndRightFlag" && v-> {
                                val d = findDigit(data, externalIndex + 1, internalIndex + 1)
                                if (trues.size == 3 && digitList.isNotEmpty() && digitList.last() == d)
                                    digitList[digitList.size-1] =d
                                else
                                    digitList.add(d)
                            }
                        }
                    }
//                    if (digitList.size >= 2) {
//                        var distinct =digitList
//                        if (digitList.size ==3)
//                             distinct = digitList.distinct().toMutableList()
////                        res.add(distinct.first() * distinct.last())
//                    }
//                    digitList.clear()
                }
            }
        }
    }
    return res.sum()
}

private fun findDigit(data: List<String>, externalIndex: Int, internalIndex: Int): Int {
    var res = 1
    val line = data[externalIndex]
    val rightIsDigit = line.findAnyOf(digits, internalIndex + 1)?.let { it.first - internalIndex == 1 } ?: false
    val rightTwoIsDigit = line.findAnyOf(digits, internalIndex + 2)?.let { it.first - internalIndex == 2 } ?: false
    val leftIsDigit = line.findAnyOf(digits, internalIndex - 1)?.let { internalIndex - it.first == 1 } ?: false
    val leftTwoIsDigit = line.findAnyOf(digits, internalIndex - 2)?.let { internalIndex - it.first == 2 } ?: false
    when {
        rightIsDigit -> {
            if (rightTwoIsDigit) res =
                "${line[internalIndex]}${line[internalIndex + 1]}${line[internalIndex + 2]}".toInt()
            if (leftIsDigit) res = "${line[internalIndex - 1]}${line[internalIndex]}${line[internalIndex + 1]}".toInt()
            if (!rightTwoIsDigit && !leftIsDigit) res = "${line[internalIndex]}${line[internalIndex + 1]}".toInt()
        }
        leftIsDigit -> {
            res =
                if (leftTwoIsDigit) "${line[internalIndex - 2]}${line[internalIndex - 1]}${line[internalIndex]}".toInt()
                else "${line[internalIndex - 1]}${line[internalIndex]}".toInt()
        }
        else -> {
            res = line[internalIndex].toInt1()
        }
    }
    return res
}


private fun judgeTopAndBottom(externalIndex: Int, data: List<String>, internalIndex: Int, layer: Int): Boolean {
    var topAndRightFlag = false
    var topAndLeftFlag = false
    var bottomAndLeftFlag = false
    var bottomAndRightFlag = false
    if (externalIndex > 0) {
        topAndRightFlag =
            data[externalIndex - 1].findAnyOf(symbols, internalIndex)?.let { it.first - internalIndex <= layer }
                ?: false
        topAndLeftFlag =
            data[externalIndex - 1].findLastAnyOf(symbols, internalIndex)?.let { internalIndex - it.first <= 1 }
                ?: false
    }
    if (externalIndex < data.size - 1) {
        bottomAndRightFlag =
            data[externalIndex + 1].findAnyOf(symbols, internalIndex)?.let { it.first - internalIndex <= layer }
                ?: false
        bottomAndLeftFlag =
            data[externalIndex + 1].findLastAnyOf(symbols, internalIndex)?.let { internalIndex - it.first <= 1 }
                ?: false
    }
    return topAndLeftFlag || topAndRightFlag || bottomAndLeftFlag || bottomAndRightFlag
}

private fun judgeLeftAndRight(chars: String, internalIndex: Int, layer: Int): Boolean {
    val rightFlag = chars.findAnyOf(symbols, internalIndex)?.let { it.first - internalIndex <= layer } ?: false
    val leftFlag = chars.findLastAnyOf(symbols, internalIndex)?.let { internalIndex - it.first <= 1 } ?: false
    return rightFlag || leftFlag
}

fun Char.toInt1() = this.toString().toInt()



import java.io.FileReader
import java.util.Scanner
import kotlin.system.measureTimeMillis

private val rulerMap by lazy {
    val ruler = mutableMapOf<String, Int>()
    ruler["red"] = 12
    ruler["green"] = 13
    ruler["blue"] = 14
    return@lazy ruler
}


//袋子中有12红，13绿，14蓝，得到所有能玩的游戏ID的和（每次都会放回）
fun main() {
    val scanner = Scanner(FileReader("/Users/coding_lyl/Desktop/day2.txt")).useDelimiter("Game")
    var sum = 0
    var index = 0
    val resList = mutableListOf<Bag>()
    while (scanner.hasNextLine()) {
        val line = scanner.nextLine().split(":")[1].trim()
        index++
        val time = measureTimeMillis {
//            if (judge(line)) {
//                sum += index
//                println(index)
//            }

            val res = partTwo(line)
            resList.add(res.first())
        }
        println("---- time taken $time ms ----")
    }
    val summary = summary(resList)
    println("summary:$summary")
    // println("result is $sum")
}

private fun judge(line: String): Boolean {
    var flag = true
    val lineList = line.split(";")
    val iterator = lineList.iterator()
    while (iterator.hasNext()) {
        flag = aloneJudge(iterator.next())
        if (!flag) break
    }
    return flag
}

private fun aloneJudge(statement: String): Boolean {
    var flag1 = true
    statement.split(",").forEach loop@{ group ->
        val chars = group.trim().split(" ")
        if (chars[0].toInt() > rulerMap[chars[1]]!!) {
            flag1 = false
            return@loop
        }
    }
    return flag1
}

data class Bag(var redCount: Int, var blueCount: Int, var greenCount: Int)

private fun partTwo(line: String): MutableList<Bag> {
    val res = mutableListOf<Bag>()
    val iterator = line.split(";").iterator()
    var isFirst = true
    while (iterator.hasNext()) {
        iterator.next().split(",").forEach { group ->
            val redCount = if (group.contains("red")) group.trim().split(" ")[0].toInt() else 1
            val blueCount = if (group.contains("blue")) group.trim().split(" ")[0].toInt() else 1
            val greenCount = if (group.contains("green")) group.trim().split(" ")[0].toInt() else 1
            if (isFirst) {
                res.add(Bag(redCount, blueCount, greenCount))
                isFirst = false
            } else {
                val bug = res.last()
                bug.redCount = if (redCount > bug.redCount) redCount else bug.redCount
                bug.blueCount = if (blueCount > bug.blueCount) blueCount else bug.blueCount
                bug.greenCount = if (greenCount > bug.greenCount) greenCount else bug.greenCount
            }
        }
    }
    return res
}

private fun summary(bugs: List<Bag>): Int {
    return bugs.fold(0) { acc, bug ->
        acc + bug.redCount * bug.blueCount * bug.greenCount
    }
}
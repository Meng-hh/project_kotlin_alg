import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import java.util.regex.Pattern

fun main() {

    //testRegx()
    //testDateLoop()
    test()

}

private fun test() {

    val arrayList = mutableListOf<Int>()
    arrayList.addAll(1..10)
    for (i in 0..9) {
        if (i!=0) {
            //arrayList[i] = arrayList[i]+arrayList[i-1]
            arrayList[i] = arrayList[i] +1
        }
    }
    println("sum is ${arrayList.sum()}")
}

private fun testDateLoop() {
    val start = Date(2023, 1, 15)
    val end = Date(2023, 1, 20)
    var date = start
    while (date.before(end)) {
        println(date.toLocalDate())
        date.add(1)
    }
}

fun Date.toLocalDate(): LocalDate {
    return this.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}
fun Date.add(day: Int = 0, month: Int = 0, year: Int = 0) = Calendar.getInstance()!!.also {
    it.time = this
    it.add(Calendar.YEAR, year)
    it.add(Calendar.MONTH, month)
    it.add(Calendar.DATE, day)
}.time!!

private fun testRegx() {
    val regex = Pattern.compile("[^0-9]*([0-9]{1,3})[^0-9]*")

    val string = "#123%abc456%def789&ghi098#!xyz321@"

    val matcher = regex.matcher(string)

    while (matcher.find()) {
        val startIndex = matcher.start()
        val endIndex = matcher.end()
        val number = matcher.group(1)

        println("Found number from $startIndex to $endIndex: $number")
    }
}




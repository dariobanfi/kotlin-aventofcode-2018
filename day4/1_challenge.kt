package day4

import java.io.File

fun main(args : Array<String>) {
    val fileLines: List<String> = File("day4/input.txt").readLines()
    val sortedLog = fileLines.sorted()


    val log = mutableMapOf<Int, MutableList<MutableList<Int>>>()
    var currentGuardId: Int = -1
    var sleepMinute: Int =  -1

    sortedLog.forEach { line ->
        println(line)
        if (line.contains("Guard")) {
            val guardId = line.split(" ")[3].replace("#", "").toInt()
            if (guardId != currentGuardId) {
                if (log[currentGuardId] == null) {
                    log[currentGuardId] = mutableListOf(mutableListOf())
                } else {
                    log[currentGuardId]?.add(mutableListOf())
                }
            }
            currentGuardId = guardId
        } else if (line.contains("falls asleep")) {
            sleepMinute = line.split(" ")[1].removeSuffix("]").takeLast(2).toInt()
        } else if (line.contains("wakes up") && currentGuardId != -1 && sleepMinute != -1) {
            val wakeUpMinute = line.split(" ")[1].removeSuffix("]").takeLast(2).toInt()
            for (i in sleepMinute..wakeUpMinute) {
                log[currentGuardId]?.last()?.add(i)
            }
        } else {
            throw Exception("Invalid data found")
        }
    }

    var totalMinsSleeping = 0
    var mostSleepyGuard = 0
    log.forEach { key, content ->
        val minutesSleeping = content.map { it.count() }.sum()
        if (minutesSleeping > totalMinsSleeping) {
            totalMinsSleeping = minutesSleeping
            mostSleepyGuard = key
        }
    }
    val commonMinute = log[mostSleepyGuard]
        ?.flatten()
        ?.groupingBy { it }
        ?.eachCount()
        ?.maxBy { it.value }
        ?.key
    if (commonMinute != null) {
        println("Solution is: guard $mostSleepyGuard Minute $commonMinute ${mostSleepyGuard*commonMinute.toInt()}")
    }

}

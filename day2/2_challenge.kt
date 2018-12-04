package day2

import java.io.File

fun main(args : Array<String>) {
    val fileLines: List<String> = File("day2/input.txt").readLines()
    var first = ""
    var second = ""
    loop@ for (i in 0..fileLines.size - 1)  {
        for (j in 0..fileLines.size -1) {
            if (i != j) {
                first = fileLines[i]
                second = fileLines[j]
                val hammingDistance = first.zip(second).count { it.first != it.second }
                if (hammingDistance == 1) {
                    break@loop
                }
            }
        }
    }

    val commonLetters = first.zip(second).filter { it.first == it.second }.map { it.first }.joinToString("")
    println("Solution is $commonLetters")
}

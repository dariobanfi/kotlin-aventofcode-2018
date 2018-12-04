package day1

import java.io.File

fun main(args : Array<String>) {
    val fileLines: List<String> = File("day1/input.txt").readLines()
    val result = fileLines
        .map{
            it.toInt()
        }
        .reduce { acc, line ->
            acc + line
        }

    println("Solution is: $result")
}

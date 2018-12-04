package day4

import java.io.File

fun main(args : Array<String>) {
    val fileLines: List<String> = File("day4/input.txt").readLines()

    fileLines.forEach { line ->
        println(line)
    }
}

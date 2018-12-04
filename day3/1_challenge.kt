package day3

import java.io.File

fun main(args : Array<String>) {
    val fileLines: List<String> = File("day3/input.txt").readLines()

    val matrix = Array(1000, {IntArray(1000)})

    var conflicts = 0

    fileLines.forEach { line ->
        val splittedLine = line.split(" ")
        val x = splittedLine[2].split(",")[0].toInt()
        val y = splittedLine[2].split(",")[1].replace(":", "").toInt()
        val lengthX = splittedLine[3].split("x")[0].toInt()
        val lengthY = splittedLine[3].split("x")[1].toInt()

        (x..(x + lengthX - 1)).forEach { i ->
            (y..(y + lengthY - 1)).forEach { j ->
                matrix[i][j]++
            }
        }
    }

    (0..(matrix.size - 1)).forEach { i ->
        (0..(matrix.first().size - 1)).forEach { j ->
            if (matrix[i][j] > 1) {
                conflicts++
            }
        }
    }

    println("Solution: $conflicts")
}
